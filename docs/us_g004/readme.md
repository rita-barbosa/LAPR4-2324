# US G004

## 1. Context

The user story G004 is first presented in Sprint A.

## 2. Requirements

>*In this section you should present the functionality that is being developed, how do you understand it, as well as possible correlations to other requirements (i.e., dependencies). You should also add acceptance criteria.*

**G004** As Project Manager, I want the team to setup a continuous integration server.

**Acceptance Criteria:**

- G004.1. GitHub Actions/Workflows should be used.

**Dependencies/References:**

> *Regarding this requirement we understand that it relates to...*

## 3. Analysis

In order to implement a continuous integration server, it is necessary to understand the concept.

Continuous Integration (CI) is a practice targeting frequent commits to a shared repository.
It helps to detect errors soner, taking less time for the developers to debug code, and makes it easier to merge changes from different team members.

A Continuous Integration Server allows for a creation of an environment that builds and tests new code commits in the repository, instead of doing it locally.

This helps to speed up the development process and saves time for developers.
It is all possible due to the automatization of building, testing and linting processes (amongst many others) - **actions** that are frequently done upon a certain **event** like the _push_ of a commit.


* **Workflows**

Workflows are sets of actions that are frequently done within a project's context. In CI terminology, these are declared in **jobs**, with define steps/phases within the workflow.

Let's see an example:

![workflow_exemplo](workflow%20exemplo%20bg.svg)

In this picture, we can see a simple workflow constituted from a single job of 3 actions. This job's aim is to fetch the repository, set up the JDK (JAVA Development Kit) used
and build the project with Maven.

It's a simple sequence of steps that gets repeated everytime a team member does push to a commit. It might not seem much, but once the commits start to get
more and more frequent, going through the whole process again countless times gets monotonous and takes time away from coding.

That's why it is beneficial to break down the process into small actions/steps and automate each one. Therefore, everytime a team member does push,
the workflow will be triggered, and its jobs will start executing.

> By default, jobs run in parallel. To run them sequentially, dependencies amongst them must be declared, using the **need:** tag.


* **YAML**

The continuous integration file is written is YAML, a human-friendly language, used for data serialization that has become popular in declarative automation and other configuration files.

Below there is a simple example demonstrating the syntax of a YAML file of a workflow. As it can be noticed, some of the terms that were mentioned in this topic will be seen.

The comments in the following code clarify some of its aspects.

```
name: Set up JDK	# name of the workflow

on:     # triggering events
  push:
    branches:     # push will trigger workflow when it targets the defined branches
      - master
      - main

jobs:   # after this block, jobs will indicate the different phases of the automated process
  build:    # name of the 1st jobs

    runs-on: ubuntu-latest      # virtual machine that will execute each step

    steps:      # different components of the job
    - uses: actions/checkout@v3     # this action, defined in another repository, will fetch the main's branch
      with:                         # complete history within the repository
        ref: 'main'
        fetch-depth: 0

- name: Set up JDK 11   				# name of the step
      uses: actions/setup-java@v3		# this action, defined in another repository, will install the JDK and the
      with:								# specified java version and distribution
        java-version: '11'
        distribution: 'temurin'
````

With this knowledge, it is possible to analyse and automate processes of the team's workflow to save time.


## 4. Design

>*In this sections, the team should present the solution design that was adopted to solve the requirement.*
> 
>*This should include, at least, a diagram of the realization of the functionality (e.g., sequence diagram), a class diagram (presenting the classes that support the functionality), the identification and rational behind the applied design patterns and the specification of the main tests used to validade the functionality.*

The following topics describe the solution to the opposing problem and justifications.

### 4.1. Realization

After reflecting on the team workflow, certain steps were found to be frequent and time-consuming:
* Fetching the repository's branch history
* Installing the chosen JDK
* Building the project with Maven
  * compiling
    * dependencies
  * testing
  * packaging
* artifact building (JAR file)

These small processes must be automated so that, when a triggering event is noted, the developer does not need to spend time with them.

### 4.2. Class Diagram

![a class diagram](class-diagram-01.svg "A Class Diagram")

### 4.3. Applied Patterns



### 4.4. Tests

>Include here the main tests used to validate the functionality. Focus on how they relate to the acceptance criteria.

[//]: # (**Test 1:** *Verifies that it is not possible to ...*)

[//]: # ()
[//]: # (**Refers to Acceptance Criteria:** G002.1)

[//]: # ()
[//]: # ()
[//]: # (```)

[//]: # (@Test&#40;expected = IllegalArgumentException.class&#41;)

[//]: # (public void ensureXxxxYyyy&#40;&#41; {)

[//]: # (	...)

[//]: # (})

[//]: # (````)

The testing phase of functionality does not use the JUnit framework. Instead, the testing is made committing code and checking
the response of GitHub and the active workflows and its phases.

**Show images of a successful commit and an unsuccessful commit status on GitHub and Workflow stages**

## 5. Implementation

>*In this section the team should present, if necessary, some evidencies that the implementation is according to the design. It should also describe and explain other important artifacts necessary to fully understand the implementation like, for instance, configuration files.*
>
>*It is also a best practice to include a listing (with a brief summary) of the major commits regarding this requirement.*

The main code to implement this feature is included on the YAML file of the project.

* **Dependency Managing**

`cache: 'maven'`

* **Building Project with Maven**

```
  - name: Build with Maven
    run: mvn --batch-mode --update-snapshots verify
````

* Dependency between Jobs

`needs: test_and_build # Dependency on 1st job
`

* Upload of Artifacts
* JAR file
  ```
    - uses: actions/upload-artifact@v4
      with:
        name: sem4pi_23_24_2dg2_jar
        path: artifacts
  ````
  * Test and Compilation Reports
    ```
      - uses: actions/upload-artifact@v4
        with:
          name: surefire-reports
          path: target/surefire-reports
    ````


* **Commit List**

Below there is a listing of the major commits of this functionality.

| Commit | Description |
|:------:|:-----------:|
|        |             |


## 6. Integration/Demonstration

This functionality is automatically triggered when a team member makes the push of a commit. The commit will only go through
once all the actions and steps of the defined workflow are successfully executed, inciting the quality of the code.

## 7. Observations

### References

Below is a listing of third party works and website pages referred to development of this user story.

* [YAML Official Site](https://yaml.org/)
* [YAML Wikipedia Page](https://en.wikipedia.org/wiki/YAML)
* [Building and testing Java with Maven](https://docs.github.com/pt/actions/automating-builds-and-tests/building-and-testing-java-with-maven)
* [setup-java Action Repository](https://github.com/actions/setup-java)
* [upload-artifact Action Repository](https://github.com/actions/upload-artifact)