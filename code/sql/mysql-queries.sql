

-- Syntax
-- Backticks for table and column identifiers: `Users`, `username`
-- Single quotes for string and date values 'hello world', '2001-01-01 00:00:00' (double quotes are supported, but single is prefered)

-- command line 
-- mysql -u root -p --database=example_app -h 127.0.0.1
-- show databases; show tables; use <database_name>; 

--- Pretty Print result
select * from x\G; 

-- Update Table
ALTER TABLE `Todos` CHANGE payback_start_month payback_start_month int(11) NOT NULL;


UPDATE `Todos`  SET payback_start_month=benefit_start_month + 1, payback_start_year=benefit_start_year 
WHERE payback_start_month IS NULL AND payback_start_year IS NULL;

UPDATE EmploymentRecord SET job_group=NULL WHERE job_group = 'undefined';

ALTER TABLE `TodoLists` DROP COLUMN estimated_total_credit_override;
