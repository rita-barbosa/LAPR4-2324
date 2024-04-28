# US 2003

## 1. Context

This is the first time this user story is being requested.

## 2. Requirements

**US 2003** As {Operator}, I want to generate and export a template text file to help collect data fields for candidates
of a job opening (so the data is used to verify the requirements of the job opening).

**Acceptance Criteria:**

- **2003.1** The usage of **_ANTLR_** tool is required.
- **2003.2** The question type are to be one of the established types: True/False, Short Text Answer, Choice with Single-Answer,
  Choice with Multiple-Answer, Integer Number, Decimal Number, Date, Time and Numeric Scale.
- **2003.3** A requirement template must have at least one requirement entry.

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


> **Question:** Could you clarify whether the questions to be used for Interview Models and Requirement Specifications are
> those shown in the documentation examples, or is there a specific set of questions you would like us to use?
>
> **Answer:** The specification document provides examples, as mentioned. These are only indicative examples. You can use
> these examples as test cases and as a starting point to define others. However, the solution is expected to support more
> than just the examples in the document. In either plugin, the types of questions to be supported are those presented on
> page 8 of the document. As the product owner, I would like a functional demonstration of the system to include at least
> 2 plugins of each type, to demonstrate minimal support for more than one plugin used simultaneously (of each type). It
> should also demonstrate the use of all types of questions presented on page 8 (updated on 2024-04-27).



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

