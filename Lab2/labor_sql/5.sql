USE labor_sql;

CREATE VIEW printer_makers AS
SELECT DISTINCT maker FROM labor_sql.product
WHERE type = 'Printer';

SELECT DISTINCT labor_sql.product.maker
FROM labor_sql.product
JOIN labor_sql.pc
ON labor_sql.product.model = labor_sql.pc.model
WHERE maker IN (SELECT * from printer_makers) AND type = 'PC'
ORDER BY speed DESC;


DROP VIEW printer_makers;
