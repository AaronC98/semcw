USE CASE: 24 Produce a Report that shows the population of people living in cities, and people not living in cities on each continent

CHARACTERISTIC INFORMATION

Goal in Context
As a Census Taker, I've been tasked with producing this SQL statement that shows the population of people living in cities, and people not living in cities in each continent
so we have adequate information for the new system.

Scope
Company.

Level
Primary task.

Preconditions
We know the information we need Category(Continent/Region/Country/City/District) from the user, Database contains current world information.

Success End Condition
A report is available for the team to provide to the organisation.

Failed End Condition
No report is produced.

Primary Actor
Census taker

Trigger
A request for Database information is sent to organisation.

MAIN SUCCESS SCENARIO
Organisation request report for a given role.
Census taker gets the population information of people living in cities, and people not living in cities in each continent.
Census taker extracts population of people living in cities, and people not living in cities in each continent.
Census taker provides report to organisation.

EXTENSIONS
Population data does not exist:
Team members double checks that they were given the correct parameters.

SUB-VARIATIONS
None.

SCHEDULE
DUE DATE: Midnight sunday of week 15