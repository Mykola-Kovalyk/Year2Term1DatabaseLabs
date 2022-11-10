USE kovalyk;

DROP TRIGGER IF EXISTS WorkingHourForeignKeyCheckInsert;
DELIMITER //
CREATE TRIGGER WorkingHourForeignKeyCheckInsert
AFTER UPDATE
ON kovalyk.working_hours FOR EACH ROW
BEGIN
	DECLARE error_message varchar(255);
	IF NOT (NEW.repairman = ANY (SELECT id FROM kovalyk.repairmen)) THEN
		SET error_message = CONCAT( 'The repairman with id ', NEW.day, ' does not exist.');
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = error_message;
    END IF;
END //
DELIMITER ;

DROP TRIGGER IF EXISTS WorkingHourForeignKeyCheckUpdate;
DELIMITER //
CREATE TRIGGER WorkingHourForeignKeyCheckUpdate
AFTER INSERT
ON kovalyk.working_hours FOR EACH ROW
BEGIN
	DECLARE error_message varchar(255);
	IF NOT (NEW.repairman = ANY (SELECT id FROM kovalyk.repairmen)) THEN
		SET error_message = CONCAT( 'The repairman with id ', NEW.day, ' does not exist.');
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = error_message;
    END IF;
END //
DELIMITER ;


DROP TRIGGER IF EXISTS WorkingHourDayCheckInsert;
DELIMITER //
CREATE TRIGGER WorkingHourDayCheckInsert
AFTER UPDATE
ON kovalyk.working_hours FOR EACH ROW
BEGIN
	DECLARE error_message varchar(255);
	IF  NOT (NEW.day IN ('Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa', 'Su'))  THEN
		SET error_message = CONCAT( 'The day "', NEW.day, '" does not exist.');
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = error_message;
    END IF;
END //
DELIMITER ;

DROP TRIGGER IF EXISTS WorkingHourDayCheckUpdate;
DELIMITER //
CREATE TRIGGER WorkingHourDayCheckUpdate
AFTER INSERT
ON kovalyk.working_hours FOR EACH ROW
BEGIN
	DECLARE error_message varchar(255);
	IF  NOT (NEW.day IN ('Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa', 'Su'))  THEN
		SET error_message = CONCAT( 'The day "', NEW.day, '" does not exist.');
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = error_message;
    END IF;
END //
DELIMITER ;

DROP TRIGGER IF EXISTS DetailCheckInsert;
DELIMITER //
CREATE TRIGGER DetailCheckInsert
AFTER UPDATE
ON kovalyk.replaced_parts FOR EACH ROW
BEGIN
	DECLARE error_message varchar(255);
    DECLARE new_device FLOAT;
	SET new_device := (SELECT device FROM kovalyk.repair_cases WHERE id = NEW.repair_case);
	IF NOT (NEW.replaced_part = ANY (SELECT id FROM kovalyk.parts WHERE device = new_device)) THEN
        SET error_message = CONCAT('The part ', NEW.replaced_part, ' does not fit to the device with id ', new_device);
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = error_message;
    END IF;
END //
DELIMITER ;

DROP TRIGGER IF EXISTS DetailCheckUpdate;
DELIMITER //
CREATE TRIGGER DetailCheckUpdate
AFTER INSERT
ON kovalyk.replaced_parts FOR EACH ROW
BEGIN
	DECLARE error_message varchar(255);
    DECLARE new_device FLOAT;
	SET new_device := (SELECT device FROM kovalyk.repair_cases WHERE id = NEW.repair_case);
	IF NOT (NEW.replaced_part = ANY (SELECT id FROM kovalyk.parts WHERE device = new_device)) THEN
        SET error_message = CONCAT('The part ', NEW.replaced_part, ' does not fit to the device with id ', new_device);
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = error_message;
    END IF;
END //
DELIMITER ;




DROP PROCEDURE IF EXISTS AddReplacedPart;
DELIMITER //
CREATE PROCEDURE AddReplacedPart(IN in_repair_case INT, IN in_replaced_part INT)
BEGIN
	INSERT INTO kovalyk.replaced_parts(repair_case, replaced_part) VALUES (in_repair_case,  in_replaced_part);
END //
DELIMITER ; 

DROP PROCEDURE IF EXISTS AddRepairmen;
DELIMITER //
CREATE PROCEDURE AddRepairmen()
BEGIN
	DECLARE x INT DEFAULT 0;

	WHILE x < 10 DO
		INSERT INTO kovalyk.repairmen (name) VALUES (CONCAT("Repairman", x));
		SET x = x + 1;
	END WHILE;
END //
DELIMITER ; 

DROP PROCEDURE IF EXISTS AddSchedule;
DELIMITER //
CREATE PROCEDURE AddSchedule(IN repairman_name varchar(255), IN work_day varchar(5), IN start_time TIME, IN end_time TIME)
BEGIN
	DECLARE x INT DEFAULT 0;
	DECLARE repairman_id INT DEFAULT 0;
    SET repairman_id = (SELECT id FROM kovalyk.repairmen WHERE name = repairman_name);
    
	INSERT INTO kovalyk.working_hours (repairman, day, start, end) VALUES (repairman_id, work_day, start_time, end_time);

END //
DELIMITER ; 

DROP FUNCTION IF EXISTS AverageCost;
DELIMITER //
CREATE FUNCTION AverageCost() RETURNS FLOAT
READS SQL DATA
BEGIN
    RETURN (SELECT AVG(service_cost) FROM kovalyk.repair_cases);
END //
DELIMITER ; 

DROP PROCEDURE IF EXISTS CreatePaymentDatabases;
DELIMITER //
CREATE PROCEDURE CreatePaymentDatabases()
BEGIN
	DECLARE done INT DEFAULT FALSE;
	DECLARE repairman_id INT;
	DECLARE cur CURSOR FOR SELECT id FROM kovalyk.repairmen;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    
    OPEN cur;
    
    create_loop: LOOP
        FETCH cur INTO repairman_id;
		IF done THEN
		  LEAVE create_loop;
		END IF;
        
        SET @create_database_query = CONCAT('CREATE DATABASE `',  repairman_id ,'_payments`');
		PREPARE create_database FROM @create_database_query;
        
        SET @create_table_query = CONCAT('CREATE TABLE `',  repairman_id, '_payments`.payments (
			id INT UNSIGNED NOT NULL AUTO_INCREMENT,
			amount FLOAT NOT NULL DEFAULT 0,
			PRIMARY KEY (id)
			) ENGINE = InnoDB
		');
		PREPARE create_table FROM @create_table_query;
        
		EXECUTE create_database;
        EXECUTE create_table;
	END LOOP;
    
    DEALLOCATE PREPARE create_database;
	DEALLOCATE PREPARE create_table;

	CLOSE cur;
    
END //
DELIMITER ; 
