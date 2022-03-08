# USE CASE: 24 Produce a report on all the capital cities in a continent organised by largest population to smallest

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *Organisation Employee* I want to produce a report on all the capital cities in a continent organised by largest population to smallest.

### Scope

continent.

### Level

Primary task.

### Preconditions

We know the region. Database contains the population size of all capital cities.

### Success End Condition

A Report is available

###Failed End Condition

No report is produced

### Primary Actor

Organisation Employee

### Trigger

A capital city report by continent is requested

## MAIN SUCCESS SCENARIO

1.Organisation Employee Requests capital city report.
2.Organisation Employee captures Continent name to get capital city information for.
3.Organisation Employee extracts current capital city statistics for all capital cities in a continent.
4.Organisation Employee Provides report.

## EXTENSIONS

3.Continent does not exist.
    i. Organisation Employee informs Organisation Employee that that continent doesn't exist

## SUB-VARIATIONS

None.

##SCHEDULE

DUE DATE: Release 1.0