Use Case: #10 The top N populated cities in a country where N is provided by the user
--------------------------------------------------
CHARACTERISTIC INFORMATION
Goal in Context: As product owner, I would like a report generated containing the top N populated cities in a country where N is provided by the user
Scope: Organisation (black-box)
Level: User goal
Preconditions: The database contains the population of the cities and countries
Success End Condition: top N populated cities in a specific country displayed starting with the largest populated
Failed End Condition: None of the correct data is displayed or in the right order
Primary Actor: The user
Trigger: User inputs N and selects country for top N populated cities within the selected country to be displayed
----------------------------------------
MAIN SUCCESS SCENARIO
1. User inputs numerical value (N)
2. User selects country
3. N cities selected
4. N cities displayed organised starting with largest populated city
EXTENSIONS
1. user inputs non-numerical value
    i. message to inform user that N needs to be numerical before allowing them to input N again
--------------------
SUB-VARIATIONS
None
