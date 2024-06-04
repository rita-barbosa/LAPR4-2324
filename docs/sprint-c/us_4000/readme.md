# US 4000

## 1. Context

This is the first time this user story is being requested.

## 2. Requirements

**US 4000** As a {Customer Manager}, when displaying the candidate data, I expect the system to present a top 20 list of
the most frequently referenced words from files uploaded by a candidate. Additionally, I require a comprehensive list of
the files in which these words appear.

**Acceptance Criteria:**

- **4000.1** The Customer Manager must have access to the candidates' application files data.
- **4000.2** The implementation of the functionality is to be done in JAVA.
- **4000.3** The implementation of the functionality must have threads.
- **4000.4** The implementation of the functionality must have synchronization mechanisms.

**Dependencies/References:**

**US1002 and US1007** | A job opening associated with a recruitment process is required so that the candidates can apply.

**US2000a and US2002** | A candidate must be registered within the system and must have at least one application (with files).

_Reference **4000.1**:_ **NFR14(SCOMP)** - The process to count words of very large files should follow specific technical
requirements such as implementing parallelism and concurrency using Java and threads. Specific requirements will be provided
in SCOMP.


**Client Clarifications:**

> **Question:**  I would like to know if in US4000, regarding the creation of the list of the most common words present 
> in the files uploaded by the candidate, do you intend to choose one application from that candidate and create the respective
> list, or do you intend for this list to be created considering all applications associated with that candidate?
>
> **Answer:** The list of words is related to a particular application.


> **Question:** Is the order of the list important? Does it need to be ordered by the number of occurrences of the words?
>
> **Answer:** Yes, the order is important. The system must present a top 20 list, from the most referenced words to the
> less referenced words.


> **Question:** For the word count, should all types of words be counted, or are there words that don't make sense to
> count, such as conjunctions?
>
> **Answer:** For the moment, there are no restrictions like the one you mention since they depend on the language used 
> in the text (the solution could/would be complex).


> **Question:** When displaying the candidate info, is it expected to show the list of words for each application of the
> candidate, or does the customer manager need to select a specific application to see the list of words?
>
> **Answer:** This regards all the possible applications of a candidate that the customer manager is managing.


> **Question:** Word Count – I think it makes sense to ignore words with less than 3 letters, or something like this,
> because it makes no sense to have words like as, I, am... Can we make this assumption or should we count any word?
>
> **Answer:** If you want, you may use a configuration file to configure the minimum number of sequence of characters to
> be counted as a word in the process. However, this is a temporary exception, accepted, but not considered as a good 
> solution (a good solution should be one that takes into account the used language).


> **Question:** Recently, you answered questions about the functionality concerning the top 20 number of words in the 
> application files of candidates (Q170 and Q187). When analyzing the questions, we were unsure which interpretation to 
> consider: whether the functionality should be applied to all of the candidate's applications (Q187), or if the Customer
> Manager chooses one application from the candidate and then the functionality works only on that application (Q170).
>
> **Answer:** The candidate data refers to the data of a candidate who may have multiple applications. In this context, 
> when presenting the data for each application, the top 20 words should appear for each individual application.


> **Question:** In US4000, it is mentioned, "Additionally, I require a comprehensive list of the files in which these words
> appear." Do you just want us to indicate in which files the word "x" appears, or is there any additional information 
> required?
>
> **Answer:** As you indicated, for each word, specify in which files it appears.

## 3. Analysis

This functionality shows the candidate data and their 20 most used words. This is done by having the system analysing the
application files of the candidate.

According to the functionality specific documentation, two possible solutions are presented:
* A file is the subject of a thread
* A file is the subject of multiple threads

All thread must have the same behaviour for all files.

Below there's a System Sequence Diagram (SSD) illustrating the expected behaviour of this functionality. After this diagram
is a partial domain model, with emphasis on US4000's concepts.

**US4000 System Sequence Diagram**

![system sequence diagram](US4000_SSD/US4000_SSD.svg)

**US4000 Partial Domain Model**

![Partial Domain Model](US4000_Domain_Model/domain-model-us-4000.svg)

## 4. Design

For the envisioned implementation of this user story we assumed the following work flow:

### 4.1. Realization

![Sequence Diagram](US4000_SD/US4000_Sequence_Diagram.svg)

### 4.2. Class Diagram

![a class diagram](US4000_CD/US4000_class_diagram-US4000_Class_Diagram.svg)

### 4.3. Applied Patterns

This topic presents the classes with the patterns applied to them along with justifications.
>**DTO Pattern**
> * JobOpeningDTO
>
> **Justifications**
>
> * The usage of the JobOpeningDTO comes from the fact that we wanted this class to server as one more layer of encapsulation between the UI and the domain classes,
    > and for security reasons, as to avoid someone using the UI to be able to change domain objects that should only be reached using the controller. 
> 
>**Repository Pattern**
> * CustomerRepository
> * JobOpeningRepository
>
> **Justifications**
>
> * As per requested, the job reference that identifies the job opening should have the customer code as a base, and be
    sequential. If the previous job opening from the same customer was made in a different session, then the current session
    does not have access to its job reference, so it must be retrieved from the job openings' repository database.
    The newly created jobOpening instance will be saved/preserved in its repository.
>
> * The customers assigned to the Customer Manager are stored within the CustomerRepository, persisting and rebuilding them
    between sessions.

>**Service Pattern**
> * JobOpeningListDTOService
> * CustomerManagementService
> * JobOpeningManagementService
> * AuthorizationService
> * ThreadService
>
> **Justifications**
>
> * CustomerManagementService is used in more than one functionality, and its in charge of managing request regarding customers,
    >   serving as encapsulation between the controller and the CustomerRepository along with the domain classes.
