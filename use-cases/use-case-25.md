# USE CASE: 25 Produce a report on all the capital cities in a Region organised by largest population to smallest

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *organisation employee*, I want to *produce a report on all the capital cities in a Region organised by largest population to smallest*.

### Scope

Region.

### Level

Primary task.

### Preconditions

We know the region. Database contains the population size of all capital cities.

### Success End Condition

A Report is available.

###Failed End Condition

No report is produced.

### Primary Actor

Organisation Employee.

### Trigger

A capital city report by region is requested.

## MAIN SUCCESS SCENARIO

1.Organisation Employee Requests capital city report.
2.Organisation Employee captures region name to get capital city information for.
3.Organisation Employee extracts current capital city statistics for all capital cities in a region.
4.Organisation Employee Provides report.

## EXTENSIONS

1. **Region does not exist**:
   - Organisation Employee informs Organisation Employee that that region doesn't exist.

## SUB-VARIATIONS

None.

##SCHEDULE

**DUE DATE**: Release 1.0