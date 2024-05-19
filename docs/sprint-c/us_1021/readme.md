# US 1021

## 1. Context

This is the first time this task is assigned.

## 2. Requirements

**US 1021** As Customer Manager, I want to display all the data of an application.

**Acceptance Criteria:**

- **1021.1.** The system should display the application files.

- **1021.2.** The system should display the data collected or generated during the process, such as interviews and
requirement process.


**Dependencies/References:**

- **US G007** - This functionality has a dependency on _US G007_ that pertains to the authentication and authorization
  for all users and functionalities.

- **US 2002** - This functionality has a dependency on _US 2002_ that consists in the creation of applications, since
  needs to exist an application in order to display its information.


**Client Clarifications:**

> **Question:** US1021 - What is 'all data of an application'? What is a job application?
>
>
> **Answer:** A job application is a candidate's application for a job opening. As for "all data of an application",
> it refers to all the data associated with an application, including the files submitted by the candidate as well as
> the data collected or generated during the process (such as interviews and requirement processing).

> **Question:** US1021 - Regarding US1021, how is chosen the Application to be displayed? Can the user start by choosing
> the Job Reference and then the email of one candidate? Or do you recommend another approach?
>
>
> **Answer:** They should apply the best UX/UI practices. There have been previous questions on similar topics (e.g. Q150).
> Note that there is a user story (US) for listing all applications to a job opening, for example.



## 3. Analysis

In this functionality, the customer manager should be able to select an application and display all the information
related to it. Initially, all applications will be displayed, and the customer manager can choose one to view.
Once an application is selected, all information associated with it will be shown, including files submitted by the
candidate and the data collected or generated during the application process, such as interviews and requirements.

This process should be repeated as many times as the user wishes to continue.


### 3.1 System Sequence Diagram

![system sequence diagram](SSD/US1021_SSD.svg)

### 3.2 Domain Model Related

![domain model related](DM/US1021_DM.svg)

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