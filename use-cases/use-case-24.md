# USE CASE: 24 Produce a report on all the capital cities in a continent organised by largest population to smallest

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *organisation employee*, I want to *produce a report on all the capital cities in a continent organised by largest population to smallest*.

### Scope

Continent.

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

A capital city report by continent is requested.

## MAIN SUCCESS SCENARIO

1. Organisation employee requests capital city report.
2. Organisation employee captures continent name to get capital city information for.
3. Organisation employee extracts current capital city statistics for all capital cities in a continent.
4. Organisation employee provides report.

## EXTENSIONS

1. **Continent does not exist**:
   - Organisation Employee informs Organisation Employee that that continent doesn't exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0