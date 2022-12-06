DROP PROCEDURE IF EXISTS select_device_repair_options;
delimiter |
CREATE PROCEDURE select_device_repair_options(IN max_price INT, IN device_id INT)
BEGIN
	SELECT * FROM kovalyk.repair_options
	WHERE device = device_id AND price <= max_price;
END |

CALL select_device_repair_options(200, 4);