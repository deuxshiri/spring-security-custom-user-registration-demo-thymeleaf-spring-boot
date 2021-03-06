CREATE DATABASE IF NOT EXISTS `spring_security_custom_user_demo`;

USE `spring_security_custom_user_demo`;

SET FOREIGN_KEY_CHECKS = 0;

--
-- Table structure for a table `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `id`         INT(11)     NOT NULL AUTO_INCREMENT,
    `username`   VARCHAR(50) NOT NULL,
    `password`   CHAR(80)    NOT NULL,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name`  VARCHAR(50) NOT NULL,
    `email`      VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;

--
-- Dumping data for table `user`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: https://www.bcryptcalculator.com
--
-- Default passwords here are: test23
--

INSERT INTO `user` (username, password, first_name, last_name, email)
VALUES ('jobs', '$2a$10$zNT9t7luizGf9krtDIiRe.dI6gtzrepKUKMwuqGeVVbUYck7n6Iim', 'Steve', 'Jobs', 'jobs@apple.com'),
       ('gates', '$2a$10$zNT9t7luizGf9krtDIiRe.dI6gtzrepKUKMwuqGeVVbUYck7n6Iim', 'Bill', 'Gates', 'gates@outlook.com'),
       ('deuxshiri', '$2a$10$zNT9t7luizGf9krtDIiRe.dI6gtzrepKUKMwuqGeVVbUYck7n6Iim', 'Sina', 'Deuxshiri',
        'deuxshiri@gmail.com');

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role`
(
    `id`   INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;

--
-- Dumping data for table `roles`
--

INSERT INTO `role` (name)
VALUES ('ROLE_EMPLOYEE'),
       ('ROLE_MANAGER'),
       ('ROLE_ADMIN');

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles`
(
    `user_id` int(11) NOT NULL,
    `role_id` int(11) NOT NULL,

    PRIMARY KEY (`user_id`, `role_id`),

    KEY `FK_ROLE_idx` (`role_id`),

    CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,

    CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

SET FOREIGN_KEY_CHECKS = 1;

--
-- Dumping data for a table `users_roles`
--

INSERT INTO `users_roles` (user_id, role_id)
VALUES (1, 1),
       (2, 1),
       (2, 2),
       (3, 1),
       (3, 2),
       (3, 3)
