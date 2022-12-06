USE labor_sql;

SELECT model, COUNT(model) as count, AVG(price) as avg_price
FROM labor_sql.pc
GROUP BY model
HAVING avg_price < 800;