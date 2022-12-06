
DROP PROCEDURE IF EXISTS select_free_repairmen;
delimiter |
CREATE PROCEDURE select_free_repairmen(IN cur_time TIME, IN cur_day CHAR(2))
BEGIN
	SELECT DISTINCT repairman FROM kovalyk.repair_cases
	WHERE closed IS NULL AND repairman = ANY (
		SELECT repairman from kovalyk.working_hours 
		WHERE day = cur_day AND 
		cur_time > start AND 
		cur_time < end 
	);
END |

SET @cur_time = CAST('10:30:00' AS time);
SET @day = CONVERT(DAYNAME(NOW()), CHAR(2));

CALL select_free_repairmen(@cur_time, @day);