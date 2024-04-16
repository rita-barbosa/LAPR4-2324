# US 1005

## 1. Context

It is necessary for the customer manager to ble able to list all the existing job applications.

## 2. Requirements

**US 1005:** As Customer Manager, I want to list all applications for a job opening.


**Dependencies/References:**

- **1005.1.** It's necessary to exist at least one job application.

- **1005.2.** All applications should be displayed, regardless of their status.

- **1005.3.** For each application, the candidate and the status should be identified.


**Dependencies/References:**

This functionality has a dependency on [_US 2002_](../sb_us_2002) since the data to be imported is going to be processed
first by the Application File Bot.


**Client Clarifications:**

> **Question:** Regarding the criteria for listing applications: Should the ongoing applications appear, or the 
> applications made in the past can be included?
> Can any applications appear or only the ones that have been accepted? What information should be displayed for 
> each application?
>
> **Answer:** As stated in the US description, all applications for a job opening should be listed. 
> It makes sense to display all applications, regardless of their status.
> Therefore, for each application, the candidate and the status of their application should be identified.


## 3. Analysis

This functionality is for the Customer Manager, so the user needs to be authenticated first to be able to list the
applications. 

It is necessary to choose a job opening before being able to see the list of the applications, since there may exist
more than one job opening in the system.

### 3.1 System Sequence Diagram

![system sequence diagram](US1005_SSD.svg)


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