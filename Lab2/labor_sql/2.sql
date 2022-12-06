USE labor_sql;

SELECT DISTINCT SUBSTRING(name, LOCATE(' ', name)) as surname FROM labor_sql.passenger
WHERE name NOT LIKE('J%');