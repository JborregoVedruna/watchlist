-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema watchlist
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `watchlist` ;

-- -----------------------------------------------------
-- Schema watchlist
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `watchlist` DEFAULT CHARACTER SET utf8mb3 ;
USE `watchlist` ;

-- -----------------------------------------------------
-- Table `watchlist`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `watchlist`.`roles` ;

CREATE TABLE IF NOT EXISTS `watchlist`.`roles` (
  `rol_id` INT NOT NULL AUTO_INCREMENT,
  `rol_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`rol_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE UNIQUE INDEX `rol_name_UNIQUE` ON `watchlist`.`roles` (`rol_name` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `watchlist`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `watchlist`.`users` ;

CREATE TABLE IF NOT EXISTS `watchlist`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(20) NOT NULL,
  `password` CHAR(60) NOT NULL,
  `email` VARCHAR(90) NOT NULL,
  `Roles_rol_id` INT NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_Users_Roles`
    FOREIGN KEY (`Roles_rol_id`)
    REFERENCES `watchlist`.`roles` (`rol_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE UNIQUE INDEX `Username_UNIQUE` ON `watchlist`.`users` (`username` ASC) VISIBLE;

CREATE INDEX `fk_Users_Roles_idx` ON `watchlist`.`users` (`Roles_rol_id` ASC) VISIBLE;

CREATE UNIQUE INDEX `email_UNIQUE` ON `watchlist`.`users` (`email` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `watchlist`.`dnis`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `watchlist`.`dnis` ;

CREATE TABLE IF NOT EXISTS `watchlist`.`dnis` (
  `dni_id` INT NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(9) NOT NULL,
  `front_img` VARCHAR(45) NULL DEFAULT NULL,
  `back_img` VARCHAR(45) NULL DEFAULT NULL,
  `Users_user_id` INT NOT NULL,
  PRIMARY KEY (`dni_id`),
  CONSTRAINT `fk_DNIs_Users1`
    FOREIGN KEY (`Users_user_id`)
    REFERENCES `watchlist`.`users` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE UNIQUE INDEX `number_UNIQUE` ON `watchlist`.`dnis` (`number` ASC) VISIBLE;

CREATE INDEX `fk_DNIs_Users1_idx` ON `watchlist`.`dnis` (`Users_user_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `watchlist`.`films`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `watchlist`.`films` ;

CREATE TABLE IF NOT EXISTS `watchlist`.`films` (
  `film_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `release_date` DATE NOT NULL,
  PRIMARY KEY (`film_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE UNIQUE INDEX `title_UNIQUE` ON `watchlist`.`films` (`title` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `watchlist`.`users_haswatched_films`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `watchlist`.`users_haswatched_films` ;

CREATE TABLE IF NOT EXISTS `watchlist`.`users_haswatched_films` (
  `Users_user_id` INT NOT NULL,
  `Films_film_id` INT NOT NULL,
  PRIMARY KEY (`Users_user_id`, `Films_film_id`),
  CONSTRAINT `fk_Users_has_Films_Films1`
    FOREIGN KEY (`Films_film_id`)
    REFERENCES `watchlist`.`films` (`film_id`),
  CONSTRAINT `fk_Users_has_Films_Users1`
    FOREIGN KEY (`Users_user_id`)
    REFERENCES `watchlist`.`users` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE INDEX `fk_Users_has_Films_Films1_idx` ON `watchlist`.`users_haswatched_films` (`Films_film_id` ASC) VISIBLE;

CREATE INDEX `fk_Users_has_Films_Users1_idx` ON `watchlist`.`users_haswatched_films` (`Users_user_id` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


INSERT INTO watchlist.roles VALUES (1, "ADMIN"), (2, "USER");

INSERT INTO watchlist.users VALUES (0, "admin", "Abcd1234", "admin@localhost.com", 1);