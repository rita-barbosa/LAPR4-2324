# US 1010

## 1. Context

This is the first time this US is being worked on. It is related to an action of the Customer Manager.

## 2. Requirements

**US 1010:** As Customer Manager, I want to open or close phases of the process for a job opening.

### Acceptance Criteria:

- **1010.1.** The system should only show Job Openings that have an ongoing recruitment process.
- **1010.2.** The Job Openings should be chosen through their job references.
- **1010.3.** The phases of a Job Opening are to be done in a sequential manner, so closing a phase means opening the next ( Unless it's the results phase. )
- **1010.4.** When the user closes a "Results" Phase, the system should also change the status of the related Recruitment Process to the equivalent of "CLOSED"
- **1010.5.** When the user closes a "Results" Phase, the system should also change the status of the Job Opening, related to this Recruitment Process, to the equivalent of "CLOSED".
- **1010.6.** When moving on to a "Screening" Phase, the system should only let the user start said phase if a requirement specification model has been selected.
- **1010.7.** When moving on to an "Interview" Phase, the system should only let the user start said phase if an interview model has been selected.

### Client Clarifications:

>**Question:** Regarding section 2.2.1 and the phases of the recruitment process, in order to move to the next phase, does the previous one have to close or can we move forward without having the previous one closed?
>
>**Answer:** The short answer is that the phases should be sequential and not overlapping. When one phase closes, the next one opens. US 1007 provides for the definition of phases. US 1010 provides for the opening and closing phases of the process. The Customer Manager's decision to close a phase must assume that the process advances to the next phase automatically (regardless of the dates defined for the phases).

>**Question:** US1007/US1010. According to these US's and Q16, each Job Opening must have defined phases. In questions Q23, Q32 and Q45 the status of the application is mentioned. Are the Job Opening phase and application status separate concepts or do they refer to the same thing?
>
>**Answer:** As previously mentioned, they are related but different concepts.

>**Question:** US 1010 - Open or close phases of the process for a job opening. – When the Customer Manager wants to open or close a recruitment phase, he must have the opportunity to choose which phase he wants to open or close, or he automatically advances to the next phase, that is, he closes the current phase and opens the next one.
>
>**Answer:** Already answered in Q16. But, in short, the idea of this US is to allow progress in the phases of a job opening. The phases must always be sequential. We can consider that closing a phase results in the opening of the next phase (and moving to the next phase means closing the previous one). It should not be possible to “skip” phases, unless phases that are not part of the process (for example, if there are no interviews).

>**Question:** I would like to know what it means to open or close the phases of a job opening, taking into account US1007 the dates have already been defined for each phase.
>
>**Answer:** Please see Q16 (and other questions on the same topic). This US allows the process to change phase (typically to advance the process). Phases have dates but, as mentioned in Q16, we can “activate” a phase even if we are not yet in its time range. The phase dates are “indicative”, in the sense that they suggest, in particular to the Customer Manager, how to manage the process over time. There are “operations” that must happen when the respective phase is “active”.

>**Question:** In the last sprint, the dates on which each phase of a job opening begins were defined, in which it is possible to close and open phases. My question would be the following, if you want to close a phase before the next one begins, is the start of this next phase anticipated? In the scenario that the start date of a phase y has already passed, with x (predecessor) having been closed, if you want to reopen doing x, is it possible? Or should the system prohibit this attempt as it violates the defined dates?
>
>**Answer:** See Q147 and Q16. “Switching” phases to “retreat” must be possible if the phase you wish to “abandon” is not yet, in fact, being “executed/active”. For example, if I'm in the screening phase and have already started checking candidate requirements, it doesn't make sense to be able to “return” to the application phase. But if I was in the application phase and decided to move on to the next one (screening) and after some time I want to return to the previous one (for example, because I made a mistake and I'm still receiving applications), I should be able to do so if I haven't already no “operation/processing” specific to the screening phase was carried out. When it comes to moving forward, a similar principle must be applied: it must be possible to move to the next phase if the previous one is “completed”, for example, I can move on to the interviews if the screening is completed, that is, if all candidates have been verified and notified. Typically/normally, the phases are to advance sequentially.

>**Question:** US 1010 - In US 1010, considering that when we close a phase the next one begins, when we consider the case of reaching the last phase, when we close the phase should we also change the status of the job opening?
>
>**Answer:** A153 See Q151. As for the second question, when the last phase of a process is closed, this process ends, that is, this job opening is no longer “active”.

>**Question:** US1010 - The system for Us1010 must perform validations, for example, the user cannot open the interview phase if the interview model has not yet been defined, or the user can change phase but will not be able, in this case, to execute the interview evaluation process while you do not have an interview specification assigned?
>
>**Answer:** I think you can do that. What you shouldn't do is conduct interviews without having the interview model specified.

>**Question:** US1010 – Should the user choose the phase they want to open or close?
>
>**Answer:** Without wanting to condition the UI/UX, I think that one possibility would be for the system to present the current phase of the process and indicate what is possible to do. If it is possible to move forward or backward, it must indicate that it is possible and the resulting phase. If it is not (yet) possible to move forward/backward, you must indicate the justification for this fact.

### Dependencies/References:

#### Dependency with US 1002:
> US 1010 is dependent on this US since it needs Job Openings to exist for it to find Job Openings with phases that can be started or/and closed.

#### Dependency with US 1007:
> US 1010 is dependent on this US since it needs the phases to be first created and prepared in stand-by to be then started by this US.

## 3. Analysis

**After questioning the client about this functionality several conclusions were reached:**

1. ***"When one phase closes, the next one opens."***
2. ***"The Customer Manager's decision to close a phase must assume that the process advances to the next phase automatically (regardless of the dates defined for the phases)."***
3. ***"The phases must always be sequential. We can consider that closing a phase results in the opening of the next phase (and moving to the next phase means closing the previous one)."***

### System Sequence Diagram Related

![Helpful-System-Sequence-Diagram.png](SSD%2FHelpful-System-Sequence-Diagram.png)

## 4. Design



### 4.1. Realization



#### Sequence Diagram Related



### 4.2. Class Diagram



### 4.3. Applied Patterns



### 4.4. Tests



## 5. Implementation



## 6. Integration/Demonstration