The solution for this functionality is to have 4 layers, following DDD development architecture: Presentation, Application,
Domain and Persistence. A link in [references](#71-references) explains this topic in-depth.

To generate a requirements specification template we need a file name and to define questions that define who is eligible
for the position/title in mind.

The questions types are the ones defined in the documentation, and there are no other ones, so no database will be needed
to store these.

In order to enhance encapsulation between layers, the usage of DTO's to the previously mentioned objects should be applied.

**New Domain Layer Classes**
* Question
* QuestionType
* RequirementsTemplate
* RequirementsTemplateManagerService
* RequirementsTemplateEvaluatorService
* RequirementsTemplateBuilder
* QuestionBuilder

**New Persistence Layer Classes**
* `No new classes`

**New Application Layer Classes**
* GenerateRequirementsTemplateFileController

**New Presentation Layer Classes**
* GenerateRequirementsTemplateFileUI

The further topics illustrate and explain this functionality usage flow, and the correlation between its components.

### 4.1. Realization

**US2003 Sequence diagram**

![US2003 Sequence diagram](./US2003_SD/US2003_SD.svg)

### 4.2. Class Diagram

![US2003 Class Diagram](./US2003_Class_Diagram/US2003_class_diagram.svg)

### 4.3 Grammar

The following text represents the grammar that defines the format of the requirement template.

```
grammar RequirementsTemplate;

//Parser rules
start: template;

//Costitution of the template
template: requirement+;

//Costitution of a requirement
requirement : REQUIREMENT_NUMBER NEWLINE question+;

//Types of questions
question: single_choice_question
| multiple_choice_question
| integer_question
| true_false_question
| numeric_scale_question
| decimal_question
| short_answer_question
| date_question
| time_question
;


//Questions Format
single_choice_question: SINGLE_CHOICE WHITESPACE DESCRIPTION_TEXT NEWLINE option+;
multiple_choice_question: MULTIPLE_CHOICE WHITESPACE DESCRIPTION_TEXT NEWLINE option+;
integer_question: INTEGER WHITESPACE DESCRIPTION_TEXT NEWLINE*;
true_false_question: TRUE_FALSE WHITESPACE DESCRIPTION_TEXT NEWLINE*;
numeric_scale_question: NUMERIC_SCALE WHITESPACE DESCRIPTION_TEXT WHITESPACE SCALE_FORMAT NEWLINE*;
decimal_question: DECIMAL WHITESPACE DESCRIPTION_TEXT NEWLINE*;
short_answer_question: SHORT_ANSWER WHITESPACE DESCRIPTION_TEXT NEWLINE*;
date_question: DATE WHITESPACE DESCRIPTION_TEXT WHITESPACE DATE_FORMAT NEWLINE*;
time_question: TIME WHITESPACE DESCRIPTION_TEXT WHITESPACE TIME_FORMAT NEWLINE*;

// Option format
option : OPTION WHITESPACE DESCRIPTION_TEXT NEWLINE+;

//Formatters
OPTION : NUMBER')';
REQUIREMENT_NUMBER : 'REQUIREMENT #'NUMBER;
SCALE_FORMAT : '['NUMBER'-'NUMBER']';
DATE_FORMAT : '[DD/MM/YYYY]';
TIME_FORMAT : '[HH:MM]';
TRUE_FALSE_FORMAT : 'Write "True" or "False".';

//Lexer rules
//Types of questions identifiers
TRUE_FALSE : '[True|False]';
SINGLE_CHOICE : '[Single Choice]';
MULTIPLE_CHOICE : '[Multiple Choice]';
INTEGER : '[Whole Number]';
NUMERIC_SCALE : '[Numeric Scale]';
DECIMAL : '[Decimal Number]';
SHORT_ANSWER : '[Short Answer]';
DATE : '[Date]';
TIME : '[Time]';

WHITESPACE: ' ';
DESCRIPTION_TEXT: '"' (~["\r\n])* '"';
NUMBER: [0-9]+;
NEWLINE: '\r'?'\n';
```

Table of token used in the template file grammar:

| Tokens            | Lexemes              |
|:------------------|:---------------------|
| TRUE_FALSE        | [True/False]         |
| SINGLE_CHOICE     | [Single Choice]      |
| MULTIPLE_CHOICE   | [Multiple Choice]    |
| INTEGER           | [Whole Number]       |
| NUMERIC_SCALE     | [Numeric Scale]      |
| DECIMAL           | [Decimal Number]     |
| SHORT_ANSWER      | [Short Answer]       |
| DATE              | [Date]               |
| TIME              | [Time]               |
| WHITESPACE        | ' '                  |
| DESCRIPTION_TEXT  | '"' (~["\r\n])* '"'  |
| NUMBER            | [0-9]+               |
| NEWLINE           | \r'?'\n              |


### 4.4. Applied Patterns

This topic presents the classes with the patterns applied to them along with justifications.

>**Builder Pattern**
> * RequirementsTemplateBuilder
> * QuestionBuilder
>
> **Justifications**
>
> * A template's structure is not linear, it can vary, in number of questions and its typologies. To face this diversity,
>   a builder is a must.
>
> * A question can have many types, and its structure changes with it, so a builder is necessary to work around the different
>   variations of a question.


>**Service Pattern**
> * RequirementsTemplateEvaluatorService
> * RequirementsTemplateManagerService
>
> **Justifications**
>
> * RequirementsTemplateManagerService is necessary because it manages a set of operations and responsibilities that don't
>   belong to any class. It's in charge of managing the process of creating a requirement specification template file.
>
> * RequirementsTemplateEvaluatorService is necessary because it comprises a set of operations and responsibilities that
>   don't belong to any class. It's in charge of checking if the template generated by the system is formatted according
>   to a certain grammar defined.


### 4.5. Tests

**Test 1:** Grammar verifies correctly formatted requirement is valid.

**Refers to Acceptance Criteria:** 2003.1

````
@Test
public void ensureCorrectFormatIsValid() {
...
}
````

**Test 2:** Grammar verifies incorrect formatted requirement is invalid.

**Refers to Acceptance Criteria:** 2003.1

````
@Test
public void ensureIncorrectFormatIsInvalid() {
...
}
````

**Test 3:** Verifies that is not possible for a question to have a type that is not defined in the system

**Refers to Acceptance Criteria:** 2003.2

````
@Test
public void ensureValidQuestionType() {
...
}
````

**Test 4:** Verifies that is not possible for a template file to not have a requirement entry

**Refers to Acceptance Criteria:** 2003.1 and 2003.3

````
@Test
public void ensureTemplateHasAtLeastOneRequirement() {
...
}
````

> **Note:** Even though the answers to the questions must match their defined types (and tests must be made to ensure those
> scenarios), it is not US2003 responsibility to define the answers to the template and their validity, so those tests won't
> appear in this document.

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


### 7.1 References

* [DDD architecture]( https://ddd-practitioners.com/home/glossary/layered-architecture/#:~:text=In%20Domain%2DDriven%20Design%20(DDD,layer%2C%20and%20an%20infrastructure%20layer. )