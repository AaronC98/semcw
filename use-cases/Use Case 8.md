Use Case: #25 The top N populated cities in the world where N is provided by the user.
--------------------------------------------------
CHARACTERISTIC INFORMATION
Goal in Context: As a population researcher I want to produce a report on the top N populated cities in the world where N is provided by the user.
Scope: System (white-box)
Level: Summary
Preconditions: The database contains the current population data of every city in the world.
Success End Condition: User can view the top N populated cities in the world where N is provided by the user.
Failed End Condition: The user is not able to view the top N populated cities in the world where N is provided by the user.
Primary Actor: The user.
Trigger: The user requests the ability to view the top N populated capital cities in the world where N is provided by the user.
----------------------------------------
MAIN SUCCESS SCENARIO
1. User requests the ability to view the top N populated cities in the world where N is provided by the user.
2. The amount of records required is captured from the user.
3. A statement is created to capture the amount of cities the user has requested.
3. The result of the statement is provided to the user.
EXTENSIONS
2. The user enters an unacceptable character.
i. Inform the user they must enter a number.

2. The user enters an unacceptable number.
i. Inform the user they must enter a number a suitable number i.e. greater than 1, less than or equal to the number of capital cities in the database.
--------------------
SUB-VARIATIONS
None

SCHEDULE
Due Date: Week 15