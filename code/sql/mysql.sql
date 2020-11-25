-- Syntax
-- Backticks for table and column identifiers: `Users`, `username`
-- Use single quotes for string and date values 'hello world', '2001-01-01 00:00:00' (double quotes are supported, but single is prefered)


-- Update Table
ALTER TABLE `OrderForm` CHANGE payback_start_month payback_start_month int(11) NOT NULL;


UPDATE `OrderForm`  SET payback_start_month=benefit_start_month + 1, payback_start_year=benefit_start_year 
WHERE payback_start_month IS NULL AND payback_start_year IS NULL AND o1.id=o2.id;