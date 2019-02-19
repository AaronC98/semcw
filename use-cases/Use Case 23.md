Use Case: #23 The top N populated capital cities in a region where N is provided by the user
--------------------------------------------------
CHARACTERISTIC INFORMATION
Goal in Context: As product owner, I would like a report generated containing the top N populated capital cities in a specific region where N is provided by the user
Scope: Organisation (black-box)
Level: User goal
Preconditions: Database complete with accurate data on regions and capital cities
Success End Condition: top N populated capital cities within selected region displayed
Failed End Condition: None of the correct data is displayed or the data isn't displayed in the correct order
Primary Actor: The user
Trigger: User input of N value
----------------------------------------
MAIN SUCCESS SCENARIO
1. user inputs numerical value
2. user selects region
3. top N capital cities from region selected
4. top N capital cities displayed
EXTENSIONS
1. user inputs non-numerical value
    i. message to inform user that N needs to be numerical before allowing them to input N again
--------------------
SUB-VARIATIONS
None
