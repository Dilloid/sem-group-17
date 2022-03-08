# USE CASE: 4 Produce a report on all the countries in a region, organised by largest population to smallest

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *organisation employee*, I want to *produce a report on all the countries in a region, organised by largest population to smallest*.

### Scope

Region.

### Level

Primary task.

### Preconditions

The organisation has information on the population of all the countries in the world, as well as which countries belong to which region.

### Success End Condition

A report on all the countries in a region organised from largest population to smallest is successfully generated.

### Failed End Condition

No report is produced.

### Primary Actor

Organisation Employee.

### Trigger

A request for the population of a region was requested by the organisation.

## MAIN SUCCESS SCENARIO

1. Organisation requests a report on the countries of a region from largest to smallest population.
2. Employee gets a list of all the countries in a specific region.
3. Employee sorts the list from largest population to smallest.
4. The employee delivers the sorted list to their organisation superior.

## EXTENSIONS

1. **Region does not exist**:
   - Organisation is informed that a requested region does not/no longer exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0