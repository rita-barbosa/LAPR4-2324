# US 1011

## 1. Context

When a job opening is created, we need to select the requirements, the recruitment process and the interview model. This US allow us to select the interview model to the job opening.

## 2. Requirements

**US 1011:** As Customer Manager, I want to select the interview model to use for the interviews of a job opening (for their evaluation/grading)

**Acceptance Criteria:**

- **1011.1.** The system should have at least one interview model created.

**Dependencies/References:**

This functionality has a dependency on [_US 1008_](../sb_us_1008) that consists in deploying and configure a plugin (Job Requirement Specification or Interview Model) to be used in the system.

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