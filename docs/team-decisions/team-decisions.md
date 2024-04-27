## Team Decisions

### Association Between Entities

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