# US 2000b

## 1. Context

*This is the first time this user story is being requested.*

## 2. Requirements

**US 2000b:** As Operator, I want to enable/disable a candidate.

**Acceptance Criteria:**

- 2000b.1. The user should be registered as a operator or admin to be able to enable/disable candidates

- 2000b.2. It should ask the user to confirm the operation

- 2000b.3. The candidate data should be showed to the operator


**Client Clarifications**

> **Question:** I would like to know if the client would like two different menus to be created, with each menu responsible for either activating or deactivating candidates.
>
> **Answer:** I have no specific requirements for the UX/UI but I want you to follow best practices

## 3. Analysis

*This functionality is for the Operator, so the user needs to be authenticated first to be able to activate or deactivate a candidate.*

The enable and disable status of the candidate determines their access to the application. If they are disabled, the login process will fail, even if they are a valid candidate.

**System Sequence Diagram:**

![SSD-US-2000b](ssd/ssd-us-2000b.svg)