>
> * JobOpeningManagementService is used in more than one functionality, and its in charge of managing request regarding
    >   jobOpenings, serving as encapsulation between the controller and the JobOpeningRepository along with the domain classes.
>
> * In order to enforce encapsulation amongst layers and adequate responsibility assigment, the JobOpeningListDTOService was
    >   created, besides being a set of instructions that is used in other functionalities.
>
> * To get the customers that are assigned to the current Customer Manager in-session, we must get something to identify them.
    >   The AuthorizationService allows to get the username (user's email), which is essential to then filter the CustomerRepository
    >   to the desired customers. This set of instructions is used in other functionalities too.
> 
> * ThreadService is used in this functionality several times due to the already described design that was chosen for this user story, 
    > not only that but serving as encapsulation between the controller and the threads needed for this functionality.

### 4.4. Tests

**Test 1, 2 and 3: Tests the functionality of the thread.

````
@Test
    public void ensureItCountsCorrectly1() {

        Map<String, Pair<Integer, List<String>>> map = new TreeMap<>();

        org.apache.commons.lang3.tuple.Pair<Integer, List<String>> pair;

        List<String> list = new ArrayList<>();

        list.add("test_file_1.txt");

        pair = org.apache.commons.lang3.tuple.Pair.of(1,list);

        map.put("a", pair);
        map.put("b", pair);
        map.put("c", pair);
        map.put("d", pair);
        map.put("e", pair);
        map.put("f", pair);
        map.put("g", pair);
        map.put("h", pair);
        map.put("i", pair);
        map.put("j", pair);
        map.put("k", pair);
        map.put("l", pair);
        map.put("m", pair);
        map.put("n", pair);
        map.put("o", pair);
        map.put("p", pair);
        map.put("q", pair);
        map.put("r", pair);
        map.put("s", pair);
        map.put("t", pair);

        ApplicationFile file = new ApplicationFile(new File("../scomp/sprintc/us4000/US4000_Test_Files/test_file_1.txt"));

        Set<ApplicationFile> set = new HashSet<>();

        set.add(file);

        assertEquals(map, ApplicationFilesThreadService.getTop20Words(set));
    }
````

````
@Test
    public void ensureItCountsCorrectly2() {

        Map<String, Pair<Integer, List<String>>> map = new TreeMap<>();

        org.apache.commons.lang3.tuple.Pair<Integer, List<String>> pair;

        List<String> list = new ArrayList<>();

        list.add("test_file_2.txt");

        pair = org.apache.commons.lang3.tuple.Pair.of(1,list);

        map.put("a", pair);
        map.put("b", pair);
        map.put("c", pair);
        map.put("d", pair);
        map.put("e", pair);
        map.put("f", pair);
        map.put("g", pair);
        map.put("h", pair);
        map.put("i", pair);
        map.put("j", pair);
        map.put("k", pair);
        map.put("l", pair);
        map.put("m", pair);
        map.put("n", pair);
        map.put("o", pair);
        map.put("p", pair);
        map.put("q", pair);
        map.put("r", pair);
        map.put("s", pair);
        map.put("t", pair);

        ApplicationFile file = new ApplicationFile(new File("../scomp/sprintc/us4000/US4000_Test_Files/test_file_2.txt"));

        Set<ApplicationFile> set = new HashSet<>();

        set.add(file);

        assertEquals(map, ApplicationFilesThreadService.getTop20Words(set));
    }
````

````
@Test
    public void ensureItCountsCorrectly2() {

        Map<String, Pair<Integer, List<String>>> map = new TreeMap<>();

        org.apache.commons.lang3.tuple.Pair<Integer, List<String>> pair;

        List<String> list = new ArrayList<>();

        list.add("test_file_2.txt");

        pair = org.apache.commons.lang3.tuple.Pair.of(1,list);

        map.put("a", pair);
        map.put("b", pair);
        map.put("c", pair);
        map.put("d", pair);
        map.put("e", pair);
        map.put("f", pair);
        map.put("g", pair);
        map.put("h", pair);
        map.put("i", pair);
        map.put("j", pair);
        map.put("k", pair);
        map.put("l", pair);
        map.put("m", pair);
        map.put("n", pair);
        map.put("o", pair);
        map.put("p", pair);
        map.put("q", pair);
        map.put("r", pair);
        map.put("s", pair);
        map.put("t", pair);

        ApplicationFile file = new ApplicationFile(new File("../scomp/sprintc/us4000/US4000_Test_Files/test_file_2.txt"));

        Set<ApplicationFile> set = new HashSet<>();

        set.add(file);

        assertEquals(map, ApplicationFilesThreadService.getTop20Words(set));
    }
````
````
@Test
public void ensureItCountsCorrectly3() {

        Map<String, Pair<Integer, List<String>>> map = new TreeMap<>();

        org.apache.commons.lang3.tuple.Pair<Integer, List<String>> pair, pair1;

        List<String> list = new ArrayList<>();

        list.add("test_file_3.txt");

        pair = org.apache.commons.lang3.tuple.Pair.of(4,list);

        map.put("test", pair);
        map.put("file", pair);
        map.put("made", pair);
        map.put("with", pair);
        map.put("intent", pair);
        map.put("to", pair);
        map.put("check", pair);
        map.put("if", pair);
        map.put("thread", pair);
        map.put("can", pair);
        map.put("count", pair);
        map.put("words", pair);
        map.put("correctly", pair);

        pair1 = org.apache.commons.lang3.tuple.Pair.of(12,list);

        map.put("the", pair1);

        ApplicationFile file = new ApplicationFile(new File("../scomp/sprintc/us4000/US4000_Test_Files/test_file_3.txt"));

        Set<ApplicationFile> set = new HashSet<>();

        set.add(file);

        assertEquals(map, ApplicationFilesThreadService.getTop20Words(set));
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