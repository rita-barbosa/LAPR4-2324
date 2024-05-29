# US 3003

## 1. Context

This is the first time this US is being worked on. It is related to an action of the Customer Manager.

## 2. Requirements

**US 3003:** As Customer, I want to be notified in my application when the state (phase) of my job openings changes.

### Acceptance Criteria:

- **3003.1.** The system should notify the Customer through their application.
- **3003.2.** Regarding changes that happen when a Customer is not in the app, the system should deliver said notifications to the Customer as soon as he opens the app.
- **3003.3.** The Customer should only receive notifications of Job Openings that belong to them.
- **3003.4.** The system should notify the Customer of all the phases and finally shen the Job Opening is closed.
- **3003.5** The implementation of this functionality must follow a client-server architecture.
- **3003.6** Direct interaction between the client applications (Candidate App and Costumer App) and the database server
  is not allowed.
- **3003.7** The protocol to be used is a TCP (Transmission Control Protocol) based client-server protocol.
- **3003.8** Exchange between applications must follow a very restrict client-server pattern: the client application sends
  one request message, and the server application sends back one response message.
- **3003.9** The client application is required to know (IP address or DNS name) the node where the server application is
  running and the TCP port number where the server application is accepting TCP connections.
- **3003.10** Connected applications exchange messages with the format described in RCOMP's ("Application Protocol").
- **3003.11** Connected applications exchange messages with adequate codes described in RCOMP's ("Application Protocol").
- **3003.12** After establishing connection with the server, the client application must undertake a user authentication
  procedure by sending an AUTH request carrying a username and a password.
- **3003.13** The state referred to is the application state, not the recruitment process one.


### Client Clarifications:

>**Question:** (Partially Related: Same concept) In US 3001 you want the candidate to be notified in their application when the status of an application changes. How do you want the candidate to be notified? And if the candidate is not running the application, is this notification lost?
>
>**Answer:** The candidate must be notified when their “app” is running. Regarding notifications that “happen” when you are not running the application, it would be interesting if you received them the next time you run the application.

### Dependencies/References:

#### Dependency with US 1020:
> US 3003 is dependent on this US since US 1020 is one of the reasons that the phases will change.

#### Dependency with US 1010:
> US 3003 is dependent on this US since the Customer Manager uses this functionality to change the phases (state) of Job Openings.

## 3. Analysis

**After questioning the client about this functionality several conclusions were reached:**

1. ***All the notifications are to be delivered through the appropriate app.***
2. ***"The candidate(or Customer) must be notified when their “app” is running"***
3. ***"Regarding notifications that “happen” when you are not running the application, it would be interesting if you received them the next time you run the application."***

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

