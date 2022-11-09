-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema sakila
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sakila
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sakila` DEFAULT CHARACTER SET utf8 ;
USE `sakila` ;

-- -----------------------------------------------------
-- Table `sakila`.`Pelicula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sakila`.`Pelicula` (
  `idPelicula` INT NOT NULL AUTO_INCREMENT,
  `nombrePeli` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idPelicula`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sakila`.`Reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sakila`.`Reserva` (
  `idReserva` INT NOT NULL AUTO_INCREMENT,
  `Pelicula_idPelicula` INT NOT NULL,
  `fecha` DATE NOT NULL,
  `horaInicio` TIME NOT NULL,
  `horaFin` TIME NOT NULL,
  PRIMARY KEY (`idReserva`),
  INDEX `fk_Reserva_Pelicula_idx` (`Pelicula_idPelicula` ASC) VISIBLE,
  CONSTRAINT `fk_Reserva_Pelicula`
    FOREIGN KEY (`Pelicula_idPelicula`)
    REFERENCES `sakila`.`Pelicula` (`idPelicula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
