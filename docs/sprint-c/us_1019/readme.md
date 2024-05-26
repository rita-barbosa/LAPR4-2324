# US 1019

## 1. Context

It is the first time this task is assigned.

## 2. Requirements

**US 1019** As Customer Manager, I want to get an ordered list of candidates, using the job interview points (grades),
to help me analyze the candidates.

**Acceptance Criteria:**

- **1019.1.** The system should display the candidates based in the interview points.

- **1019.2.** The list should be in descending order, from the highest score to the lowest score.

- **1019.3.** The system should only display the candidates for job openings if they have interviews.


**Dependencies/References:**

- **US G007** - This functionality has a dependency on _US G007_ that pertains to the authentication and authorization
  for all users and functionalities.

- **US 2002** - This functionality has a dependency on _US 2002_ that consists in the creation of applications, since
  needs to exist an application in order to display its information.

- **US 1018** - This functionality has a dependency on _US 1018_ since in this user story the interviews will be evaluated,
  to obtain the grades that will be used.

- **US 1017** - This functionality has a dependency on _US 1017_ that consists in the upload of the text files with the
  candidate responses for an interview.


**Client Clarifications:**

> **Question:** US1019 - Regarding the user story, "US1019 - As Customer Manager, I want to get and ordered list of
> candidates, using the job interview points (grades), to help me analyze the candidates", the list you want is related
> to a job opening, correct? Should the sorting be ascending, or do you want an option to include both ascending and
> descending?
>
>
> **Answer:** Yes, the sorting is related to applications for a job opening. The sorting should be descending, from the
> highest score to the lowest score.


> **Question:** US1019 Clarification - In US1019: As a Customer Manager, I want to get an ordered list of candidates,
> using the job interview points (grades), to help me analyze the candidates. You want me to return an ordered list of
> candidates and their interview grades for a specific job opening of one of my clients. I plan to implement this
> functionality as follows:
> 
> Job Opening: XXX
> 
> Nome | Email | Grade
> 
> Jane Doe| jane@doe.pt | 85
> 
> John Doe| john@doe.pt | 70
> 
> In other words, with descending order.
> As mentioned in Q153, you can see this list in one instance, and in another instance, you can create the ranking you
> find pertinent.
> Does this sound good?
>
> **Answer:** I think you want to refer to Q163. Regarding the example you provided, it seems to meet my requirements.


> **Question:** US1019 - In question Q169 is mentioned that the list should be ordered descending by the interview score
> (as also mentioned in the US itself), however, the question is, how do you envision the ordering if the job opening
> does not include an interview?
> 
> 
> **Answer:** This User Story does not make sense for processes that do not have an interview.


> **Question:** US1019 - According to the response A197, should we then only allow the listing of job openings that
> have an interview?
>
>
> **Answer:** I think I didn't quite understand the reference to the listing of job openings. This US doesn't make sense
> for job openings that don't have an interview, as it is based on listing the candidates and their interview scores.
 

## 3. Analysis

In this functionality, the customer manager needs to select a job opening and then the order list of the candidates will
be displayed. The list should be in descending order, from the candidate with the better interview grade to the worst.


### 3.1 System Sequence Diagram

![system sequence diagram](SSD/US1019_SSD.svg)

### 3.2 Domain Model Related

![domain model related](DM/US1019_DM.svg)


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