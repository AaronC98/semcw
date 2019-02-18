USE CASE: 16 Produce a Report that shows all the capital cities in a region organised by largest to smallest population.

CHARACTERISTIC INFORMATION

Goal in Context
As a Census Taker, I've been tasked with producing this SQL statement that shows all the capital cities in a
region organised by largest to smallest, so we have adequate information for the new system.

Scope
Company.

Level
Primary task.

Preconditions
Database contains current world information.

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
Census Taker gets the population information of all the capital cities a selected region arranged from largest to smallest
population
Census Taker extracts population information.
Census Taker provides report to organisation.

EXTENSIONS
Population data does not exist:
Team members double checks that they were given the correct parameters, correct region.

SUB-VARIATIONS
None.

SCHEDULE
DUE DATE: Midnight sunday of week 15