# US 2003

## 1. Context

This is the first time this user story is being requested.

## 2. Requirements

**US 2003** As {Operator}, I want to generate and export a template text file to help collect data fields for candidates
of a job opening (so the data is used to verify the requirements of the job opening).

**Acceptance Criteria:**

- **2003.1** The usage of **_ANTLR_** tool is required.

**Dependencies/References:**

This functionality has no direct dependencies, although it is a file generated from this user story that will be used in
the registration of a job opening, regarding its requirements specifications.

_Reference **2003.1**:_ **NFR09(LPROG)** - Requirement Specifications and Interview Models: The support for this functionality
must follow specific technical requirements, specified in LPROG. The _**ANTLR**_ tool should be used (https://www.antlr.org/).


**Client Clarifications:**

> **Question:** What fields/information need to be extracted from the candidate to verify the requirements of a job opening?
>
> **Answer:** It will depend on what is designed/specified in the Requirements Specification model used for that job opening.


> **Question:** Who will fill in the answers in the template file?
>
> **Answer:** The Operator will fill in the answers, and as part of US2004, they will submit the completed file in the system.


> **Question:** After the applications are filtered during the screening process, I'm unsure about who manages the results
> and oversees this phase. Could you please clarify if the responsibility for managing the screening results falls under
> the customer manager, the operators, or both?
>
> **Answer:** In US2003 and US2004, it is the Operator who downloads a template file to register the requirements,
> records the answers for the requirements in the file, and then uploads the file. Subsequently, the Customer Manager
> executes the verification process (US1015) and performs the notification of the results (US1016).


> **Question:** Can you clarify the usage of ANTLR within user story 2003? You've stated that US2003 simply generates the
> file, while in US2004 the Operator defines the answers and uploads the file. Where is this file uploaded to? Given this,
> where is the usage of ANTLR in US2003 directed to?
>
> **Answer:** Regarding the first question, it is possible to generate the template text file using ANTLR, although this
> might be challenging. This would be where the usage of ANTLR comes into play. However, unless there is a specific requirement
> from LPROG for evaluation purposes, it is acceptable to have the template file hardcoded in the plugin without the need for
> any complex generation process or function. Regarding the second question, the file is uploaded to the system. The response
> to the last question was provided earlier.


## 3. Analysis

This functionality aims generate and export a template text file with the requirements to be evaluated and the possible
answers for each requirement, regarding a job opening.

Further requirements can be delineated as needed, such as:
* Number of years of experience
* Degree
* Candidate personal qualities
* Programming languages proficiency
* Specific software proficiency
* Specific knowledge
* Work methodology proficiency
* Others...

Requirements can have different natures:

* **True/False** - A question with only a true or false answer.
* **Short Text Answer** - A question with a short text answer. The limit of the answer should the specified by a regular expression.
* **Choice, with Single-Answer** - A question with a set of choices where only one can be selected
* **Choice, with Multiple-Answer** - A question with a set of choices where many can be selected
* **Integer Number** - A question which answer is an integer number
* **Decimal Number** - A question which answer is a decimal number
* **Date** - A question which answer is a date
* **Time** - A question which answer is a time
* **Numeric Scale** - A question which answer is one option in a range of integers (ex: 1-5)

The answers to this template are to be defined in US2004 and then uploaded to the system, out of scope for this functionality.

Below there's a System Sequence Diagram (SSD) illustrating the expected behaviour of this functionality. After this diagram
is a partial domain model, with emphasis on US2003's concepts.

**US2003 System Sequence Diagram**

![system sequence diagram](./US2003_SSD/US2003_SSD.svg)


**US2003 Domain Model**

![partial domain model](./US2003_Domain_Model/domain-model-us-2003.svg)

## 4. Design

*In this sections, the team should present the solution design that was adopted to solve the requirement. This should
include, at least, a diagram of the realization of the functionality (e.g., sequence diagram), a class diagram (
presenting the classes that support the functionality), the identification and rational behind the applied design
patterns and the specification of the main tests used to validade the functionality.*

### 4.1. Realization



### 4.2. Class Diagram

![a class diagram]()

### 4.3 Grammar

### 4.4. Applied Patterns

### 4.5. Tests

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