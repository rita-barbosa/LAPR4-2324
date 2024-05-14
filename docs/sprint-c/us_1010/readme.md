# US 1010

## 1. Context

This is the first time this US is being worked on. It is related to an action of the Customer Manager.

## 2. Requirements

**US 1010:** As Customer Manager, I want to open or close phases of the process for a job opening.

### Acceptance Criteria:

- **1010.1.** The system should only show Job Openings that have an ongoing recruitment process.
- **1010.2.** The Job Openings should be chosen through their job references.
- **1010.3.** The phases of a Job Opening are to be done in a sequential manner, so closing a phase means opening the next ( Unless it's the results phase. )

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

