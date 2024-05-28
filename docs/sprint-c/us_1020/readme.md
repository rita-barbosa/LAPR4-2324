# US 1020

## 1. Context

This is the first time this user story is being requested.

## 2. Requirements

**US 1020** As {Customer Manager}, I want to publish the results of the selection of candidates for a job opening, so
that candidates and customer are notified by email of the result.

**Acceptance Criteria:**

- 1020.1. The number of selected applications/candidates should not surpass the number of vacancies for the job opening.

- 1020.2. The system is required to notify both the customer linked to the job opening and the candidate about the
  selected application.

**Dependencies/References:**

We understand that this requirement pertains to reference NFR11, which specifies that the solution should be deployed
across multiple network nodes. Specifically, it is expected that the relational database server and the Follow Up Server
be deployed on nodes separate from localhost, preferably in the cloud. Additionally, the Follow Up Server must handle
e-mail notification tasks in the background.

**Client Clarifications:**

> **Question:** Regarding the sending of email notifications, is it necessary to keep a record of this?
>
>
> **Answer:** The document does not specifically address this matter. However, from Jobs4u's perspective, it seems
> appropriate to have this information recorded.

> **Question:** What is the format for this publication?
>
>
> **Answer:** The publication refers to informing candidates and the client, via email. Candidates who are selected must
> receive an email indicating that they have been selected for their application for the job opening and will be
> contacted
> by the company. As far as the company is concerned, it must receive an email with the list of selected candidates,
> which
> must include the candidate's name and contact details.


> **Question:** Should the email be in English or Portuguese?
>
>
> **Answer:** It can be in Portuguese or English.

> **Question:** Should we assume that the first N candidates in the ranking (where N is the number of job vacancies) are
> chosen, or should we allow the customer manager to select the N candidates?
>
>
> **Answer:** The first option (using the results from US1013).

## 3. Analysis

It is understood that for this functionality, the customer manager must select a job opening to publish the results.
Additionally, the first N candidates, where N is the number of job vacancies, are chosen to fill the positions for
that
job opening.

Furthermore, candidates can only be selected for job openings whose recruitment process is in the
Result phase.

System-wise, after selection, emails will be sent to both the chosen candidates and the customer. The email to the
candidate will inform them that their application was selected and that they will be contacted by the company. For the
company/customer, the email must contain the list of selected candidates, including their names and contact details.

### Domain Model

![Domain Model - US 1020](analysis-diagram/domain-model.svg)

## 4. Design

*In this sections, the team should present the solution design that was adopted to solve the requirement. This should
include, at least, a diagram of the realization of the functionality (e.g., sequence diagram), a class diagram (
presenting the classes that support the functionality), the identification and rational behind the applied design
patterns and the specification of the main tests used to validade the functionality.*

### 4.1. Realization

![Sequence Diagram](design-diagrams/sequence-diagram.svg)
![Sequence Diagram](design-diagrams/sequence-diagram-job-opening-in-result.svg)
![Sequence Diagram](design-diagrams/sequence-diagram-application-accepted.svg)
![Sequence Diagram](design-diagrams/sequence-diagram-job-opening-results-published.svg)

### 4.2. Class Diagram

![a class diagram]()

### 4.3. Applied Patterns

### 4.4. Tests

*Include here the main tests used to validate the functionality. Focus on how they relate to the acceptance criteria.*

**Test 1:** Verifies that it is not possible to ...

**Refers to Acceptance Criteria:** XXX.1

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