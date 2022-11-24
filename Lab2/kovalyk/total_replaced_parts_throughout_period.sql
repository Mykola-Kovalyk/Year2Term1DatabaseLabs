USE kovalyk ;

DROP PROCEDURE IF EXISTS total_replaced_parts_throughout_period;
delimiter |
CREATE PROCEDURE total_replaced_parts_throughout_period(IN start_time DATETIME, IN end_time DATETIME)
BEGIN

	SELECT replaced_part, COUNT(*) as amount_replaced FROM kovalyk.replaced_parts
	JOIN kovalyk.repair_cases ON kovalyk.repair_cases.id = kovalyk.replaced_parts.repair_case
	WHERE kovalyk.repair_cases.closed IS NOT NULL AND start_time <= kovalyk.repair_cases.opened AND end_time >= kovalyk.repair_cases.closed
	GROUP BY replaced_part;
END |

CALL total_replaced_parts_throughout_period(CAST('2021-10-02 12:25:30' AS DATETIME), CAST('2021-10-02 17:35:30' AS DATETIME));