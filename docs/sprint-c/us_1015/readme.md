# US 1015

## 1. Context

This is the first time this user story is being requested.

## 2. Requirements

**US 1015** As {Customer Manager}, I want to execute the process of verification of requirements of applications for a
job opening.

**Acceptance Criteria:**

- 1015.1. The verification must only be done to candidates which requirements file has already been submitted.

**Dependencies/References:**

Regarding this requirement, we acknowledge the reference NFR09 of LPROG, which states that support for this
functionality must adhere to specific technical requirements outlined in LPROG. As of 17/04/2024, no additional
requirements have been presented. The ANTLR tool should be used (https://www.antlr.org/).

It is also important to note that this functionality relies on [US 2004](../us_2004/readme.md), as this is the user
story where the completed
requirement specifications are submitted into the system for evaluation using this functionality.

**Client Clarifications:**

> **Question:** Should the process be carried out for all candidates or just for some (according to some criteria)?
>
> **Answer:** Files with responses to requirements are gradually entered into the system. Perhaps it would be “simpler”
> for the
> process to run (i.e., check the requirements) for candidates for whom the requirements file has already been
> submitted.
> At some point the process will run with all candidates already having submitted requirements files.

## 3. Analysis

Since the customer manager is responsible for executing this functionality, it must be possible for them to select one
of the job openings associated with them to evaluate the candidates who have applied for that position. Additionally, as
clarified in the acceptance criteria, only the requirements of candidates whose requirements file has already been
submitted should be assessed.

The plugins will evaluate the requirement specifications to determine if the candidate's curriculum meets the
requisites established by the customer/company for the job opening currently under evaluation. If the evaluation
results in the rejection of a candidate, a justification must be provided, explaining the reason for the rejection.

### Domain Model

![Domain Model](domain-model.svg)

## 4. Design

### 4.1. Realization

![Sequence Diagram](sequence-diagram.svg)

**Ref1:** Check the partial sequence diagram
in [team-decisions](../../team-decisions/team-decisions.md#shared-sequence-diagrams) to see the adopted behaviour.

### 4.2. Class Diagram

![a class diagram]()

### 4.3. Applied Patterns

* **DTO**
* **Repository**
* **Service**
* **MVC**

> **MVC**
>
> **Justification:**
>
> The MVC pattern was employed to divide the system into three distinct parts: model, view, and controller, each
> responsible for a specific aspect of the system’s functionality. This separation of concerns enhances maintainability
> and extensibility, as changes to one part do not require changes to the others.

> **Repository Pattern**
> * JobOpeningRepository
> * ApplicationRepository
>
> **Justification:**
>
> The repositories were used to retrieve the persisted job openings and to save the job opening instance after the
> changes were made.

> **DTO**
>
> **Justification:**
>
> We opted for DTOs due to the significant amount of domain information required for this functionality. Recognizing the
> benefits of encapsulation and layer decoupling offered by DTOs, we concluded that applying this pattern was
> helpful in this context.

> **Service Pattern**
> * AuthorizationService
>
> **Justification:**
>
> The authorization service was employed to verify the roles of the logged-in user. Different services were used to
> get job openings. As for verifying the requirements, no services were used since this is a unique function not
> intended for other use cases.

### 4.4. Tests

*Include here the main tests used to validate the functionality. Focus on how they relate to the acceptance criteria.*

**Test 1:** Verifies that it is not possible to ...

**Refers to Acceptance Criteria:** 1015.1

````
@Test(expected = IllegalArgumentException.class)
public void ensureXxxxYyyy() {
...
}
````

## 5. Implementation

*In this section the team should present, if necessary, some evidencies that the implementation is according to the
design. It should also describe and explain other important artifacts necessary to fully understand the implementation
like, for instance, configuration files.*

*It is also a best practice to include a listing (with a brief summary) of the major commits regarding this
requirement.*

## 6. Integration/Demonstration

In this section the team should describe the efforts realized in order to integrate this functionality with the other
parts/components of the system

It is also important to explain any scripts or instructions required to execute an demonstrate this functionality

## 7. Observations

*This section should be used to include any content that does not fit any of the previous sections.*

*The team should present here, for instance, a critical prespective on the developed work including the analysis of
alternative solutioons or related works*

*The team should include in this section statements/references regarding third party works that were used in the
development this work.*