USE CASE: 22 Produce a Report on the Population of Cities of a Given Category(Continent/Region/Country/City/District)

CHARACTERISTIC INFORMATION

Goal in Context
As a Census Taker, I've been tasked with producing this SQL statement that shows the results of the
top populated cities in a category provided by a user so we have adequate information for the new system.

Scope
Company.

Level
Primary task.

Preconditions
We know the Category(Continent/Region/Country/City/District) from the user, Database contains current world information.

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
Census taker captures name of the category to get population information from.
Census taker extracts current population information from the given category.
Census taker provides report to organisation.

EXTENSIONS
Region does not exist:
Team members i double checks that they were given the correct category.

SUB-VARIATIONS
None.

SCHEDULE
DUE DATE: Midnight sunday of week 15