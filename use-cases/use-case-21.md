# USE CASE: 21 Produce a report on the top N most populated cities in a district, where N is provided by the user

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *organisation employee*, I want to *Produce a report on the top N most populated cities in a district, where N is provided by the user*.

### Scope

District.

### Level

Primary task.

### Preconditions

need N from user, We know the district, Database contains population information of the world.

### Success End Condition

A report is returned.

### Failed End Condition

No report is returned.

### Primary Actor

Organisation Employee.

### Trigger

A district population report is requested.

## MAIN SUCCESS SCENARIO

1. A report is requested.
2. Employee extracts N form user.
3. Employee captures district name to get population information for.
4. Employee extracts population information of each city.
5. Employee provides report.

## EXTENSIONS

1. **district does not exist**:
    - Organisation Employee informs Organisation Employee that that district doesn't exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0