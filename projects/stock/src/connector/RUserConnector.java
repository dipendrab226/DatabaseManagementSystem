package connector;

import dbhelper.DbConfig;
import domain.RUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RUserConnector {
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/databasemodel";
    String sqlQuery;
    public int insertRUser(RUser rUser) {
        /*  `RUserid` INT(10) UNSIGNED NOT NULL,
  `Firstname` VARCHAR(45) NOT NULL,
  `Lastname` VARCHAR(45) NOT NULL,
  `DOB` DATE NOT NULL,
  `Age` INT(11) NOT NULL,
  `Category_Cid` INT(11) NULL,
  `bank_Bankid` INT(11) NULL,
  `perk_Perkid` INT(11) NULL,
  `level_Levelid` INT(11) NULL,*/
 /*       sqlQuery = "INSERT INTO ruser ( RUserid, Firstname, Lastname, DOB, Age, Category_Cid, bank_Bankid, perk_Perkid, level_Levelid)" +
                " VALUES(  +1, '" + rUser.getFirstName() + "', '" + rUser.getLastName()+ "', " + rUser.getBirthDay().toString() + ", " +
                rUser.getAge() + ", " + rUser.getCategoryCId()+ ", " +
                rUser.getBankBankID() + ", "+
                rUser.getPerkPerkID() + ", " +
                rUser.getLevelLevelID() +  ")";*/
        sqlQuery = "SELECT MAX(RUserid) FROM ruser";
        try {
            Connection con = DriverManager.getConnection(CONNECTION_URL, DbConfig.DB_USER,DbConfig.DB_PASSWORD);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);
            rs.absolute(1);
            int maxId = rs.getInt(1) +1;
            String categoryId, bankId, levelId, perkId;
            if(rUser.getCategoryCId() ==0)
                categoryId =null;
            else
                categoryId = String.valueOf(rUser.getCategoryCId());
            if(rUser.getBankBankID()==0)
                bankId =null;
            else
                bankId = String.valueOf(rUser.getBankBankID());
            if(rUser.getPerkPerkID()==0)
                perkId = null;
            else
                perkId = String.valueOf(rUser.getPerkPerkID());
            if(rUser.getLevelLevelID() ==0)
                levelId = null;
            else
                levelId = String.valueOf(rUser.getLevelLevelID());
            sqlQuery =  "INSERT INTO ruser( RUserid, Firstname, Lastname, DOB, Age, " +
                    "Category_Cid, bank_Bankid, perk_Perkid, level_Levelid)" +
                    " VALUES(" + maxId +", '" + rUser.getFirstName() +"', '" +
                    rUser.getLastName()+ "', '" + rUser.getBirthDay().toString() + "', " +
                    rUser.getAge() + ", " + categoryId + ", " + bankId + ", " +
                    perkId + ", " + levelId + ")";
            statement.execute(sqlQuery);
        }
        catch (Exception e) {
            return -1;
        }
        return 1;

    }
    public int updateUser(RUser ruser) {
        String categoryId, bankId, levelId, perkId;
        if(ruser.getCategoryCId() ==0)
            categoryId =null;
        else
            categoryId = String.valueOf(ruser.getCategoryCId());
        if(ruser.getBankBankID()==0)
            bankId =null;
        else
            bankId = String.valueOf(ruser.getBankBankID());
        if(ruser.getPerkPerkID()==0)
            perkId = null;
        else
            perkId = String.valueOf(ruser.getPerkPerkID());
        if(ruser.getLevelLevelID() ==0)
            levelId = null;
        else
            levelId = String.valueOf(ruser.getLevelLevelID());

        sqlQuery = "UPDATE ruser SET Firstname = '"+ ruser.getFirstName()+
                "', Lastname = '" + ruser.getLastName() +
                "', DOB = '" + ruser.getBirthDay().toString() +
                "', Age = " + ruser.getAge() +
                ", Category_Cid = " + categoryId +
                ", bank_Bankid = " + bankId +
                ", perk_Perkid = " +  perkId +
                ", level_Levelid = " + levelId +
                " WHERE RUserid = " + ruser.getrUserID();
        try {
            Connection con = DriverManager.getConnection(CONNECTION_URL, DbConfig.DB_USER,DbConfig.DB_PASSWORD);
            Statement statement = con.createStatement();
            statement.execute(sqlQuery);
        }
        catch (Exception e) {
            return -1;
        }
        return 1;

    }

    public int deleteUser(RUser ruser) {
        sqlQuery = "DELETE FROM ruser WHERE RUserid = " + ruser.getrUserID() ;
        try {
            Connection con = DriverManager.getConnection(CONNECTION_URL, DbConfig.DB_USER,DbConfig.DB_PASSWORD);
            Statement statement = con.createStatement();
            statement.execute(sqlQuery);
        }
        catch (Exception e) {
            return -1;
        }
        return 1;
    }

    public RUser getRUserById(int id) {
                        /*  `RUserid` INT(10) UNSIGNED NOT NULL,
  `Firstname` VARCHAR(45) NOT NULL,
  `Lastname` VARCHAR(45) NOT NULL,
  `DOB` DATE NOT NULL,
  `Age` INT(11) NOT NULL,
  `Category_Cid` INT(11) NULL,
  `bank_Bankid` INT(11) NULL,
  `perk_Perkid` INT(11) NULL,
  `level_Levelid` INT(11) NULL,*/
        sqlQuery = "SELECT * FROM ruser WHERE RUserid =" + id;
        RUser rUser;
        try {
            Connection con = DriverManager.getConnection(CONNECTION_URL, DbConfig.DB_USER,DbConfig.DB_PASSWORD);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            resultSet.absolute(1);
            rUser = new RUser(resultSet.getInt("RUserid"), resultSet.getString("Firstname"),
                    resultSet.getString("Lastname"), resultSet.getDate("DOB"),
                    resultSet.getInt("Age"), resultSet.getInt("Category_Cid"),
                    resultSet.getInt("bank_Bankid"), resultSet.getInt("perk_Perkid"),
                    resultSet.getInt("level_Levelid"));
        }
        catch (Exception e) {
            return null;
        }

        return rUser;
    }
    public List<RUser> getRUserByName(String name) {
         List<RUser> userList = new ArrayList<>();
        sqlQuery = "SELECT * FROM ruser WHERE Firstname = '" + name + "'";
        RUser rUser;
        try {
            Connection con = DriverManager.getConnection(CONNECTION_URL, DbConfig.DB_USER,DbConfig.DB_PASSWORD);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                rUser = new RUser(resultSet.getInt("RUserid"), resultSet.getString("Firstname"),
                        resultSet.getString("Lastname"), resultSet.getDate("DOB"),
                        resultSet.getInt("Age"), resultSet.getInt("Category_Cid"),
                        resultSet.getInt("bank_Bankid"), resultSet.getInt("perk_Perkid"),
                        resultSet.getInt("level_Levelid"));
                userList.add(rUser);
            }
            sqlQuery = "SELECT * FROM ruser WHERE Lastname = '" + name + "'";
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                rUser = new RUser(resultSet.getInt("RUserid"), resultSet.getString("Firstname"),
                        resultSet.getString("Lastname"), resultSet.getDate("DOB"),
                        resultSet.getInt("Age"), resultSet.getInt("Category_Cid"),
                        resultSet.getInt("bank_Bankid"), resultSet.getInt("perk_Perkid"),
                        resultSet.getInt("level_Levelid"));
                userList.add(rUser);
            }
        }
        catch (Exception e) {
            return null;
        }

         return userList;
    }
    public List<RUser> getRUserByPerkId(int perkID) {
        List<RUser> userList = new ArrayList<>();
        sqlQuery = "SELECT * FROM ruser WHERE perk_Perkid =" + perkID;
        RUser rUser;
        try {
            Connection con = DriverManager.getConnection(CONNECTION_URL, DbConfig.DB_USER,DbConfig.DB_PASSWORD);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.absolute(1)) {
                rUser = new RUser(resultSet.getInt("RUserid"), resultSet.getString("Firstname"),
                        resultSet.getString("Lastname"), resultSet.getDate("DOB"),
                        resultSet.getInt("Age"), resultSet.getInt("Category_Cid"),
                        resultSet.getInt("bank_Bankid"), resultSet.getInt("perk_Perkid"),
                        resultSet.getInt("level_Levelid"));
                userList.add(rUser);
            }
        }
        catch (Exception e) {
            return null;
        }

        return userList;
    }
    public List<RUser> getRUserByAge(int age) {
        List<RUser> userList = new ArrayList<>();
        sqlQuery = "SELECT * FROM ruser WHERE Age =" + age;
        RUser rUser;
        try {
            Connection con = DriverManager.getConnection(CONNECTION_URL, DbConfig.DB_USER,DbConfig.DB_PASSWORD);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.absolute(1)) {
                rUser = new RUser(resultSet.getInt("RUserid"), resultSet.getString("Firstname"),
                        resultSet.getString("Lastname"), resultSet.getDate("DOB"),
                        resultSet.getInt("Age"), resultSet.getInt("Category_Cid"),
                        resultSet.getInt("bank_Bankid"), resultSet.getInt("perk_Perkid"),
                        resultSet.getInt("level_Levelid"));
                userList.add(rUser);
            }
        }
        catch (Exception e) {
            return null;
        }

        return userList;
    }
    public List<RUser> getRUserByBirth(Date birthDay) {
        List<RUser> userList = new ArrayList<>();
        sqlQuery = "SELECT * FROM ruser WHERE DOB =" + birthDay;
        RUser rUser;
        try {
            Connection con = DriverManager.getConnection(CONNECTION_URL, DbConfig.DB_USER,DbConfig.DB_PASSWORD);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.absolute(1)) {
                rUser = new RUser(resultSet.getInt("RUserid"), resultSet.getString("Firstname"),
                        resultSet.getString("Lastname"), resultSet.getDate("DOB"),
                        resultSet.getInt("Age"), resultSet.getInt("Category_Cid"),
                        resultSet.getInt("bank_Bankid"), resultSet.getInt("perk_Perkid"),
                        resultSet.getInt("level_Levelid"));
                userList.add(rUser);
            }
        }
        catch (Exception e) {
            return null;
        }
        return userList;
    }
}
