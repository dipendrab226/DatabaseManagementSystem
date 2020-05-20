package connector;

import dbhelper.DbConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseCreator {

    public int dbCreator() {
        String sqlQuery = "CREATE SCHEMA IF NOT EXISTS `databasemodel` DEFAULT CHARACTER SET utf8 ;";
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", DbConfig.DB_USER, DbConfig.DB_PASSWORD);
            Statement statement = con.createStatement();
            statement.execute(sqlQuery);
            statement.execute("CREATE TABLE IF NOT EXISTS `databasemodel`.`category` (\n" +
                    "  `Cid` INT(11) NOT NULL,\n" +
                    "  `Cname` VARCHAR(45) NOT NULL,\n" +
                    "  PRIMARY KEY (`Cid`))\n" +
                    "ENGINE = InnoDB\n" +
                    "AUTO_INCREMENT = 4\n" +
                    "DEFAULT CHARACTER SET = utf8;");
            statement.execute("CREATE TABLE IF NOT EXISTS `databasemodel`.`bank` (\n" +
                    "  `Bankid` INT(11) NOT NULL,\n" +
                    "  `BankName` VARCHAR(45) NULL DEFAULT NULL,\n" +
                    "  `Amount` INT(11) NULL DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`Bankid`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;");
            statement.execute("INSERT INTO `databasemodel`.`bank` VALUES (1, 'Bank Of America', 3000);");
            statement.execute("CREATE TABLE IF NOT EXISTS `databasemodel`.`perk` (\n" +
                    "  `Perkid` INT(11) NOT NULL,\n" +
                    "  `Perkname` VARCHAR(45) NULL DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`Perkid`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;");
            statement.execute("CREATE TABLE IF NOT EXISTS `databasemodel`.`level` (\n" +
                    "  `Levelid` INT(11) NOT NULL,\n" +
                    "  `Lname` VARCHAR(45) NULL DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`Levelid`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;");
            statement.execute("CREATE TABLE IF NOT EXISTS `databasemodel`.`ruser` (\n" +
                    "  `RUserid` INT(10) UNSIGNED NOT NULL,\n" +
                    "  `Firstname` VARCHAR(45) NOT NULL,\n" +
                    "  `Lastname` VARCHAR(45) NOT NULL,\n" +
                    "  `DOB` DATE NOT NULL,\n" +
                    "  `Age` INT(11) NOT NULL,\n" +
                    "  `Category_Cid` INT(11) NULL,\n" +
                    "  `bank_Bankid` INT(11) NULL,\n" +
                    "  `perk_Perkid` INT(11) NULL,\n" +
                    "  `level_Levelid` INT(11) NULL,\n" +
                    "  PRIMARY KEY (`RUserid`),\n" +
                    "  INDEX `fk_User_Category1_idx` (`Category_Cid` ASC) ,\n" +
                    "  INDEX `fk_ruser_bank1_idx` (`bank_Bankid` ASC) ,\n" +
                    "  INDEX `fk_ruser_perk1_idx` (`perk_Perkid` ASC) ,\n" +
                    "  INDEX `fk_ruser_level1_idx` (`level_Levelid` ASC) ,\n" +
                    "  CONSTRAINT `fk_User_Category1`\n" +
                    "    FOREIGN KEY (`Category_Cid`)\n" +
                    "    REFERENCES `databasemodel`.`category` (`Cid`)\n" +
                    "    ON DELETE SET NULL\n" +
                    "    ON UPDATE SET NULL,\n" +
                    "  CONSTRAINT `fk_ruser_bank1`\n" +
                    "    FOREIGN KEY (`bank_Bankid`)\n" +
                    "    REFERENCES `databasemodel`.`bank` (`Bankid`)\n" +
                    "    ON DELETE SET NULL\n" +
                    "    ON UPDATE SET NULL,\n" +
                    "  CONSTRAINT `fk_ruser_perk1`\n" +
                    "    FOREIGN KEY (`perk_Perkid`)\n" +
                    "    REFERENCES `databasemodel`.`perk` (`Perkid`)\n" +
                    "    ON DELETE SET NULL\n" +
                    "    ON UPDATE SET NULL,\n" +
                    "  CONSTRAINT `fk_ruser_level1`\n" +
                    "    FOREIGN KEY (`level_Levelid`)\n" +
                    "    REFERENCES `databasemodel`.`level` (`Levelid`)\n" +
                    "    ON DELETE SET NULL\n" +
                    "    ON UPDATE SET NULL)\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;");
            statement.execute("INSERT INTO `databasemodel`.`ruser` VALUES (1, 'Cristopper', 'Bail', '1987-05-19', 33, NULL, NULL, NULL, NULL)");
            statement.execute("INSERT INTO `databasemodel`.`ruser` VALUES (2, 'William ', 'Angel', '1995-06-20', 25, NULL, NULL, NULL, NULL)");
            statement.execute("INSERT INTO `databasemodel`.`ruser` VALUES (3, 'Xavi', 'Crousor', '1989-02-15', 31, NULL, NULL, NULL, NULL)");
            statement.execute("INSERT INTO `databasemodel`.`ruser` VALUES (4, 'David', 'Jhon', '1990-05-05', 30, NULL, NULL, NULL, NULL)");
            statement.execute("INSERT INTO `databasemodel`.`ruser` VALUES (5, 'Hamilton', 'kein', '1990-07-05', 30, NULL, NULL, NULL, NULL)");

            statement.execute("CREATE TABLE IF NOT EXISTS `databasemodel`.`account` (\n" +
                    "  `Accountid` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
                    "  `Email` VARCHAR(45) NOT NULL,\n" +
                    "  `Password` VARCHAR(45) NOT NULL,\n" +
                    "  `ruser_RUserid` INT(10) UNSIGNED NOT NULL,\n" +
                    "  PRIMARY KEY (`Accountid`),\n" +
                    "  INDEX `fk_account_ruser1_idx` (`ruser_RUserid` ASC) ,\n" +
                    "  CONSTRAINT `fk_account_ruser1`\n" +
                    "    FOREIGN KEY (`ruser_RUserid`)\n" +
                    "    REFERENCES `databasemodel`.`ruser` (`RUserid`)\n" +
                    "    ON DELETE CASCADE\n" +
                    "    ON UPDATE CASCADE)\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;");
            statement.execute("INSERT INTO `databasemodel`.`account` VALUES (1, 'sdlkfjs@123', 'wflkff', 1);");
            statement.execute("INSERT INTO `databasemodel`.`account` VALUES (2, 'sadfsadf', 'sdfad', 2);");
            statement.execute("INSERT INTO `databasemodel`.`account` VALUES (3, 'sadfsadfk', 'sdfad', 2);");
            statement.execute("CREATE TABLE IF NOT EXISTS `databasemodel`.`paymethod` (\n" +
                    "  `Payid` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `ruser_RUserid` INT(10) UNSIGNED NOT NULL,\n" +
                    "  PRIMARY KEY (`Payid`),\n" +
                    "  INDEX `fk_paymethod_ruser1_idx` (`ruser_RUserid` ASC) ,\n" +
                    "  CONSTRAINT `fk_paymethod_ruser1`\n" +
                    "    FOREIGN KEY (`ruser_RUserid`)\n" +
                    "    REFERENCES `databasemodel`.`ruser` (`RUserid`)\n" +
                    "    ON DELETE CASCADE\n" +
                    "    ON UPDATE CASCADE)\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;");
            statement.execute("CREATE TABLE IF NOT EXISTS `databasemodel`.`bankac` (\n" +
                    "  `paymethod_Payid` INT(11) NOT NULL,\n" +
                    "  `BankACid` INT(11) NOT NULL,\n" +
                    "  `Descrp` VARCHAR(45) NULL,\n" +
                    "  PRIMARY KEY (`paymethod_Payid`),\n" +
                    "  INDEX `fk_bankac_paymethod1_idx` (`paymethod_Payid` ASC) ,\n" +
                    "  CONSTRAINT `fk_bankac_paymethod1`\n" +
                    "    FOREIGN KEY (`paymethod_Payid`)\n" +
                    "    REFERENCES `databasemodel`.`paymethod` (`Payid`)\n" +
                    "    ON DELETE CASCADE\n" +
                    "    ON UPDATE CASCADE)\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;\n");
            statement.execute("CREATE TABLE IF NOT EXISTS `databasemodel`.`creditcard` (\n" +
                    "  `paymethod_Payid` INT(11) NOT NULL,\n" +
                    "  `CCid` INT(11) NOT NULL,\n" +
                    "  `Descrp` VARCHAR(45) NULL,\n" +
                    "  PRIMARY KEY (`paymethod_Payid`),\n" +
                    "  INDEX `fk_creditcard_paymethod1_idx` (`paymethod_Payid` ASC) ,\n" +
                    "  CONSTRAINT `fk_creditcard_paymethod1`\n" +
                    "    FOREIGN KEY (`paymethod_Payid`)\n" +
                    "    REFERENCES `databasemodel`.`paymethod` (`Payid`)\n" +
                    "    ON DELETE CASCADE\n" +
                    "    ON UPDATE CASCADE)\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;");
            statement.execute("CREATE TABLE IF NOT EXISTS `databasemodel`.`days` (\n" +
                    "  `Daysid` INT(11) NOT NULL,\n" +
                    "  `Stockid` INT(11) NULL DEFAULT NULL,\n" +
                    "  `ruser_RUserid` INT(10) UNSIGNED NOT NULL,\n" +
                    "  PRIMARY KEY (`Daysid`, `ruser_RUserid`),\n" +
                    "  INDEX `fk_days_ruser1_idx` (`ruser_RUserid` ASC) ,\n" +
                    "  CONSTRAINT `fk_days_ruser1`\n" +
                    "    FOREIGN KEY (`ruser_RUserid`)\n" +
                    "    REFERENCES `databasemodel`.`ruser` (`RUserid`)\n" +
                    "    ON DELETE CASCADE\n" +
                    "    ON UPDATE CASCADE)\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;");
            statement.execute("CREATE TABLE IF NOT EXISTS `databasemodel`.`debitcard` (\n" +
                    "  `paymethod_Payid` INT(11) NOT NULL,\n" +
                    "  `DebitCardid` INT(11) NOT NULL,\n" +
                    "  `Descrp` VARCHAR(45) NULL,\n" +
                    "  PRIMARY KEY (`paymethod_Payid`),\n" +
                    "  INDEX `fk_debitcard_paymethod1_idx` (`paymethod_Payid` ASC) ,\n" +
                    "  CONSTRAINT `fk_debitcard_paymethod1`\n" +
                    "    FOREIGN KEY (`paymethod_Payid`)\n" +
                    "    REFERENCES `databasemodel`.`paymethod` (`Payid`)\n" +
                    "    ON DELETE CASCADE\n" +
                    "    ON UPDATE CASCADE)\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;");
            statement.execute("CREATE TABLE IF NOT EXISTS `databasemodel`.`expert` (\n" +
                    "  `Eid` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `ExpertName` VARCHAR(45) NOT NULL,\n" +
                    "  `ruser_RUserid` INT(10) UNSIGNED NULL,\n" +
                    "  PRIMARY KEY (`Eid`),\n" +
                    "  INDEX `fk_expert_ruser1_idx` (`ruser_RUserid` ASC) ,\n" +
                    "  CONSTRAINT `fk_expert_ruser1`\n" +
                    "    FOREIGN KEY (`ruser_RUserid`)\n" +
                    "    REFERENCES `databasemodel`.`ruser` (`RUserid`)\n" +
                    "    ON DELETE CASCADE\n" +
                    "    ON UPDATE CASCADE)\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;");
            statement.execute("CREATE TABLE IF NOT EXISTS `databasemodel`.`friends` (\n" +
                    "  `Friendid` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `Fname` TEXT NULL DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`Friendid`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;");
            statement.execute("CREATE TABLE IF NOT EXISTS `databasemodel`.`invite` (\n" +
                    "  `Inviteid` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `Personname` VARCHAR(45) NULL DEFAULT NULL,\n" +
                    "  `ruser_RUserid` INT(10) UNSIGNED NOT NULL,\n" +
                    "  PRIMARY KEY (`Inviteid`, `ruser_RUserid`),\n" +
                    "  INDEX `fk_invite_ruser1_idx` (`ruser_RUserid` ASC) ,\n" +
                    "  CONSTRAINT `fk_invite_ruser1`\n" +
                    "    FOREIGN KEY (`ruser_RUserid`)\n" +
                    "    REFERENCES `databasemodel`.`ruser` (`RUserid`)\n" +
                    "    ON DELETE CASCADE\n" +
                    "    ON UPDATE CASCADE)\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;");
            statement.execute("CREATE TABLE IF NOT EXISTS `databasemodel`.`stock` (\n" +
                    "  `Stockid` INT(11) NOT NULL,\n" +
                    "  `Stockvalue` INT(11) NOT NULL,\n" +
                    "  `stockname` VARCHAR(45) NULL,\n" +
                    "  PRIMARY KEY (`Stockid`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;\n");
            statement.execute("INSERT INTO `databasemodel`.`stock` VALUES (1, 200, 'Oracle')");
            statement.execute("INSERT INTO `databasemodel`.`stock` VALUES (2, 105, 'Google')");
            statement.execute("INSERT INTO `databasemodel`.`stock` VALUES (3, 234, 'mx')");

            statement.execute("CREATE TABLE IF NOT EXISTS `databasemodel`.`wishlist` (\n" +
                    "  `Wishlistid` INT(11) NOT NULL,\n" +
                    "  `Stockid` INT(11) NOT NULL,\n" +
                    "  `Wname` VARCHAR(45) NULL DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`Wishlistid`, `Stockid`),\n" +
                    "  INDEX `fk_wishlist_stockid_idx` (`Stockid` ASC) ,\n" +
                    "  CONSTRAINT `fk_wishlist_stockid`\n" +
                    "    FOREIGN KEY (`Stockid`)\n" +
                    "    REFERENCES `databasemodel`.`stock` (`Stockid`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION)\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;");
            statement.execute("CREATE TABLE IF NOT EXISTS `databasemodel`.`maintain` (\n" +
                    "  `Maintainid` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `Wishlist_Wishlistid` INT(11) NOT NULL,\n" +
                    "  `Wishlist_Stockid` INT(11) NOT NULL,\n" +
                    "  `ruser_RUserid` INT(10) UNSIGNED NOT NULL,\n" +
                    "  PRIMARY KEY (`Maintainid`, `Wishlist_Wishlistid`, `Wishlist_Stockid`),\n" +
                    "  INDEX `fk_Maintain_Wishlist1_idx` (`Wishlist_Wishlistid` ASC, `Wishlist_Stockid` ASC) ,\n" +
                    "  INDEX `fk_maintain_ruser1_idx` (`ruser_RUserid` ASC) ,\n" +
                    "  CONSTRAINT `fk_Maintain_Wishlist1`\n" +
                    "    FOREIGN KEY (`Wishlist_Wishlistid` , `Wishlist_Stockid`)\n" +
                    "    REFERENCES `databasemodel`.`wishlist` (`Wishlistid` , `Stockid`)\n" +
                    "    ON DELETE CASCADE\n" +
                    "    ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `fk_maintain_ruser1`\n" +
                    "    FOREIGN KEY (`ruser_RUserid`)\n" +
                    "    REFERENCES `databasemodel`.`ruser` (`RUserid`)\n" +
                    "    ON DELETE CASCADE\n" +
                    "    ON UPDATE CASCADE)\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;");
            statement.execute("CREATE TABLE IF NOT EXISTS `databasemodel`.`rating` (\n" +
                    "  `Ratingid` INT(11) NOT NULL,\n" +
                    "  `Ratingnumber` VARCHAR(45) NULL DEFAULT NULL,\n" +
                    "  `ruser_RUserid` INT(10) UNSIGNED NOT NULL,\n" +
                    "  PRIMARY KEY (`Ratingid`, `ruser_RUserid`),\n" +
                    "  INDEX `fk_rating_ruser1_idx` (`ruser_RUserid` ASC) ,\n" +
                    "  CONSTRAINT `fk_rating_ruser1`\n" +
                    "    FOREIGN KEY (`ruser_RUserid`)\n" +
                    "    REFERENCES `databasemodel`.`ruser` (`RUserid`)\n" +
                    "    ON DELETE CASCADE\n" +
                    "    ON UPDATE CASCADE)\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;");
            statement.execute("CREATE TABLE IF NOT EXISTS `databasemodel`.`referral` (\n" +
                    "  `Referralid` INT(11) NOT NULL,\n" +
                    "  `Refferedname` VARCHAR(45) NULL DEFAULT NULL,\n" +
                    "  `ruser_RUserid` INT(10) UNSIGNED NOT NULL,\n" +
                    "  PRIMARY KEY (`Referralid`, `ruser_RUserid`),\n" +
                    "  INDEX `fk_referral_ruser1_idx` (`ruser_RUserid` ASC) ,\n" +
                    "  CONSTRAINT `fk_referral_ruser1`\n" +
                    "    FOREIGN KEY (`ruser_RUserid`)\n" +
                    "    REFERENCES `databasemodel`.`ruser` (`RUserid`)\n" +
                    "    ON DELETE CASCADE\n" +
                    "    ON UPDATE CASCADE)\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;\n");
            statement.execute("CREATE TABLE IF NOT EXISTS `databasemodel`.`share` (\n" +
                    "  `Shareid` INT(11) NOT NULL,\n" +
                    "  `Stockid` INT(11) NULL,\n" +
                    "  `Friends_Friendid` INT(11) NOT NULL,\n" +
                    "  `ruser_RUserid` INT(10) UNSIGNED NOT NULL,\n" +
                    "  PRIMARY KEY (`Shareid`),\n" +
                    "  INDEX `fk_Share_Friends1_idx` (`Friends_Friendid` ASC) ,\n" +
                    "  INDEX `fk_share_ruser1_idx` (`ruser_RUserid` ASC) ,\n" +
                    "  CONSTRAINT `fk_Share_Friends1`\n" +
                    "    FOREIGN KEY (`Friends_Friendid`)\n" +
                    "    REFERENCES `databasemodel`.`friends` (`Friendid`)\n" +
                    "    ON DELETE CASCADE\n" +
                    "    ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `fk_share_ruser1`\n" +
                    "    FOREIGN KEY (`ruser_RUserid`)\n" +
                    "    REFERENCES `databasemodel`.`ruser` (`RUserid`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION)\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;");
            statement.execute("CREATE TABLE IF NOT EXISTS `databasemodel`.`Buying` (\n" +
                    "  `paymethod_payid` INT NOT NULL,\n" +
                    "  `Buydate` DATE NULL,\n" +
                    "  `Description` VARCHAR(45) NULL,\n" +
                    "  `stock_Stockid` INT(11) NOT NULL,\n" +
                    "  `ruser_RUserid` INT(10) UNSIGNED NOT NULL,\n" +
                    "  INDEX `fk_Buying_stock1_idx` (`stock_Stockid` ASC) ,\n" +
                    "  INDEX `fk_Buying_ruser1_idx` (`ruser_RUserid` ASC) ,\n" +
                    "  PRIMARY KEY (`paymethod_payid`),\n" +
                    "  INDEX `fk_Buying_paymethod1_idx` (`paymethod_payid` ASC) ,\n" +
                    "  CONSTRAINT `fk_Buying_stock1`\n" +
                    "    FOREIGN KEY (`stock_Stockid`)\n" +
                    "    REFERENCES `databasemodel`.`stock` (`Stockid`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `fk_Buying_ruser1`\n" +
                    "    FOREIGN KEY (`ruser_RUserid`)\n" +
                    "    REFERENCES `databasemodel`.`ruser` (`RUserid`)\n" +
                    "    ON DELETE CASCADE\n" +
                    "    ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `fk_Buying_paymethod1`\n" +
                    "    FOREIGN KEY (`paymethod_payid`)\n" +
                    "    REFERENCES `databasemodel`.`paymethod` (`Payid`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION)\n" +
                    "ENGINE = InnoDB;");

            con.close();
            System.out.println("Ok");
            return 1;
        } catch (Exception e) {
            System.out.println("occurred error");
            return -1;
        }

    }
    public boolean isCreatedDB() throws Exception{

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", DbConfig.DB_USER, DbConfig.DB_PASSWORD);
            try{
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasemodel", DbConfig.DB_USER, DbConfig.DB_PASSWORD);
                con.close();
                return true;
            } catch (Exception e){
                return false;
            }


    }
    public int tableInitiator() {
        return 1;
    }
}
