# Team Decisions

## Technical Decisions

### Project Structure

The team opted to use the Eapli framework and project for establishing the project's initial structure.
Furthermore, we'll integrate the existing code for UIs and authentication/authorization into the applications. However,
this latter aspect will undergo modifications to align with the client's requirements and business domain.

### Organization

The team reached a unanimous decision to enhance organization by adding tags to the beginning of the user stories
folders, indicating the sprint they belong to. However, user stories that are addressed globally throughout the
project's duration will not need this additional identification.

**Example:**

* For Sprint B US's: _sb_us_1000_
* For SprintC US's: _sc_us_1000_
* For Global US's: _us_g007_

### TDD

Given the necessity of implementing test-driven development within our project, following a discussion with our EAPLI
professor, we collectively decided to formulate a plan for the tests to be conducted. This choice primarily stems from
the product owner's insistence that the project on GitHub must consistently compile error-free, alongside the
requirement for a workflow that incorporates test execution. It's clear that without a thoroughly completed
implementation, the development of tests becomes impractical. Thus, this realization guided us to the previously
mentioned
decision.

Despite adhering to this methodology, all team members are responsible for delineating the required tests. This involves
specifying the test class, its objectives, and the corresponding acceptance criteria it addresses.

## Association Between Entities

#### CustomerManager - Entity

We concluded that the Entity/Customer should possess an attribute referring to a CustomerManager, in the event that it
represents a user. We considered two scenarios:

1. The CustomerManager maintains a list of Entities under its responsibility.
2. The Entity/Customer possesses a reference to a CustomerManager (user).

Nevertheless, after careful consideration, we determined that establishing this association was more helpful from
our perspective. Creating individual user instances incurred significant costs for the system. Hence, we believe it is
preferable to undertake additional steps to identify the entity associated with a CustomerManager.

#### CustomerUser - Entity

To ensure consistency in decision-making, we opted for the entity/customer to include the corresponding user as an
instance.

### Shared sequence diagrams

To avoid redundant design implementations and decisions, the team resolved to create diagrams that outline the program
flow for specific topics.

#### Repository

The subsequent design is consistently employed whenever a repository is required. Hence, it is understood that when a
repository is utilized in the user stories diagram, the following instructions will also be executed.

![Repository](shared-sequence-diagram/sequence-diagram-repositories.svg)

### Plugin

After discussing with our LPROG and EAPLI teacher, the team decided that the system will actually only have one plugin.
This plugin will be designed to support both requirement specifications and interview models, and it will be capable of
providing a template file as well as evaluating the models. To achieve this, several configuration files will be
required, each specific to a type of model and its necessary information. These files will be essential for loading data
into our symbol table, which will then be used to evaluate the models or generate the template text file.

This decision was made after extensive discussions with our LPROG teacher about the plugin's development. Despite
considering the client's input, the teacher emphasized that this was the best approach. Furthermore, after confirming
with our EAPLI teacher that this approach was acceptable, the team decided to adopt it.