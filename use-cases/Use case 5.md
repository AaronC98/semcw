Use Case: #5 The top N populated cities in the world where N is provided by the user.
-------------------------------------------------- 
CHARACTERISTIC INFORMATION
Goal in Context: As the Census Taker, I'm going to create an SQL statement that shows the top N populated cities in the world where N is provided by the user.
Scope: Organisation (black-box)
Level: User goal
Preconditions: We know all the cities and their population.
Success End Condition: User can view the top N populated cities in the world where N is provided by the user.
Failed End Condition: The user cannot view the top populated cities in the world.
Primary Actor: The user.
Trigger: The user requests to view all the top populated cities in the world.
 ---------------------------------------- 
MAIN SUCCESS SCENARIO 
1. User requests to view the top N populated cities in the world.
2. Census taker captures N and the population of all the cities in the world.
3. Census taker finds all cities and their population in the world.
4. Census taker provides information to the user.
 ---------------------- 
EXTENSIONS 
3a. N does not exist:
i.	Census taker informs the user that N does not exist.
-------------------- 
SUB-VARIATIONS 
None

SCHEDULE
Due Date: Week 15
