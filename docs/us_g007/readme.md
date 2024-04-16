# US G007

## 1. Context

This is the first time this user story is being requested.

## 2. Requirements

> **US G007** As a {Project Manager}, I want the system to support and apply authentication and authorization for all
> its
> users and functionalities.

After reviewing the functionality description, it's apparent that an authentication system for existent users needs to
be
developed. Additionally, implementing role-based access control is necessary to ensure that users can only access
authorized functionalities.

**Acceptance Criteria:**

- G007.1. The system necessitates role-based access control, limiting users to accessing functionalities and apps
  corresponding to their roles.

- G007.1. The system requires an authentication process to restrict access solely to registered users.

**Dependencies/References:**

This functionality is deemed a primary dependency within the system, as it stands without dependencies on other
functionalities.

**Client Clarifications:**

> **Question:** Can a user have multiple roles within the system?
>
> **Answer:** Ensuring that individuals have only one means of accessing the system is challenging. For instance, a
> customer manager may also be a potential candidate for a job opening, granting them access to the candidate
> application.
> Regarding internal roles, we should contemplate a hierarchy of access privileges. The admin will have access to all
> backoffice functionalities, followed by the customer manager, and finally, the operator.

> **Question:** Do users access a single application where their credentials determine their access to various
> functionalities, or are there separate applications?
>
> **Answer:** From the Product Owner's perspective, it's more logical to have distinct applications. For instance, even
> if a user is identified as a valid user with the role of Customer, they shouldn't be able to access the Candidate app.

## 3. Analysis

User authentication is essential for accessing and using any other type of functionality.

The system currently must support the following roles:

1. Admin
2. Customer Manager
3. Operator
4. Candidate
5. Customer

The initial three will have access to the back-office application, albeit with certain functionality restrictions
outlined by the client. This entails that the admin will enjoy unrestricted access to all functionalities, followed by
the customer manager, and finally, the operator.
Users assigned the role of candidate will be provided access to the candidate app. Similarly, customers will have
access to the customer app.

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