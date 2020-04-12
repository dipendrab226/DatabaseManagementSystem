-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema new1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema new1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `new1` DEFAULT CHARACTER SET utf8 ;
USE `new1` ;

-- -----------------------------------------------------
-- Table `new1`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new1`.`category` (
  `Cid` INT(11) NOT NULL,
  `Cname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Cid`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new1`.`bank`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new1`.`bank` (
  `Bankid` INT(11) NOT NULL,
  `BankName` VARCHAR(45) NULL DEFAULT NULL,
  `Amount` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`Bankid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new1`.`perk`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new1`.`perk` (
  `Perkid` INT(11) NOT NULL,
  `Perkname` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`Perkid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new1`.`level`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new1`.`level` (
  `Levelid` INT(11) NOT NULL,
  `Lname` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`Levelid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new1`.`ruser`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new1`.`ruser` (
  `RUserid` INT(10) UNSIGNED NOT NULL,
  `Firstname` VARCHAR(45) NOT NULL,
  `Lastname` VARCHAR(45) NOT NULL,
  `DOB` DATE NOT NULL,
  `Age` INT(11) NOT NULL,
  `Category_Cid` INT(11) NULL,
  `bank_Bankid` INT(11) NULL,
  `perk_Perkid` INT(11) NULL,
  `level_Levelid` INT(11) NULL,
  PRIMARY KEY (`RUserid`),
  INDEX `fk_User_Category1_idx` (`Category_Cid` ASC) INVISIBLE,
  INDEX `fk_ruser_bank1_idx` (`bank_Bankid` ASC) VISIBLE,
  INDEX `fk_ruser_perk1_idx` (`perk_Perkid` ASC) VISIBLE,
  INDEX `fk_ruser_level1_idx` (`level_Levelid` ASC) VISIBLE,
  CONSTRAINT `fk_User_Category1`
    FOREIGN KEY (`Category_Cid`)
    REFERENCES `new1`.`category` (`Cid`)
    ON DELETE SET NULL
    ON UPDATE SET NULL,
  CONSTRAINT `fk_ruser_bank1`
    FOREIGN KEY (`bank_Bankid`)
    REFERENCES `new1`.`bank` (`Bankid`)
    ON DELETE SET NULL
    ON UPDATE SET NULL,
  CONSTRAINT `fk_ruser_perk1`
    FOREIGN KEY (`perk_Perkid`)
    REFERENCES `new1`.`perk` (`Perkid`)
    ON DELETE SET NULL
    ON UPDATE SET NULL,
  CONSTRAINT `fk_ruser_level1`
    FOREIGN KEY (`level_Levelid`)
    REFERENCES `new1`.`level` (`Levelid`)
    ON DELETE SET NULL
    ON UPDATE SET NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new1`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new1`.`account` (
  `Accountid` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Email` VARCHAR(45) NOT NULL,
  `Paasword` VARCHAR(45) NOT NULL,
  `ruser_RUserid` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`Accountid`),
  INDEX `fk_account_ruser1_idx` (`ruser_RUserid` ASC) VISIBLE,
  CONSTRAINT `fk_account_ruser1`
    FOREIGN KEY (`ruser_RUserid`)
    REFERENCES `new1`.`ruser` (`RUserid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new1`.`paymethod`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new1`.`paymethod` (
  `Payid` INT(11) NOT NULL AUTO_INCREMENT,
  `ruser_RUserid` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`Payid`),
  INDEX `fk_paymethod_ruser1_idx` (`ruser_RUserid` ASC) VISIBLE,
  CONSTRAINT `fk_paymethod_ruser1`
    FOREIGN KEY (`ruser_RUserid`)
    REFERENCES `new1`.`ruser` (`RUserid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new1`.`bankac`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new1`.`bankac` (
  `paymethod_Payid` INT(11) NOT NULL,
  `BankACid` INT(11) NOT NULL,
  `Descrp` VARCHAR(45) NULL,
  PRIMARY KEY (`paymethod_Payid`),
  INDEX `fk_bankac_paymethod1_idx` (`paymethod_Payid` ASC) VISIBLE,
  CONSTRAINT `fk_bankac_paymethod1`
    FOREIGN KEY (`paymethod_Payid`)
    REFERENCES `new1`.`paymethod` (`Payid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new1`.`creditcard`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new1`.`creditcard` (
  `paymethod_Payid` INT(11) NOT NULL,
  `CCid` INT(11) NOT NULL,
  `Descrp` VARCHAR(45) NULL,
  PRIMARY KEY (`paymethod_Payid`),
  INDEX `fk_creditcard_paymethod1_idx` (`paymethod_Payid` ASC) VISIBLE,
  CONSTRAINT `fk_creditcard_paymethod1`
    FOREIGN KEY (`paymethod_Payid`)
    REFERENCES `new1`.`paymethod` (`Payid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new1`.`days`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new1`.`days` (
  `Daysid` INT(11) NOT NULL,
  `Stockid` INT(11) NULL DEFAULT NULL,
  `ruser_RUserid` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`Daysid`, `ruser_RUserid`),
  INDEX `fk_days_ruser1_idx` (`ruser_RUserid` ASC) VISIBLE,
  CONSTRAINT `fk_days_ruser1`
    FOREIGN KEY (`ruser_RUserid`)
    REFERENCES `new1`.`ruser` (`RUserid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new1`.`debitcard`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new1`.`debitcard` (
  `paymethod_Payid` INT(11) NOT NULL,
  `DebitCardid` INT(11) NOT NULL,
  `Descrp` VARCHAR(45) NULL,
  PRIMARY KEY (`paymethod_Payid`),
  INDEX `fk_debitcard_paymethod1_idx` (`paymethod_Payid` ASC) VISIBLE,
  CONSTRAINT `fk_debitcard_paymethod1`
    FOREIGN KEY (`paymethod_Payid`)
    REFERENCES `new1`.`paymethod` (`Payid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new1`.`expert`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new1`.`expert` (
  `Eid` INT(11) NOT NULL AUTO_INCREMENT,
  `ExpertName` VARCHAR(45) NOT NULL,
  `ruser_RUserid` INT(10) UNSIGNED NULL,
  PRIMARY KEY (`Eid`),
  INDEX `fk_expert_ruser1_idx` (`ruser_RUserid` ASC) VISIBLE,
  CONSTRAINT `fk_expert_ruser1`
    FOREIGN KEY (`ruser_RUserid`)
    REFERENCES `new1`.`ruser` (`RUserid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new1`.`friends`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new1`.`friends` (
  `Friendid` INT(11) NOT NULL AUTO_INCREMENT,
  `Fname` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`Friendid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new1`.`invite`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new1`.`invite` (
  `Inviteid` INT(11) NOT NULL AUTO_INCREMENT,
  `Personname` VARCHAR(45) NULL DEFAULT NULL,
  `ruser_RUserid` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`Inviteid`, `ruser_RUserid`),
  INDEX `fk_invite_ruser1_idx` (`ruser_RUserid` ASC) VISIBLE,
  CONSTRAINT `fk_invite_ruser1`
    FOREIGN KEY (`ruser_RUserid`)
    REFERENCES `new1`.`ruser` (`RUserid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new1`.`stock`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new1`.`stock` (
  `Stockid` INT(11) NOT NULL,
  `Stockvalue` INT(11) NOT NULL,
  `stockname` VARCHAR(45) NULL,
  PRIMARY KEY (`Stockid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new1`.`wishlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new1`.`wishlist` (
  `Wishlistid` INT(11) NOT NULL,
  `Stockid` INT(11) NOT NULL,
  `Wname` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`Wishlistid`, `Stockid`),
  INDEX `fk_wishlist_stockid_idx` (`Stockid` ASC) VISIBLE,
  CONSTRAINT `fk_wishlist_stockid`
    FOREIGN KEY (`Stockid`)
    REFERENCES `new1`.`stock` (`Stockid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new1`.`maintain`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new1`.`maintain` (
  `Maintainid` INT(11) NOT NULL AUTO_INCREMENT,
  `Wishlist_Wishlistid` INT(11) NOT NULL,
  `Wishlist_Stockid` INT(11) NOT NULL,
  `ruser_RUserid` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`Maintainid`, `Wishlist_Wishlistid`, `Wishlist_Stockid`),
  INDEX `fk_Maintain_Wishlist1_idx` (`Wishlist_Wishlistid` ASC, `Wishlist_Stockid` ASC) VISIBLE,
  INDEX `fk_maintain_ruser1_idx` (`ruser_RUserid` ASC) VISIBLE,
  CONSTRAINT `fk_Maintain_Wishlist1`
    FOREIGN KEY (`Wishlist_Wishlistid` , `Wishlist_Stockid`)
    REFERENCES `new1`.`wishlist` (`Wishlistid` , `Stockid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_maintain_ruser1`
    FOREIGN KEY (`ruser_RUserid`)
    REFERENCES `new1`.`ruser` (`RUserid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new1`.`rating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new1`.`rating` (
  `Ratingid` INT(11) NOT NULL,
  `Ratingnumber` VARCHAR(45) NULL DEFAULT NULL,
  `ruser_RUserid` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`Ratingid`, `ruser_RUserid`),
  INDEX `fk_rating_ruser1_idx` (`ruser_RUserid` ASC) VISIBLE,
  CONSTRAINT `fk_rating_ruser1`
    FOREIGN KEY (`ruser_RUserid`)
    REFERENCES `new1`.`ruser` (`RUserid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new1`.`referral`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new1`.`referral` (
  `Referralid` INT(11) NOT NULL,
  `Refferedname` VARCHAR(45) NULL DEFAULT NULL,
  `ruser_RUserid` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`Referralid`, `ruser_RUserid`),
  INDEX `fk_referral_ruser1_idx` (`ruser_RUserid` ASC) VISIBLE,
  CONSTRAINT `fk_referral_ruser1`
    FOREIGN KEY (`ruser_RUserid`)
    REFERENCES `new1`.`ruser` (`RUserid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new1`.`share`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new1`.`share` (
  `Shareid` INT(11) NOT NULL,
  `Stockid` INT(11) NULL,
  `Friends_Friendid` INT(11) NOT NULL,
  `ruser_RUserid` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`Shareid`),
  INDEX `fk_Share_Friends1_idx` (`Friends_Friendid` ASC) VISIBLE,
  INDEX `fk_share_ruser1_idx` (`ruser_RUserid` ASC) VISIBLE,
  CONSTRAINT `fk_Share_Friends1`
    FOREIGN KEY (`Friends_Friendid`)
    REFERENCES `new1`.`friends` (`Friendid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_share_ruser1`
    FOREIGN KEY (`ruser_RUserid`)
    REFERENCES `new1`.`ruser` (`RUserid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new1`.`Buying`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new1`.`Buying` (
  `paymethod_payid` INT NOT NULL,
  `Buydate` DATE NULL,
  `Description` VARCHAR(45) NULL,
  `stock_Stockid` INT(11) NOT NULL,
  `ruser_RUserid` INT(10) UNSIGNED NOT NULL,
  INDEX `fk_Buying_stock1_idx` (`stock_Stockid` ASC) VISIBLE,
  INDEX `fk_Buying_ruser1_idx` (`ruser_RUserid` ASC) VISIBLE,
  PRIMARY KEY (`paymethod_payid`),
  INDEX `fk_Buying_paymethod1_idx` (`paymethod_payid` ASC) INVISIBLE,
  CONSTRAINT `fk_Buying_stock1`
    FOREIGN KEY (`stock_Stockid`)
    REFERENCES `new1`.`stock` (`Stockid`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Buying_ruser1`
    FOREIGN KEY (`ruser_RUserid`)
    REFERENCES `new1`.`ruser` (`RUserid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Buying_paymethod1`
    FOREIGN KEY (`paymethod_payid`)
    REFERENCES `new1`.`paymethod` (`Payid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
