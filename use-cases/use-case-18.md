# USE CASE: 18 Produce a report on the top N most populated cities in a continent, where N is provided by the user

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *organisation employee*, I want to *Produce a report on the top N most populated cities in a continent, where N is provided by the user*.

### Scope

Continent.

### Level

Primary task.

### Preconditions

need N from user, We know the Continent, Database contains population information of the world.

### Success End Condition

A report is returned.

### Failed End Condition

No report is returned.

### Primary Actor

Organisation Employee.

### Trigger

A continent population report is requested.

## MAIN SUCCESS SCENARIO

1. A report is requested.
2. Employee extracts N form user.
3. Organisation employee captures continent name to get population information for.
4. Employee extracts population information of each city.
5. Employee provides report.

## EXTENSIONS

1. **Continent does not exist**:
   - Organisation Employee informs Organisation Employee that that continent doesn't exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0