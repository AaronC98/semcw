Use Case: #6 The top N populated Countries in the World where N is provided by the user
--------------------------------------------------
CHARACTERISTIC INFORMATION
Goal in Context: As the Product owner, I would like to produce a report which displays the top N populated Countries in the world where N is provided by the user
Scope: Organisation (Black-box)
Level: User Goal
Preconditions: Database is complete with correct data for the country populations
Success End Condition: N countries are displayed starting with the largest population
Failed End Condition: None of the correct data is displayed or the data isn't displayed in the correct order
Primary Actor: the user
Trigger: user inputs value (N) to display top N populated countries in the world
----------------------------------------
MAIN SUCCESS SCENARIO
1. user inputs numerical value (N)
2. Number of Countries selected equal to input value
3. Countries are displayed starting with largest population
EXTENSIONS
1. user inputs non-numerical value (N)
    i. message to inform user that N needs to be numerical before allowing them to input N again
--------------------
SUB-VARIATIONS
None
