SET @selected_repairman = 1;


SELECT * FROM kovalyk.repair_cases
WHERE repairman = @selected_repairman;