
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