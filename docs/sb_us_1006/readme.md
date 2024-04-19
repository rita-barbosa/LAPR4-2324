# US 1006

## 1. Context

This is the first time this US is being worked on. It is related to an action of the Customer Manager.

## 2. Requirements

**US 1006:** As Customer Manager, I want to display all the personal data of a candidate.

**Acceptance Criteria:**

- **1006.1.** The system should not try to show candidate data of a candidate that does not have data. If such a situation were to happen the system should return an error and inform the user of what occurred.


- **1006.2.** The system should show the name, phone number and email address of the candidate. In case one of the parameters does not have information it should show the message **"NO DATA FOUND"**. 

**Dependencies/References:**

In US1006 it is asked of us to display all the personal information related to a chosen candidate, in this case, we consider personal information to be all the details in the candidature that relate to only himself, not the applications.

***Dependency with US 1006b:*** This user story is dependent on US 1006 as it will use the same system, only show more data.

***Dependency with US 2000a:*** To be able to choose the preferred candidate it must exist in the system, so this user story is essential.

***Dependency with US 2000c:*** To be able to choose the preferred candidate it will show the user all the candidates available to be chosen.

## 3. Analysis

*In this section, the team should report the study/analysis/comparison that was done in order to take the best design
decisions for the requirement. This section should also include supporting diagrams/artifacts (such as domain model; use
case diagrams, etc.)*

## 4. Design

*In this sections, the team should present the solution design that was adopted to solve the requirement. This should
include, at least, a diagram of the realization of the functionality (e.g., sequence diagram), a class diagram (
presenting the classes that support the functionality), the identification and rational behind the applied design
patterns and the specification of the main tests used to validade the functionality.*

### 4.1. Realization

### 4.2. Class Diagram

![a class diagram]()

### 4.3. Applied Patterns

### 4.4. Tests

*Include here the main tests used to validate the functionality. Focus on how they relate to the acceptance criteria.*

**Test 1:** Verifies that it is not possible to ...

**Refers to Acceptance Criteria:** G002.1

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

*It is also a best practice to include a listing (with a brief summary) of the major commits regarding this requirement.*

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