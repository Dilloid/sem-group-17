# USE CASE: 7 Produce a report of the top N populated countries in the world, where N is provided by the user.

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *organisation*, we want to *generate a report of the top N populated countries in a given region, where N is provided by the user*.

### Scope

Region.

### Level

Primary task.

### Preconditions

The organisation has the necessary information on all the countries in the world, including which region each country belongs to.

### Success End Condition

A report is available to be provided to the organisation.

### Failed End Condition

No report is produced.

### Primary Actor

Employee of the organization.

### Trigger

A request is made for a report to be provided.

## MAIN SUCCESS SCENARIO

1. Organisation requests a report on the top N most populated countries in a given region.
2. Employee takes the number N provided by the requester, as well as the region to look for.
3. Employee queries the database using the provided N and region name to generate the report.
4. The employee delivers the report to the organisation.

## EXTENSIONS

None.

## SUB-VARIATIONS

1. *Region doesn't exist*:
    - Employee informs their superior that the region provided does not exist in the database.
2. *Region contains less than N countries*:
    - Employee lists as many countries as possible, and reports to the requester that there are not enough countries in the region to provide the top N.

## SCHEDULE

**DUE DATE**: Release 1.0