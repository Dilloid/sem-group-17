# USE CASE: 10 Produce a report of the top N populated countries in a given continent, where N is provided by the user.

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *organisation employee*, I want to *produce a report of the top N populated countries in a given continent, where N is provided by the user*.

### Scope

Continent.

### Level

Primary task.

### Preconditions

The organisation has the necessary information on all the countries in the world, including which continent each country belongs to.

### Success End Condition

A report is available to be provided to the organisation.

### Failed End Condition

No report is produced.

### Primary Actor

Organisation Employee.

### Trigger

A request is made for a report to be provided.

## MAIN SUCCESS SCENARIO

1. Organisation requests a report on the top N most populated countries in a given continent.
2. Employee takes the number N provided by the requester, as well as the continent to look for.
3. Employee queries the database using the provided N and continent name to generate the report.
4. The employee delivers the report to the organisation.

## EXTENSIONS

1. **Continent doesn't exist**:
   - Employee informs their superior that the continent provided does not exist in the database.
2. **Continent doesn't contain N countries**:
   - Employee lists as many countries as possible, and reports to the requester that there are not enough countries in the continent to provide the top N.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0