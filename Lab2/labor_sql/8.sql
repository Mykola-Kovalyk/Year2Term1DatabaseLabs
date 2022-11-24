SELECT DISTINCT primary_table.maker, 
(SELECT COUNT(*) FROM labor_sql.product as temp_table WHERE type = "PC" AND temp_table.maker = primary_table.maker) as pc_count,
(SELECT COUNT(*) FROM labor_sql.product as temp_table WHERE type = "Laptop" AND temp_table.maker = primary_table.maker) as laptop_count,
(SELECT COUNT(*) FROM labor_sql.product as temp_table WHERE type = "Printer" AND temp_table.maker = primary_table.maker) as printer_count
FROM labor_sql.product as primary_table;