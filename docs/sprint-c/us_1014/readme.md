# US 1014

## 1. Context

*This is the first time this user story is being requested.*

## 2. Requirements

**US 1014:** As Customer Manager, I want to record the time and date for an interview with a
candidate.

**Acceptance Criteria:**

- 1014.1. The user should be registered as a customer manager;
- 1014.2. The system should confirm the operation
- 1014.3. The system should show to the customer manager the job opening list and job application list

**Client Clarifications**

> **Question:** When should it be possible to set this date? Will it be possible to schedule the interview only when the recruitment phase is at the intervenist stage? Or is it possible to score in other previous phases?
>
>  **Answer:** For me it may be possible to schedule interviews before but one should pay attention to whether the candidate "passed" the screening. It makes no sense to schedule an interview for a candidate who has not been accepted. Keep this type of aspect in mind.

> **Question:** Is it possible to schedule interviews for any day and time or we must take into account weekends, working hours and holidays, for example?
>
>  **Answer:** The system should display the day of the week for the selected date. But the system should accept any valid date.

> **Question:** When scheduling an interview with a candidate, should it be possible to schedule more than one interview per application?
>
> **Answer:** The system at this stage is thought to only support one interview per job Opening so it doesn’t make much sense to schedule more than one interview for a candidate other than to reschedule the same interview (for example, the applicant was justifiably absent from a previous interview). In this context, I would say that it makes more sense to be able to change the appointment of an interview than to schedule more than one interview for the same candidate.

> **Question:** You enter a date and the system should guess the day of the week of this date and show it in the screen? I couldn't understand well.
>
> **Answer:** For the data entered by the user the system should display the day of the week: Monday, Tuesday, Wednesday, etc. It should not be a guess, but the true/real day of the week.





## 3. Analysis

*This main functionality is for the Customer Manager, so the user needs to be authenticated first to be able to record the time and date for an interview with a candidate.*

**System Sequence Diagram:**

![SSD-US-1014](ssd/ssd-us-1014.svg)

## 4. Design
The principal function is register a day and time for an interview, the input for the Customer Manager consists on:

* Select a Job Opening
* Select an application
* Insert the day
* Insert the time

After successfully submitting this information, the system should update the application and record the day and time for the interview.

### 4.1.1. Domain Model
![Domain model](dm/domain-model-us-1014.svg)

### 4.1. Realization
![Sequence diagram](sd/sd-us-1014.svg)

### 4.2. Class Diagram

![a class diagram](cd/cd-us-1014.svg)

### 4.3. Applied Patterns
* **DTO**
* **Repository**
* **Service**

> **Repository Pattern**
> * ApplicationRepository
> * JobOpeningRepository
>
> **Justifications**
>
>The repositories were employed to persist applications and reconstruct objects from the
persistence (applications and job openings).


> **Service Pattern**
> * AuthorizationService
> * ApplicationManagementService
> * JobOpeningManagementService
>
> **Justifications**
>
> The AuthorizationService, is a pre-existing service within the Eapli.Framework were used here
> to retrieve the logged-in user with Customer Manager roles.
>
> The JobOpeningManagementService is employed to register Job Openings, tasked with the responsibility of
give us the list of all job openings.
>
> The ApplicationManagementService is employed to register Application, tasked with the responsibilities of
give us the list of all application and update the chosen one.
> 
> The mentioned services were developed because the functionalities they offer will be utilized across multiple use
> cases.

> **DTO**
> * ApplicationDTOService
> * JobOpeningDTOService
>
> **Justifications**
>
> In order to enforce encapsulation amongst layers and adequate responsibility assigment, the ApplicationDTOService and JobOpeningDTOService
> were created, besides being a set of instructions that is used in other functionalities.
>
### 4.4. Tests

**Test 1:** Verifies if equal users are detected

**Refers to Acceptance Criteria:** 2000a.1

