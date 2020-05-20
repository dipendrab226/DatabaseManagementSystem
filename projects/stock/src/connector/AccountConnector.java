package connector;

import dbhelper.DbConfig;
import domain.AccountInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccountConnector {
    String sqlQuery;

    public AccountInfo getAccountInfo(int accountID) {
        sqlQuery = "SELECT * FROM account WHERE Accountid=" + accountID;
        AccountInfo accountInfo;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasemodel", DbConfig.DB_USER, DbConfig.DB_PASSWORD);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);
            rs.absolute(1);
            accountInfo = new AccountInfo(rs.getInt("Accountid"), rs.getString("Email"),
                    rs.getString("password"), rs.getInt("ruser_RUserid"));

            con.close();
            return accountInfo;
        } catch (Exception e) {
            return null;
        }

    }

    public int insertAccount(AccountInfo accountInfo) {
        sqlQuery = "INSERT INTO account(  email, password, ruser_RUserid) VALUES('" + accountInfo.getEmail() +
                "', '" + accountInfo.getPassword() + "', " + accountInfo.getUserID() + ")";
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasemodel", DbConfig.DB_USER, DbConfig.DB_PASSWORD);
            Statement statement = con.createStatement();
            statement.execute(sqlQuery);
            con.close();
        } catch (Exception e) {
            return -1;
        }

        return 1;
    }

    public boolean isRightAccount(AccountInfo accountInfo) {

        sqlQuery = "SELECT Accountid FROM account WHERE Email = '" + accountInfo.getEmail() + "' AND " +
                "Password = '" + accountInfo.getPassword() + "' AND " +
                "ruser_RUserid = " + accountInfo.getUserID();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasemodel", DbConfig.DB_USER, DbConfig.DB_PASSWORD);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            return resultSet.next();

        } catch (Exception e) {
            return false;
        }
    }
}
