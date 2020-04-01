-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema databasemodel
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema databasemodel
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `databasemodel` DEFAULT CHARACTER SET utf8 ;
USE `databasemodel` ;

-- -----------------------------------------------------
-- Table `databasemodel`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databasemodel`.`category` (
  `Cid` INT(11) NOT NULL,
  `Cname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Cid`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `databasemodel`.`bank`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databasemodel`.`bank` (
  `Bankid` INT(11) NOT NULL,
  `BankName` VARCHAR(45) NULL DEFAULT NULL,
  `Amount` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`Bankid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `databasemodel`.`perk`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databasemodel`.`perk` (
  `Perkid` INT(11) NOT NULL,
  `Perkname` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`Perkid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `databasemodel`.`level`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databasemodel`.`level` (
  `Levelid` INT(11) NOT NULL,
  `Lname` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`Levelid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `databasemodel`.`ruser`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databasemodel`.`ruser` (
  `RUserid` INT(10) UNSIGNED NOT NULL,
  `Firstname` VARCHAR(45) NOT NULL,
  `Lastname` VARCHAR(45) NOT NULL,
  `DOB` DATE NOT NULL,
  `Age` INT(11) NOT NULL,
  `Category_Cid` INT(11) NOT NULL,
  `bank_Bankid` INT(11) NULL,
  `perk_Perkid` INT(11) NULL,
  `level_Levelid` INT(11) NOT NULL,
  PRIMARY KEY (`RUserid`),
  INDEX `fk_User_Category1_idx` (`Category_Cid` ASC) VISIBLE,
  INDEX `fk_ruser_bank1_idx` (`bank_Bankid` ASC) VISIBLE,
  INDEX `fk_ruser_perk1_idx` (`perk_Perkid` ASC) VISIBLE,
  INDEX `fk_ruser_level1_idx` (`level_Levelid` ASC) VISIBLE,
  CONSTRAINT `fk_User_Category1`
    FOREIGN KEY (`Category_Cid`)
    REFERENCES `databasemodel`.`category` (`Cid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ruser_bank1`
    FOREIGN KEY (`bank_Bankid`)
    REFERENCES `databasemodel`.`bank` (`Bankid`)
    ON DELETE SET NULL
    ON UPDATE SET NULL,
  CONSTRAINT `fk_ruser_perk1`
    FOREIGN KEY (`perk_Perkid`)
    REFERENCES `databasemodel`.`perk` (`Perkid`)
    ON DELETE SET NULL
    ON UPDATE SET NULL,
  CONSTRAINT `fk_ruser_level1`
    FOREIGN KEY (`level_Levelid`)
    REFERENCES `databasemodel`.`level` (`Levelid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `databasemodel`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databasemodel`.`account` (
  `Accountid` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Email` VARCHAR(45) NOT NULL,
  `Paasword` VARCHAR(45) NOT NULL,
  `ruser_RUserid` INT(10) UNSIGNED NULL,
  PRIMARY KEY (`Accountid`),
  INDEX `fk_account_ruser1_idx` (`ruser_RUserid` ASC) VISIBLE,
  CONSTRAINT `fk_account_ruser1`
    FOREIGN KEY (`ruser_RUserid`)
    REFERENCES `databasemodel`.`ruser` (`RUserid`)
    ON DELETE SET NULL
    ON UPDATE SET NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `databasemodel`.`paymethod`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databasemodel`.`paymethod` (
  `Payid` INT(11) NOT NULL AUTO_INCREMENT,
  `ruser_RUserid` INT(10) UNSIGNED NULL,
  PRIMARY KEY (`Payid`),
  INDEX `fk_paymethod_ruser1_idx` (`ruser_RUserid` ASC) VISIBLE,
  CONSTRAINT `fk_paymethod_ruser1`
    FOREIGN KEY (`ruser_RUserid`)
    REFERENCES `databasemodel`.`ruser` (`RUserid`)
    ON DELETE SET NULL
    ON UPDATE SET NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `databasemodel`.`bankac`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databasemodel`.`bankac` (
  `paymethod_Payid` INT(11) NOT NULL,
  `BankACid` INT(11) NOT NULL,
  `Descrp` VARCHAR(45) NULL,
  PRIMARY KEY (`paymethod_Payid`),
  INDEX `fk_bankac_paymethod1_idx` (`paymethod_Payid` ASC) VISIBLE,
  CONSTRAINT `fk_bankac_paymethod1`
    FOREIGN KEY (`paymethod_Payid`)
    REFERENCES `databasemodel`.`paymethod` (`Payid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `databasemodel`.`stock`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databasemodel`.`stock` (
  `Payid` INT(11) NOT NULL AUTO_INCREMENT,
  `Stockid` INT(11) NOT NULL,
  `Stockname` TEXT NOT NULL,
  `Stockvalue` INT(11) NOT NULL,
  PRIMARY KEY (`Payid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `databasemodel`.`buying`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databasemodel`.`buying` (
  `Stock_Payid` INT(11) NOT NULL,
  `Stockid` INT(11) NOT NULL,
  `Buydate` DATE NOT NULL,
  `ruser_RUserid` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`Stock_Payid`, `ruser_RUserid`),
  INDEX `fk_Buying_Stock1_idx` (`Stock_Payid` ASC) VISIBLE,
  INDEX `fk_buying_ruser1_idx` (`ruser_RUserid` ASC) VISIBLE,
  CONSTRAINT `fk_Buying_Stock1`
    FOREIGN KEY (`Stock_Payid`)
    REFERENCES `databasemodel`.`stock` (`Payid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_buying_ruser1`
    FOREIGN KEY (`ruser_RUserid`)
    REFERENCES `databasemodel`.`ruser` (`RUserid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `databasemodel`.`creditcard`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databasemodel`.`creditcard` (
  `paymethod_Payid` INT(11) NOT NULL,
  `CCid` INT(11) NOT NULL,
  `Descrp` VARCHAR(45) NULL,
  PRIMARY KEY (`paymethod_Payid`),
  INDEX `fk_creditcard_paymethod1_idx` (`paymethod_Payid` ASC) VISIBLE,
  CONSTRAINT `fk_creditcard_paymethod1`
    FOREIGN KEY (`paymethod_Payid`)
    REFERENCES `databasemodel`.`paymethod` (`Payid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `databasemodel`.`days`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databasemodel`.`days` (
  `Daysid` INT(11) NOT NULL,
  `Stockid` INT(11) NULL DEFAULT NULL,
  `ruser_RUserid` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`Daysid`, `ruser_RUserid`),
  INDEX `fk_days_ruser1_idx` (`ruser_RUserid` ASC) VISIBLE,
  CONSTRAINT `fk_days_ruser1`
    FOREIGN KEY (`ruser_RUserid`)
    REFERENCES `databasemodel`.`ruser` (`RUserid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `databasemodel`.`debitcard`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databasemodel`.`debitcard` (
  `paymethod_Payid` INT(11) NOT NULL,
  `DebitCardid` INT(11) NOT NULL,
  `Descrp` VARCHAR(45) NULL,
  PRIMARY KEY (`paymethod_Payid`),
  INDEX `fk_debitcard_paymethod1_idx` (`paymethod_Payid` ASC) VISIBLE,
  CONSTRAINT `fk_debitcard_paymethod1`
    FOREIGN KEY (`paymethod_Payid`)
    REFERENCES `databasemodel`.`paymethod` (`Payid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `databasemodel`.`expert`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databasemodel`.`expert` (
  `Eid` INT(11) NOT NULL AUTO_INCREMENT,
  `ExpertName` VARCHAR(45) NOT NULL,
  `ruser_RUserid` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`Eid`, `ruser_RUserid`),
  INDEX `fk_expert_ruser1_idx` (`ruser_RUserid` ASC) VISIBLE,
  CONSTRAINT `fk_expert_ruser1`
    FOREIGN KEY (`ruser_RUserid`)
    REFERENCES `databasemodel`.`ruser` (`RUserid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `databasemodel`.`friends`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databasemodel`.`friends` (
  `Friendid` INT(11) NOT NULL AUTO_INCREMENT,
  `Fname` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`Friendid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `databasemodel`.`invite`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databasemodel`.`invite` (
  `Inviteid` INT(11) NOT NULL AUTO_INCREMENT,
  `Personname` VARCHAR(45) NULL DEFAULT NULL,
  `ruser_RUserid` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`Inviteid`, `ruser_RUserid`),
  INDEX `fk_invite_ruser1_idx` (`ruser_RUserid` ASC) VISIBLE,
  CONSTRAINT `fk_invite_ruser1`
    FOREIGN KEY (`ruser_RUserid`)
    REFERENCES `databasemodel`.`ruser` (`RUserid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `databasemodel`.`wishlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databasemodel`.`wishlist` (
  `Wishlistid` INT(11) NOT NULL,
  `Stockid` INT(11) NOT NULL,
  `Wname` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`Wishlistid`, `Stockid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `databasemodel`.`maintain`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databasemodel`.`maintain` (
  `Maintainid` INT(11) NOT NULL AUTO_INCREMENT,
  `Wishlist_Wishlistid` INT(11) NOT NULL,
  `Wishlist_Stockid` INT(11) NOT NULL,
  `ruser_RUserid` INT(10) UNSIGNED NULL,
  PRIMARY KEY (`Maintainid`, `Wishlist_Wishlistid`, `Wishlist_Stockid`),
  INDEX `fk_Maintain_Wishlist1_idx` (`Wishlist_Wishlistid` ASC, `Wishlist_Stockid` ASC) VISIBLE,
  INDEX `fk_maintain_ruser1_idx` (`ruser_RUserid` ASC) VISIBLE,
  CONSTRAINT `fk_Maintain_Wishlist1`
    FOREIGN KEY (`Wishlist_Wishlistid` , `Wishlist_Stockid`)
    REFERENCES `databasemodel`.`wishlist` (`Wishlistid` , `Stockid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_maintain_ruser1`
    FOREIGN KEY (`ruser_RUserid`)
    REFERENCES `databasemodel`.`ruser` (`RUserid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `databasemodel`.`rating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databasemodel`.`rating` (
  `Ratingid` INT(11) NOT NULL,
  `Ratingnumber` VARCHAR(45) NULL DEFAULT NULL,
  `ruser_RUserid` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`Ratingid`, `ruser_RUserid`),
  INDEX `fk_rating_ruser1_idx` (`ruser_RUserid` ASC) VISIBLE,
  CONSTRAINT `fk_rating_ruser1`
    FOREIGN KEY (`ruser_RUserid`)
    REFERENCES `databasemodel`.`ruser` (`RUserid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `databasemodel`.`referral`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databasemodel`.`referral` (
  `Referralid` INT(11) NOT NULL,
  `Refferedname` VARCHAR(45) NULL DEFAULT NULL,
  `ruser_RUserid` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`Referralid`, `ruser_RUserid`),
  INDEX `fk_referral_ruser1_idx` (`ruser_RUserid` ASC) VISIBLE,
  CONSTRAINT `fk_referral_ruser1`
    FOREIGN KEY (`ruser_RUserid`)
    REFERENCES `databasemodel`.`ruser` (`RUserid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `databasemodel`.`share`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `databasemodel`.`share` (
  `Shareid` INT(11) NOT NULL,
  `Stockid` INT(11) NOT NULL,
  `Friends_Friendid` INT(11) NOT NULL,
  `ruser_RUserid` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`Shareid`, `Friends_Friendid`, `ruser_RUserid`),
  INDEX `fk_Share_Friends1_idx` (`Friends_Friendid` ASC) VISIBLE,
  INDEX `fk_share_ruser1_idx` (`ruser_RUserid` ASC) VISIBLE,
  CONSTRAINT `fk_Share_Friends1`
    FOREIGN KEY (`Friends_Friendid`)
    REFERENCES `databasemodel`.`friends` (`Friendid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_share_ruser1`
    FOREIGN KEY (`ruser_RUserid`)
    REFERENCES `databasemodel`.`ruser` (`RUserid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
