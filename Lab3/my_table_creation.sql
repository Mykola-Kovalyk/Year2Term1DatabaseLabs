SET @EXISTED_BEFORE = EXISTS (SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'kovalyk');



CREATE SCHEMA IF NOT EXISTS kovalyk;
USE kovalyk ;

-- manufacturers
-- devices
-- parts
-- repairmen
-- repair_options
-- repair_cases
-- replaced_parts
-- working_hours

CREATE TABLE IF NOT EXISTS kovalyk.manufacturers (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	name VARCHAR(45) NOT NULL,
	PRIMARY KEY (id))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS kovalyk.devices (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	manufacturer INT UNSIGNED NOT NULL,
	serial_number VARCHAR(45) NOT NULL,
	PRIMARY KEY (id),
	INDEX manufacturer_idx (manufacturer),
	CONSTRAINT devices_manufacturer
		FOREIGN KEY (manufacturer)
		REFERENCES kovalyk.manufacturers (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS kovalyk.parts (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	part_number VARCHAR(45) NOT NULL,
	device INT UNSIGNED NOT NULL,
	manufacturer INT UNSIGNED NOT NULL,
	amount SMALLINT UNSIGNED NOT NULL DEFAULT 0,
	PRIMARY KEY (id),
	INDEX device_id_idx (device),
	INDEX manufacturer_idx (manufacturer),
	CONSTRAINT parts_device
		FOREIGN KEY (device)
		REFERENCES kovalyk.devices (id),
	CONSTRAINT parts_manufacturer
		FOREIGN KEY (manufacturer)
		REFERENCES kovalyk.manufacturers (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS kovalyk.repairmen (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS kovalyk.repair_options (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	device INT UNSIGNED NOT NULL,
	option_name VARCHAR(25) NOT NULL,
	details VARCHAR(200),
    price FLOAT UNSIGNED NOT NULL DEFAULT 0,
	PRIMARY KEY (id),
	INDEX device_id_idx (device ASC),
	CONSTRAINT repair_options_device
		FOREIGN KEY (device)
		REFERENCES kovalyk.devices (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS kovalyk.repair_cases (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	repair_option INT UNSIGNED NOT NULL,
	repairman INT UNSIGNED NOT NULL,
	opened DATETIME NOT NULL,
	closed DATETIME NULL,
	failed TINYINT NOT NULL,
	PRIMARY KEY (id),
	INDEX repair_option_idx (repair_option),
	INDEX repairman_idx (repairman),
	CONSTRAINT repair_cases_repair_option
		FOREIGN KEY (repair_option)
		REFERENCES kovalyk.repair_options (id),
	CONSTRAINT repair_cases_repairman
		FOREIGN KEY (repairman)
		REFERENCES kovalyk.repairmen (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS kovalyk.replaced_parts (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	repair_case INT UNSIGNED NOT NULL,
	replaced_part INT UNSIGNED NOT NULL,
	PRIMARY KEY (id),
	INDEX repair_case_idx (repair_case),
	INDEX replaced_part_idx (replaced_part),
	CONSTRAINT replaced_parts_repair_case
		FOREIGN KEY (repair_case)
		REFERENCES kovalyk.repair_cases (id),
	CONSTRAINT replaced_parts_replaced_part
		FOREIGN KEY (replaced_part)
		REFERENCES kovalyk.parts (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS kovalyk.working_hours (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	repairman INT UNSIGNED NOT NULL,
	day ENUM('Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa', 'Su') NOT NULL,
	start TIME NOT NULL,
	end TIME NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT working_hours_repairman
		FOREIGN KEY (repairman)
		REFERENCES kovalyk.repairmen (id)
) ENGINE = InnoDB;

-- manufacturers
-- devices
-- parts
-- repairmen
-- repair_options
-- repair_cases
-- replaced_parts
-- working_hours

delimiter |
CREATE PROCEDURE InsertValues ()
BEGIN
	IF NOT @EXISTED_BEFORE THEN
    
		INSERT INTO kovalyk.manufacturers (name) VALUES
		("Apple"), ("Samsung"), ("Google"), ("HP"), ("Lenovo"), ("Moto"), ("Nokia"), ("Dell"), ("MSI"), ("Xiaomi");

		INSERT INTO kovalyk.devices (manufacturer, serial_number) VALUES
		(1, "Apple-M1-B29362"),
		(2, "S84629"),
		(3, "G84402"),
		(3, "G84403"),
		(4, "H-P9172"),
		(5, "ncd9276rmvc"),
		(6, "CIEB028"),
		(7, "G0387MV"),
		(8, "CIOEG20"),
		(9, "CMNI28W7");

		INSERT INTO kovalyk.parts (part_number, device, manufacturer, amount) VALUES
		("974VBS0", 3, 10, 0),
		("0VY43W0", 1, 7, 30),
		("V7402CC", 4, 4, 0),
		("08YVAWD", 2, 4, 45),
		("07GWCE2", 3, 8, 0),
		("V0CVYWR", 5, 9, 10),
		("CEWV92Q", 7, 2, 0),
		("C0BWNX0", 6, 3, 70),
		("C0WH832", 10, 5, 20),
		("CBAW9EY", 8, 6, 0),
		("CBQ9GA6", 9, 1, 10);

		INSERT INTO kovalyk.repairmen (name) VALUES
		("Святослав Лиман"),
		("Володимир Рудий"),
		("В'ячеслав Чорногір"),
		("Олег Руденко"),
		("Ольга Весняна"),
		("Валерій Рожинний"),
		("Роман Мазур"),
		("Руслана Речна"),
		("Маріо Сутужний"),
		("Олег Рима");

		INSERT INTO kovalyk.repair_options (device, option_name, details, price) VALUES
		(1, "Full repair", "", 499),
		(2, "Board check", "", 99),
		(3, "Short circuit repair", "", 99),
		(4, "Full repair", "", 399),
		(4, "Screen replacement", "", 49),
		(5, "Sound repair", "", 59),
		(6, "Fixing backlight", "", 39),
		(7, "Storage repair", "", 149),
		(8, "Full check", "", 499),
		(9, "Full check", "", 699);

		INSERT INTO kovalyk.repair_cases (repair_option, repairman, opened, closed, failed) VALUES 
		(1, 1, '2021-10-02 15:00:00', '2021-10-02 17:00:00', false),
		(2, 2, '2021-10-02 14:00:00', '2021-10-02 18:00:00', false),
		(3, 2, '2021-10-02 15:00:00', '2021-10-02 16:00:00', false),
		(4, 3, '2021-10-02 13:00:00', NULL, false),
		(5, 4, '2021-10-02 14:00:00', '2021-10-02 17:00:00', false),
		(6, 5, '2021-10-02 14:30:00', '2021-10-02 16:00:00', false),
		(7, 8, '2021-10-02 15:00:00', NULL, false),
		(8, 7, '2021-10-02 12:30:00', '2021-10-02 16:00:00', false),
		(9, 6, '2021-10-02 15:00:00', '2021-10-02 15:30:00', false),
		(10, 9, '2021-10-02 12:00:00', NULL, false),
		(10, 10, '2021-10-02 13:30:00', '2021-10-02 15:30:00', false);

		INSERT INTO kovalyk.replaced_parts (repair_case, replaced_part) VALUES 
		(1, 2),
		(2, 4),
		(3, 1),
		(3, 5),
		(4, 3),
		(5, 3),
		(6, 6),
		(7, 8),
		(8, 2),
		(10, 10),
		(11, 3);

		INSERT INTO kovalyk.working_hours (repairman, day, start, end) VALUES
		INSERT INTO kovalyk.manufacturers (name) VALUES
				("Apple"), ("Samsung"), ("Google"), ("HP"), ("Lenovo"), ("Moto"), ("Nokia"), ("Dell"), ("MSI"), ("Xiaomi");

		INSERT INTO kovalyk.devices (manufacturer, serial_number) VALUES
		(1, "Apple-M1-B29362"),
		(2, "S84629"),
		(3, "G84402"),
		(3, "G84403"),
		(4, "H-P9172"),
		(5, "ncd9276rmvc"),
		(6, "CIEB028"),
		(7, "G0387MV"),
		(8, "CIOEG20"),
		(9, "CMNI28W7");

		INSERT INTO kovalyk.parts (part_number, device, manufacturer, amount) VALUES
		("974VBS0", 3, 10, 0),
		("0VY43W0", 1, 7, 30),
		("V7402CC", 4, 4, 0),
		("08YVAWD", 2, 4, 45),
		("07GWCE2", 3, 8, 0),
		("V0CVYWR", 5, 9, 10),
		("CEWV92Q", 7, 2, 0),
		("C0BWNX0", 6, 3, 70),
		("C0WH832", 10, 5, 20),
		("CBAW9EY", 8, 6, 0),
		("CBQ9GA6", 9, 1, 10);

		INSERT INTO kovalyk.repairmen (name) VALUES
		("Святослав Лиман"),
		("Володимир Рудий"),
		("В'ячеслав Чорногір"),
		("Олег Руденко"),
		("Ольга Весняна"),
		("Валерій Рожинний"),
		("Роман Мазур"),
		("Руслана Речна"),
		("Маріо Сутужний"),
		("Олег Рима");

		INSERT INTO kovalyk.repair_options (device, option_name, details, price) VALUES
		(1, "Full repair", "", 499),
		(2, "Board check", "", 99),
		(3, "Short circuit repair", "", 99),
		(4, "Full repair", "", 399),
		(4, "Screen replacement", "", 49),
		(5, "Sound repair", "", 59),
		(6, "Fixing backlight", "", 39),
		(7, "Storage repair", "", 149),
		(8, "Full check", "", 499),
		(9, "Full check", "", 699);

		INSERT INTO kovalyk.repair_cases (repair_option, repairman, opened, closed, failed) VALUES
		(1, 1, '2021-10-02 15:00:00', '2021-10-02 17:00:00', false),
		(2, 2, '2021-10-02 14:00:00', '2021-10-02 18:00:00', false),
		(3, 2, '2021-10-02 15:00:00', '2021-10-02 16:00:00', false),
		(4, 3, '2021-10-02 13:00:00', NULL, false),
		(5, 4, '2021-10-02 14:00:00', '2021-10-02 17:00:00', false),
		(6, 5, '2021-10-02 14:30:00', '2021-10-02 16:00:00', false),
		(7, 8, '2021-10-02 15:00:00', NULL, false),
		(8, 7, '2021-10-02 12:30:00', '2021-10-02 16:00:00', false),
		(9, 6, '2021-10-02 15:00:00', '2021-10-02 15:30:00', false),
		(10, 9, '2021-10-02 12:00:00', NULL, false),
		(10, 10, '2021-10-02 13:30:00', '2021-10-02 15:30:00', false);

		INSERT INTO kovalyk.replaced_parts (repair_case, replaced_part) VALUES
		(1, 2),
		(2, 4),
		(3, 1),
		(3, 5),
		(4, 3),
		(5, 3),
		(6, 6),
		(7, 8),
		(8, 2),
		(10, 10),
		(11, 3);

		INSERT INTO kovalyk.working_hours (repairman, day, start, end) VALUES
		(1, "Mo", '10:00:00', '19:00:00'),
		(1, "Tu", '10:00:00', '19:00:00'),
		(1, "We", '10:00:00', '19:00:00'),
		(1, "Th", '10:00:00', '19:00:00'),
		(1, "Fr", '10:00:00', '19:00:00'),
		(2, "Mo", '10:00:00', '19:00:00'),
		(2, "Tu", '10:00:00', '19:00:00'),
		(2, "We", '10:00:00', '19:00:00'),
		(2, "Th", '10:00:00', '19:00:00'),
		(2, "Fr", '10:00:00', '19:00:00'),
		(3, "Mo", '10:00:00', '19:00:00'),
		(3, "Tu", '10:00:00', '19:00:00'),
		(3, "We", '10:00:00', '19:00:00'),
		(3, "Th", '10:00:00', '19:00:00'),
		(3, "Fr", '10:00:00', '19:00:00'),
		(4, "Mo", '10:00:00', '19:00:00'),
		(4, "Tu", '10:00:00', '19:00:00'),
		(4, "We", '10:00:00', '19:00:00'),
		(4, "Th", '10:00:00', '19:00:00'),
		(4, "Fr", '10:00:00', '19:00:00'),
		(5, "Mo", '10:00:00', '19:00:00'),
		(5, "Tu", '10:00:00', '19:00:00'),
		(5, "We", '10:00:00', '19:00:00'),
		(5, "Th", '10:00:00', '19:00:00'),
		(5, "Fr", '10:00:00', '19:00:00'),
		(6, "Mo", '10:00:00', '19:00:00'),
		(6, "Tu", '10:00:00', '19:00:00'),
		(6, "We", '10:00:00', '19:00:00'),
		(6, "Th", '10:00:00', '19:00:00'),
		(6, "Fr", '10:00:00', '19:00:00'),
		(7, "Mo", '10:00:00', '19:00:00'),
		(7, "Tu", '10:00:00', '19:00:00'),
		(7, "We", '10:00:00', '19:00:00'),
		(7, "Th", '10:00:00', '19:00:00'),
		(7, "Fr", '10:00:00', '19:00:00'),
		(8, "Mo", '10:00:00', '19:00:00'),
		(8, "Tu", '10:00:00', '19:00:00'),
		(8, "We", '10:00:00', '19:00:00'),
		(8, "Th", '10:00:00', '19:00:00'),
		(8, "Fr", '10:00:00', '19:00:00'),
		(9, "Mo", '10:00:00', '19:00:00'),
		(9, "Tu", '10:00:00', '19:00:00'),
		(9, "We", '10:00:00', '19:00:00'),
		(9, "Th", '10:00:00', '19:00:00'),
		(9, "Fr", '10:00:00', '19:00:00'),
		(10, "Mo", '10:00:00', '19:00:00'),
		(10, "Tu", '10:00:00', '19:00:00'),
		(10, "We", '10:00:00', '19:00:00'),
		(10, "Th", '10:00:00', '19:00:00'),
		(10, "Fr", '10:00:00', '19:00:00');
    END IF;
END |

CALL InsertValues;
DROP PROCEDURE InsertValues;
