USE labor_sql;

CREATE VIEW product_view_1 AS
SELECT * FROM labor_sql.product
WHERE model = SOME (SELECT model from labor_sql.pc);

CREATE VIEW product_view_2 AS
SELECT * FROM labor_sql.product
WHERE model = SOME (SELECT model from labor_sql.laptop);

SELECT DISTINCT product_view_1.maker FROM product_view_1
INNER JOIN product_view_2
ON product_view_1.maker = product_view_2.maker;

DROP VIEW product_view_1;
DROP VIEW product_view_2;