CREATE VIEW ships_detailed AS
SELECT name, primary_table.class, type, country, numGuns, bore, displacement, launched  FROM labor_sql.ships as primary_table
JOIN labor_sql.classes as joined_table
ON primary_table.class = joined_table.class;

SELECT name FROM ships_detailed
-- why use CASE when we can just do this
WHERE (
	(numGuns = 9) + 
    (bore = 16) + 
    (displacement = 46000) + 
    (type = 'bb') + 
    (country = 'Japan') + 
    (launched = 1916) +
    (class = 'Revenge')
) >= 3;
-- alright, if you really need CASE, here's the template:
-- CASE <some_value> <operator> <some_other_value> WHEN true THEN 1 ELSE 0 END + <another_case>
-- see how junky it is?

DROP VIEW ships_detailed;