````
    @Test
    public void ensureEqualsCandidateUsersPassesForSamePhoneNumber() throws Exception {

        final Candidate candidate1 = new Candidate(getNewDummyUser(),phoneNumber1);
        final Candidate candidate2 = new Candidate(getNewDummyUser(),phoneNumber1);

        final boolean expected = candidate1.equals(candidate2);

        assertTrue(expected);
    }
````
**Test 2:** Verifies if a candidate without phone number fails

**Refers to Acceptance Criteria:** 2000a.2

````
    @Test
    public void ensureCandidateUserWithoutPhoneNumberFails(){
        assertThrows(IllegalArgumentException.class, () -> new Candidate(getNewDummyUser(), null));
    }
````
**Test 3:** Verifies if a candidate without system user fails

**Refers to Acceptance Criteria:** 2000a.2

````
    @Test
    public void ensureCandidateUserWithoutSystemUserFails(){
        assertThrows(IllegalArgumentException.class, () -> new Candidate(null, phoneNumber1));
    }
````
**Test 4:** Verifies if a phone number without extension Fails

**Refers to Acceptance Criteria:** 2000a.1

````
    @Test
    public void ensurePhoneNumberWithoutExtensionFails() {
        assertThrows(NullPointerException.class, () -> new PhoneNumber(null, "910000000"));
    }
````
**Test 5:** Verifies if a phone number without number Fails

**Refers to Acceptance Criteria:** 2000a.1

````
    @Test
    public void ensurePhoneNumberWithoutNumberFails() {
        assertThrows(NullPointerException.class, () -> new PhoneNumber("+351", null));
    }
````

**Test 6:** Verifies if an extension without "+" Fails

**Refers to Acceptance Criteria:** 2000a.1

````
    @Test
    public void ensureExtensionWithoutPlusFails(){
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("351", "12345678"));
    }
````

**Test 6 and 7:** Verifies if a number with less than 8 digits and plus than 15 digits Fails

**Refers to Acceptance Criteria:** 2000a.1

````
    @Test
    public void ensurePhoneNumberLessThan8DigitsFails() {
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("+351", "1234567"));
    }
    
    @Test
    public void ensurePhoneNumberPlusThan15DigitsFails() {
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("+351", "1234567890123456"));

    }
````
## 5. Implementation

### RegisterCandidateController

```
 public boolean registerCandidate(String name, String email, String extension, String number){
        Optional<SystemUser> operator = authz.loggedinUserWithPermissions(BaseRoles.OPERATOR);
        PhoneNumber phoneNumber = new PhoneNumber(extension, number);
        operator.ifPresent(systemUser -> candidateManagementService.registerCandidate(name, email, phoneNumber));

        return true;
    }
```
### CandidateManagementService

```
 public void registerCandidate(String name, String email, PhoneNumber phoneNumber) {
        String password = passwordService.generatePassword();

        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.CANDIDATE_USER);

        SystemUser sysUser = userManagementService.registerNewUser(email, password, name,"Candidate",email, roles);

        final DomainEvent event = new NewCandidateUserRegisteredEvent(sysUser,phoneNumber);
        dispatcher.publish(event);
    }
```
### NewCandidateUserRegisteredWatchDog

```
 @Override
    public void onEvent(final DomainEvent domainEvent) {
        assert domainEvent instanceof NewCandidateUserRegisteredEvent;

        final NewCandidateUserRegisteredEvent newCandidateUserRegisteredEvent = (NewCandidateUserRegisteredEvent) domainEvent;

        final AddCandidateOnNewCandidateUserRegisteredController controller = new AddCandidateOnNewCandidateUserRegisteredController();
        controller.registerNewCandidate(newCandidateUserRegisteredEvent);
    }
```
### AddCandidateOnNewCandidateUserRegisteredController

```
 public void registerNewCandidate(NewCandidateUserRegisteredEvent event) {
        candidateRepository.save(new Candidate(event.systemUser(), event.phoneNumber()));
    }
```
## 6. Integration/Demonstration
To execute this functionality it is necessary to run the script named `run-backoffice-app` and log in with Operator permissions
after it, must select the menu `Operator` followed by `Register a Candidate`.

