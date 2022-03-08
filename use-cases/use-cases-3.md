# USE CASE: 3 Generate a report on all the countries in a continent, organised by largest population to smallest

## CHARACTERISTIC INFORMATION

### Goal in Context

As an organisation, we want to generate a report on all the countries in a continent, organised by largest population to smallest

### Scope

Continent.

### Level

Primary task.

### Preconditions

The organisation has information on the population of all the countries in the world, as well as which countries belong to which continent.

### Success End Condition

A report on all the countries in a continent organised from largest population to smallest is successfully generated.

### Failed End Condition

No report is produced.

### Primary Actor

Organisation Employee.

### Trigger

A request for the population of a continent was requested by the organisation.

## MAIN SUCCESS SCENARIO

1. Organisation requests a report on the population of a continent from largest to smallest.
2. Employee gets a list of all the countries in a specific continent.
3. Employee sorts the list from largest population to smallest.
4. The employee delivers the sorted list to their organisation superior.

## EXTENSIONS

1.**Continent does not exist**:
    - Organisation is informed that a requested continent does not/no longer exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0