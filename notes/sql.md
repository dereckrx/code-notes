---
title: SQL
tags: [sql]
---

## sql - what does group by allow you to do?

squash up a table so that each row is condensed and you can perform counts on that individual row -- e.g.

select title, count(actor_id) from movie, casting where movie.id=movieid and yr=1978 group by title;

## sql - get all unique regions (from a countries data table)

select distinct(region) from bbc;

## Coalece

### sql - what does the coalesce func do?

takes any number of arguments and returns the first value that is not null.

  COALESCE(x,y,z) = x if x is not NULL
  
### sql  - how to use coalesce to avoid nulls in a select statement
SELECT name, party
      ,COALESCE(party,'None') as non_null_party;

# output has three cols name, party and non_null_party (party with "none" replacing nulls)
# as third_col_name is necessary

## sql - how many rows are returned by
   
   SELECT * FROM table_name1 CROSS JOIN table_name2;

N (table1 count) * M (table2 count) rows

If the table table_name1 has N rows and the table table_name2 has M rows, the result set will contains N * M rows.

# result set which contain rows consisting of all columns in table table_name1 followed by all columns in table table_name2.

## sql for country names starting with N

SELECT name FROM bbc
  WHERE name LIKE 'N%';

## sql - to use a nested select in the place of from what must change here

select name from (select * from bbc where region = 'Europe');

# add as AS statement

select name from (select * from bbc where region = 'Europe') as europe

## sql - a table has a gdp and a population column - are you allowed to divide one by the other in a select

yes - this works:

select name, gdp/population from bbc where population > 200000000;

## Case

sql - what does this do

SELECT name, population
      CASE WHEN population<1000000 THEN 'small'
            WHEN population<10000000 THEN 'medium'
            ELSE 'large'
       END
  FROM bbc;
  
returns small, medium or big as a third column depending on population size

## Join

## sql - use case of outer joins
ALL customers and their purchases. That is, show even customers who have not purchased anything
