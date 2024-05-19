# US 1013

## 1. Context

This is the first time this user story is being requested.

## 2. Requirements

**US 1013** As {Customer Manager}, I want to rank the candidates for a job opening.

**Acceptance Criteria:**

- **1013.1** The Customer Manager must have access to the candidates' application files data.
- **1013.2** The Customer Manager must have access to the candidates' interview score, when applicable.
- **1013.3** The ranking is flexible, meaning that interview scores do not define automatically the candidate's rank.
- **1013.4** The ranking is done by manual sorting.
- **1013.5** No attributed ranking scores are duplicated.
- **1013.6** The system must have global ranking configurations: 1 - exactly the number of vacancies; 2 - double of the
number of vacancies; 0.5 - half the number of the vacancies.
- **1013.7** Candidates that are not ranked must be tagged with something signaling that they do not have a ranking for a
certain recruitment process
- **1013.8** A rank can be edited if none of the interested parties were yet notified of the results.
- **1013.9** All the candidates should be ranked before moving to the result phase.
- **1013.10** It should be possible to have 2 or more instances of the application running to check for the data within
the candidates files.


**Dependencies/References:**

**US1002 and US1007** | A job opening associated with a recruitment process is required so that the candidates can apply.

**US2002** | The candidates must be registered in the system and their application files must be imported, so that the 
Customer Manager can evaluate them and rank the candidates.

**Client Clarifications:**

> **Question:** Regarding section 2.2.1, is it during the Analysis phase that interviews are evaluated, and is this result
> what defines the ranking of candidates? Additionally, what is the role of the CV in this phase? Since interviews are not
> mandatory, what happens if they are not conducted?
>
> **Answer:** The scoring of interviews occurs during the interview phase. The CV and other data (like interview results)
> are used by the Customer Manager in the analysis phase to rank candidates. However, the ordering is the responsibility
> of the Customer Manager (for example, it does not necessarily follow the order of interview scores). US 1013 corresponds
> to manual sorting of candidates by the Customer Manager. The absence of interviews does not impact candidate ranking since
> it does not explicitly depend on interviews.


> **Question:** Mr Client mentioned a manual ranking. If the score of an interview is not essential for the candidate's 
> rank, what criteria is to be used when ranking?
>
> **Answer:** The ranking is a decision of the Customer Manager based on all the data that he/she may have during the process
> (including CV and other attached documents and the interviews as well as other data/information that may not be part of
> the system).


> **Question:** Does the customer manager assign a score to each application, and does the system then order them in ascending
> order, thus assigning the ranking to each application? If so, does the score need to be on a scale? If it is done this way,
> can the manager only assign a score when they have knowledge of all applications? Or can they assign scores gradually,
> and the ranking is only assigned once all applications have been evaluated?
>
> **Answer:** The ordering of candidates (ranking) is the responsibility of the customer manager. They may base it on 
> interview results and other information, but the ranking is not automatic. There is no specific score or scale used.
> The applications are simply ordered.


> **Question:** Is there a limit on rank entries? Let's say that 100 candidates apply for a job opening. Does the Customer
> Manager have to sort all 100 candidates?
>
> **Answer:** The order of candidates should include at least all the first candidates within the vacancy number and some
> following candidates. At the moment, I do not know exactly the number of the following candidates to be ordered. Therefore,
> I would like for it to be a global configuration property in the system. It could be a number representing a magnitude
> from the vacancy number. For instance, 1 could mean exactly the same number of vacancies, 2 the double, 0,5 half the
> number of vacancies. The remainder of the candidates could be just tagged as not ranked.


> **Question:** When a customer manager starts the ranking process, he can stop and continue later? Or the ranking process
> must be done in one go?
>
> **Answer:** I guess it may depend on how you implement the solution. But, in the case it may work as a “long operation” 
> be aware of when and how to conclude the “operation”.


> **Question:** The customer manager can change the rank of a candidate after assigning it?
>
> **Answer:** That should be possible if none of the interested parties were yet notified of the results.


> **Question:** When the customer manager is ranking the candidates, in terms of UI, should we display information from
> the application such as interview score, etc... or just the candidate's name and email?
>
> **Answer:** As stated before, I do not have specific requirements for the UI/UX. Use best practices. However, I would 
> like it to be possible for the Customer Manager to have 2 or more instances of the application running, so that he/she
> could, for instance, see the interviews grades and, at the same time, register the order/ranking of the candidates.


> **Question:** When the analysis phase ends, the ranking need to have all the candidates? or can the customer manager
> rank only some of the candidates?
>
> **Answer:** All the candidates should be ranked before moving to the result phase.


> **Question:** Mr. Client mentioned in Q155 that the system should have ranking configurations so that the Customer Manager
> doesn't have to rank all the candidates for a job opening, and that the ones that haven't been manually ranked are to be
> tagged with "not ranked". However, in Q162, you've said that all the candidates must be ranked before the result phase
> starts. Can you clarify this situation?
>
> **Answer:** The customer manager must evaluate all the candidates. It is the only way he/she can produce a ranking/order
> for the candidates and select the “best” candidates to be included in the vacancies for the job opening. In Q155 I was
> only proposing a way to avoid recording in the system a lot of details that will not have any impact on the next activities.
> The term “not ranked” maybe is not the best. Maybe “rank not recorded” or something similar could be more appropriated.


## 3. Analysis

As clarified by the client, there is no criteria to rank the candidates. The ranking is done manually by the Customer
Manager by looking at each candidate application files and, when applicable, interview score to make a decision.

> Clarify about global settings

Below there's a System Sequence Diagram (SSD) illustrating the expected behaviour of this functionality. After this diagram
is a partial domain model, with emphasis on US1013's concepts.

**US1013 System Sequence Diagram**

![system sequence diagram](./US1013_SSD/US1013_SSD.svg)

**US1013 Partial Domain Model**

![Partial Domain Model](./US1013_Domain_Model/domain-model-us-1013.svg)

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