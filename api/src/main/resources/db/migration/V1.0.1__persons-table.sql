-- create persons table structure
CREATE TABLE `persons` (
`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
`first_name` VARCHAR(255) NOT NULL,
`last_name` VARCHAR(255) NOT NULL,
`cnp` VARCHAR(13) NOT NULL,
`id_card_serial` VARCHAR(2) NOT NULL,
`id_card_number` VARCHAR(6) NOT NULL,
`address` TEXT NOT NULL,
`birth_date` DATE NOT NULL,
`issuance_date` DATE NOT NULL,
`expiration_date` DATE NOT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);