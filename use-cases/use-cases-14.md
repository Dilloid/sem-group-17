# USE CASE: 14 Generate a report on all the cities in a region, organised by population from largest to smallest

## CHARACTERISTIC INFORMATION

### Goal in Context

As an organisation, we want to generate a report on all the cities in a region, organised by largest population to smallest.

### Scope

A region.

### Level

Primary task.

### Preconditions

The organisation has information on the population of all the cities in a region.

### Success End Condition

A report on all the cities in a given region organised from largest population to smallest is successfully generated.

### Failed End Condition

No report is produced.

### Primary Actor

Organisation Employee.

### Trigger

A request for the population of the cities of a specific region was requested by the organisation.

## MAIN SUCCESS SCENARIO

1. Organisation requests a report on the cities from a region from largest to smallest population.
2. Employee gets a list of all the cities in the region.
3. Employee sorts the list from largest population to smallest.
4. The employee delivers the sorted list to their organisation superior.

## EXTENSIONS

1.**City does not exist**:
- Organisation is informed that a requested city does not/no longer exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0