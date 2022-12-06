
DROP PROCEDURE IF EXISTS select_repairman_by_competence;
delimiter |
CREATE PROCEDURE select_repairman_by_competence(IN device_id INT, IN minimum_repaired_amount INT)
BEGIN
	SELECT kovalyk.repair_cases.repairman as repairman, kovalyk.repair_options.device as device, count(*) as amount_repaired FROM kovalyk.repair_cases
    JOIN kovalyk.repair_options ON kovalyk.repair_options.id = kovalyk.repair_cases.repair_option
    GROUP BY repairman, device
	HAVING device =  device_id AND amount_repaired >= minimum_repaired_amount;
END |

SET @device_id = 9;
SET @minimum_repaired_amount = 0;

CALL select_repairman_by_competence(@device_id, @minimum_repaired_amount);