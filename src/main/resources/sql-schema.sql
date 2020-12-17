CREATE SCHEMA IF NOT EXISTS `ims`;
USE `ims` ;
CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) NULL DEFAULT NULL,
    `surname` VARCHAR(40) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`item` (
	`item_id` INT(11) NOT NULL AUTO_INCREMENT,
	`item_name` VARCHAR(60) DEFAULT NULL,
	`stock` INT DEFAULT NULL,
	`price` DECIMAL (10, 2) DEFAULT NULL,
	PRIMARY KEY (`item_id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`orders` (
	`order_id` INT (11) NOT NULL AUTO_INCREMENT,
	`fk_customer_id` INT (11) NULL DEFAULT NULL,
	`date_placed` DATE NULL DEFAULT NULL,
	`total_price` DECIMAL(10, 2) NULL DEFAULT NULL,
	PRIMARY KEY(`order_id`),
	FOREIGN KEY(`fk_customer_id`) REFERENCES `customers`(`id`)
);