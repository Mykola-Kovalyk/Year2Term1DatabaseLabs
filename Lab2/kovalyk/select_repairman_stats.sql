

DROP PROCEDURE IF EXISTS select_repairman_stats;
delimiter |
CREATE PROCEDURE select_repairman_stats()
BEGIN
	SET @repair_case_count = 0;

	SELECT 
		kovalyk.repairmen.id,
		name,
		@repair_case_count := (SELECT COUNT(*) FROM kovalyk.repair_cases WHERE kovalyk.repair_cases.repairman = kovalyk.repairmen.id AND kovalyk.repair_cases.failed = 0) as repair_case_count,
		((@repair_case_count + 0.0) / ((SELECT COUNT(*) FROM kovalyk.repair_cases WHERE kovalyk.repair_cases.repairman = kovalyk.repairmen.id) + 0.0)) as success_ratio
	FROM kovalyk.repairmen;
END |

CALL select_repairman_stats();