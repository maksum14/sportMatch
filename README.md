# sport match board
 Live stream football World Cup Score Board.
 Simple library to create, update, delete and observe online football match score.

## Required software
- Java JDK 17
- Maven 3.+


### Tech notes
For the business layer, I split logic across different use cases, such as 'StartMatch, FinishMathc, ..etc',
Instead of database, I used simple ArrayList as in-memory storage, as thread-safe is not a part of the current task.

In my experience, I was not involved in TDD approach, that's why it's not implemented here, I used implementation first.
I followed an approach with a business use cases, where each use case is an isolated flow, which can be maintained/tested independently.

