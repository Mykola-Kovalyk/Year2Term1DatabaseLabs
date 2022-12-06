CREATE VIEW products AS
SELECT  products.maker, products.model, products.type, pcs.price FROM labor_sql.pc as pcs
JOIN labor_sql.product AS products ON products.model =  pcs.model
UNION
SELECT  products.maker, products.model, products.type, laptops.price FROM labor_sql.laptop as laptops
JOIN labor_sql.product AS products ON products.model =  laptops.model
UNION
SELECT  products.maker, products.model, products.type, printers.price FROM labor_sql.printer as printers
JOIN labor_sql.product AS products ON products.model =  printers.model;

SELECT * FROM products WHERE maker = "B";

DROP VIEW products;