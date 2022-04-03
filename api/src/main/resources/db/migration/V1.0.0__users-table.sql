-- create users table structure
CREATE TABLE `users` (
`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
`username` VARCHAR(255) NOT NULL,
`password` VARCHAR(72) NOT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);

-- add user
INSERT INTO `users` (`username`, `password`) VALUES ('admin', '$2a$12$RdJ/zlFPZSbIysag4surUuJ9DGPZqgcDeYFgzV/RBiYhtN4hIXJ5i');
