# US 1018

## 1. Context

*This is the first time this user story is being requested.*

## 2. Requirements

**US 1018:** As Customer Manager, I want to execute the process that evaluates (grades) the
interviews for a job opening.

**Acceptance Criteria:**

- 1018.1. Logged as a Customer Manager;

- 1018.2. Must have at least one job opening;

**Client Clarifications**

> **Question:** Our group has a question regarding the processing of candidate response files for the interview. In the case of uploading a file, if the question requiring a number as an answer is filled with an invalid format, for example a letter, we should consider this as an invalid format in US 1017 (and ask the user to upload a valid file again) or should we, in US1018, consider that it is incorrect and assign 0 points automatically for this invalid response? That is, in US 1017, we should just check the file format or should we also check if the answers are filled with the correct data type?
>
> **Answer:** The case mentioned should be considered a file validation error (that is, the submitted file does not match the defined grammar)

> **Question:** Regarding US1018, after the execution of the evaluation process of all job Opening interviews, should the stage where it is be automatically changed to "Result" or should it be kept in "Analysis" and can only be changed by the execution of US1010?
> 
> **Answer:** The US1018 should not change the current phase. The US1010 allows you to change phases of the recruitment process.

## 3. Analysis

*This functionality is for the Customer Manager, so the user needs to be authenticated first to be able to execute the process that evaluates the interviews.*

**System Sequence Diagram:**

![SSD-US-2000a](US2000a_SSD/ssd-us-2000a.svg)


## 4. Design
The principal function is to register a candidate, the input for the Operator consists of:

* Candidate Name
* Candidate Email
* Candidate Phone Number

After successfully submitting this information, the system should create a candidate and the corresponding user.

### 4.1. Realization

![Sequence diagram](US2000a_SD/sd-us-2000a.svg)

### 4.2. Class Diagram

![a class diagram](US2000a_Class_Diagram/class-diagram-us-2000a.svg)

### 4.3. Applied Patterns
* **Observer**
* **Repository**
* **Service**

> **Repository Pattern**
> * CandidateRepository
>
> **Justifications**
>
>The repositories were employed to persist candidates and usercandidates, as well as to reconstruct objects from the
persistence.


> **Service Pattern**
> * AuthorizationService
> * CandidateManagementService
> * UserManagementService
>
> **Justifications**
>
> The UserManagementService and AuthorizationService, pre-existing services within the Eapli.Framework were used here
> to register users and retrieve the logged-in user with Operator roles.
>
> The CandidateManagementService is employed to register candidates, tasked with the responsibility of
candidate creation.
>
> The mentioned services were developed because the functionalities they offer will be utilized across multiple use
> cases.

> **Observer**
> * EventPublisher
> * NewCandidateUserRegisteredEvent
> * NewCandidateUserRegisteredWatchDog
> * AddCandidateOnNewCandidateUserRegisteredController
>
> **Justifications**
>
> All the mentioned objects are components of the applied observer pattern. This pattern was implemented to ensure
> that when a new candidate is registered, a user is automatically registered as well. Following this procedure, upon the
> registration of a candidate, a NewCandidateUserRegisteredEvent instance is generated, and the EventPublisher is utilized
> to notify the WatchDog (Observer).
>
> Upon receiving this notification, the WatchDog triggers the registration of the
> user through the AddCandidateOnNewCandidateUserRegisteredController. 
>
> In this case, an instance of NewCandidateUserRegisteredEvent is used to inform the specific WatchDog,
> which then invokes the AddCandidateOnNewCandidateUserRegisteredController for the registration of the CandidateUser.
>
> To maintain consistency in the creation process, we used the EventPublisher within the service to
> ensure that both the candidate and its user were created, thus preserving the system's valid state.
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

