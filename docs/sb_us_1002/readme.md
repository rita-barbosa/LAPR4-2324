# US 1002

## 1. Context

This is the first time this user story is being requested.

## 2. Requirements

**US 1002** As {Customer Manager}, I want to register a job opening.

**Acceptance Criteria:**

- **1002.1** The "Number of Vacancies" must not be less than or equal to 0.
- **1002.2** The job reference is based on a costumer code that must be unique, which is limited to 10 characters, followed by a sequential number.
- **1002.3** Regarding the "Company" field in a job opening, it should be the company/costumer's name, but when storing it within
the database, the costumer code representing said company should be used.
- **1002.4** A job opening is only managed by a single Costumer Manager, the one that is in charge of the company/costumer
of said job opening.
- **1002.5** The job opening must have a title/function.
- **1002.6** The job opening must have a contract type, which must be amongst the types defined.
- **1002.7** The job opening must have a work mode, which must be amongst the types defined.
- **1002.8** The company's address is obligatory in a job opening.
- **1002.9** A 4-stage development process {MVC + persistence} must be done.


**Dependencies/References:**

**US1008** | The Job Requirement Specification plugin configured and deployed in US1008 is essential to associate a set of
requirements to a certain job opening.

 **US2003** | This functionality has a dependency with US2003, which generates and exports a template text file to help collect the
candidates' data based on the job opening specifications/requirements. The data will be used to evaluate the candidate and
check if they are who the company/costumer wishes for.

_Reference **1002.1**:_ Alternatively, this can be achieved by a bootstrap process.


**Client Clarifications:**

> **Question:** In the context where the Customer Manager registers a job opening, how are the requirements for that job offer selected/defined?
>
> **Answer:** The Customer Manager registers the job opening (US 1002) and then typically selects which requirements 
> specification is suitable for that job opening. The requirements specification will be one of those "created" by the language
> engineer and registered in the system.


> **Question:** Regarding the Job Opening (section 2.2.2), the job reference states that it should be generated
> by the system based on a customer code. What is this customer code and are there any rules for its creation?
>
> **Answer:** I would say that every customer must have a unique identifying code, which could be a sort of abbreviation 
> of their name with a limited number of characters. For example, for the customer "Instituto Superior de Engenharia do Porto",
> the customer code could be "ISEP" and no other customer could have this customer code. A reasonable limit might be 8 to 10
> characters; let's define it as 10. This code is manually entered when creating the customer in the system.


> **Question:** Are all fields in the job opening mandatory or are there optional ones?
>
> **Answer:** The fields mentioned in section 2.2.2 are mandatory to fill out. The requirements will be dynamic as they
> depend on the requirements specification selected for that job opening (which is based on a language).


> **Question:** Regarding the job specification, is it the client's responsibility to provide the requirements
> or is it the responsibility of the customer manager? What is the concept of a job specification?
>
> **Answer:** Typically, it will be the client who informs the customer manager of the minimum requirements for a job opening.
> The customer manager checks if there is already a suitable requirements specification available.
> If none exists, with the help of the Language Engineer, a new one is created.


> **Question:** In the job opening (section 2.2.2), for the "company" field, should it be the customer name 
> or the customer code, considering the customer code is unique and manually entered?
>
> **Answer:** The information related to the job opening appearing at the end of page 5 should be seen as something 
> used for job advertisement. In that context, it makes more sense to display the company name (customer name) for the "Company"
> field rather than its code. However, for database storage purposes, the unique code (customer code) may be used.


> **Question:** Does a job opening follows only one interview model?
>
> **Answer:** The Customer Manager selects the interview model to be used in interviews for a job opening. Therefore, there
> will be only one interview model used in the interviews for that job opening.


> **Question:** Does a job opening have only one customer manager?
>
> **Answer:** Yes, typically one customer manager handles all job openings for a client (customer). Consequently, there 
> is only one customer manager for each job opening.


> **Question:** "As Customer Manager, I want to register a job opening", are there any acceptance criteria not yet mentioned
> related to the attributes? Or is it left to the development team's discretion based on best practices and common sense? Some examples:
> - The "Number of Vacancies" must not be less than or equal to 0 or can it be optional;
> - The "Description" should have a character limit or can it be optional.
>
> **Answer:** Regarding whether there are acceptance criteria not mentioned, I will not comment. It's part of the process
> to discover them. I would suggest using more than just common sense.


> **Question:** Are the phases of the Job Opening and the application status separate concepts or do they refer to the same thing?
>
> **Answer:** They are related but are different concepts.


> **Question:** When the Customer Manager registers a job offer, does he create the requirement specifications and the interview
> models or is he given a list of these to select from?
>
> **Answer:** There is US1002, US1009 and US1011. I think it's clear what each one is responsible for. The creation
> of interview templates and requirements is a specific use case with a specific US to register in the system
> the respective plugins (US1008).


## 3. Analysis

To register a job opening, some information must be provided:

* **Job Reference** - based on a unique costumer code followed by a sequential number
* **Title or Function** - indicating the position that people are applying for
* **Contract Type** - one of the defined types (full-time or part-time)
* **Mode** - one of the defined types (remote, hybrid, onsite)
* **Address** - address of the company
* **Company** - costumer Name
* **Number of vacancies** - number of people that will be employed
* **Description** - a brief message from the company
* **Requirements** - the job opening specifications, retrieved from a plugin (Job Requirement Specification Module)

After inserting all this information, the job opening becomes valid and eligible to be part of a recruitment process.

Below there's a System Sequence Diagram (SSD) illustrating the expected behaviour of this functionality. After this diagram
is a partial domain model, with emphasis on US1002's concepts.

**US1002 System Sequence Diagram**

![system sequence diagram](./US1002_SSD/US1002_SSD.svg)
> For now, the selection of requirements and the description are mandatory.

**US1002 Domain Model**

![partial domain model](./US1002_Domain_Model/domain-model-us-1002.svg)

## 4. Design

The solution for this functionality is to have 4 stages, starting with the MVC development architecture.

> * **Model:** represents the application's data and business logic. It manages data storage, implements business rules, and 
responds to data requests from other components like the View and Controller.

> * **Controller:** bridges the Model and the View. It handles user input, updates the Model based on this input, and refreshes
the View to reflect any changes in the Model. Additionally, the Controller encapsulates application logic, such as input
validation and data transformation.

> * **View:** displays data from the Model to users and forwards user inputs to the Controller. It remains passive and doesn't
directly interact with the Model; instead, it receives data from the Model and relays user interactions to the Controller
for processing.

In addition to implementing the MVC architecture, it's essential to incorporate persistence to store and retain application
data. Persistence ensures that all data created during the application's usage is stored in a database, allowing it to 
persist even after the application is closed. This means that when the application is reopened, the same data can be 
retrieved seamlessly, ensuring continuity and preserving user data across sessions.

The further topics illustrate and explain this functionality usage flow, and the correlation between its components.

### 4.1. Realization

**US1002 Sequence Diagram**

[US1002 Split Sequence Diagram]()

### 4.2. Class Diagram

**US1002 Class Diagram** 

![US1002 Class Diagram](./US1002_SD/US1002_SD_split.svg)

> Fazer os SD's das referências


### 4.3. Applied Patterns

The following table justifies the design decisions taken through the applied design patters.

**US1002 Rational**

> DO THE RATIONAL TABLE

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

### 7.1 References

* [MVC architecture](https://www.geeksforgeeks.org/mvc-design-pattern/)