USE labor_sql;

SELECT maker 
FROM labor_sql.product
JOIN labor_sql.laptop
ON labor_sql.product.model = labor_sql.laptop.model
WHERE speed <= 500;