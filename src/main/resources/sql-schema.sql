DROP SCHEMA ims;
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
	PRIMARY KEY(`order_id`),
	CONSTRAINT `fk_customer_id` FOREIGN KEY(`fk_customer_id`) REFERENCES `customers`(`id`)ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `ims`.`orderitems` (
	`orderitems_id` INT(11) NOT NULL AUTO_INCREMENT,
	`fk_order_id` INT (11) NOT NULL,
	`fk_item_id` INT (11) NOT NULL,
	PRIMARY KEY (`orderitems_id`),
	CONSTRAINT `fk_order_id` FOREIGN KEY(`fk_order_id`) REFERENCES `orders`(`order_id`) ON DELETE CASCADE,
	CONSTRAINT `fk_item_id` FOREIGN KEY (`fk_item_id`) REFERENCES `item`(`item_id`) ON DELETE CASCADE
);