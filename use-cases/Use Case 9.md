USE CASE: 9 Produce a Report on the Population of Cities of a Given Region

CHARACTERISTIC INFORMATION

Goal in Context
As a Census Taker, I've been tasked with producing this SQL statement that shows the results of the
top populated cities in a region provided by a user so we have adequate information for the new system.

Scope
Company.

Level
Primary task.

Preconditions
We know the region from the user, Database contains current world information.

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
Census taker captures name of the region to get population information from.
Census taker extracts current population information of all cities of the given region.
Census taker provides report to organisation.

EXTENSIONS
Region does not exist:
Team members informs user that no such region exists and double checks they were given the correct region.

SUB-VARIATIONS
None.

SCHEDULE
DUE DATE: Midnight sunday of week 15