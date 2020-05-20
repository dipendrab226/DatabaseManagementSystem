package connector;

import dbhelper.DbConfig;
import domain.RUser;
import domain.Stock;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockConnector {
    private String sqlQuery;


    public int insertStock(Stock stock) {
        /*  `Stockid` INT(11) NOT NULL,
  `Stockvalue` INT(11) NOT NULL,
  `stockname` VARCHAR(45) NULL,*/
        /*INSERT INTO lending(book_id, borrow_date, return_date, borrow_member, return_state)
        VALUES((SELECT id FROM book where isbn =123457), CURRENT_DATE+16, NULL, 1234,0);*/
        sqlQuery = "SELECT MAX(Stockid) FROM stock ";
/*        sqlQuery = "INSERT INTO stock ( Stockid,Stockvalue, stockname) VALUES("
                + ("SELECT MAX(Stockid) FROM stock +" +1) +", " + stock.getStockValue() + ", '" + stock.getStockName()+
                "');";*/
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasemodel", DbConfig.DB_USER,DbConfig.DB_PASSWORD);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);
            rs.absolute(1);
            int maxId = rs.getInt(1);
            sqlQuery = "INSERT INTO stock ( Stockid,Stockvalue, stockname) VALUES("
                    + (maxId +1) + ", " + stock.getStockValue() + ", '" + stock.getStockName()+
                    "');";
            statement.execute(sqlQuery);

        }
        catch (Exception e) {
            return -1;
        }
        return 1;
    }
    public int updateStock(Stock stock) {
        /*UPDATE Customers SET ContactName = 'Alfred Schmidt', City= 'Frankfurt' WHERE CustomerID = 1;*/
        sqlQuery = "UPDATE stock SET Stockvalue = " + stock.getStockValue() + ", stockname = '" + stock.getStockName()+
                "' WHERE Stockid= " + stock.getStockId();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasemodel", DbConfig.DB_USER,DbConfig.DB_PASSWORD);
            Statement statement = con.createStatement();
            statement.execute(sqlQuery);
        }catch (Exception e){
            return -1;
        }

        return 1;
    }

    public int deleteStock(Stock stock) {
        /*DELETE From library_user where person_id = 3;*/
        sqlQuery = "DELETE FROM stock WHERE Stockid = " + stock.getStockId();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasemodel", DbConfig.DB_USER,DbConfig.DB_PASSWORD);
            Statement statement = con.createStatement();
            statement.execute(sqlQuery);
        }catch (Exception e){
            return -1;
        }
        return 1;
    }

    public Stock getStockById(int id) {
        Stock stock = new Stock();
        sqlQuery = "SELECT * FROM stock WHERE Stockid =" + id;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasemodel", DbConfig.DB_USER,DbConfig.DB_PASSWORD);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                stock = new Stock(resultSet.getInt("Stockid"), resultSet.getInt("Stockvalue"),
                        resultSet.getString("stockname"));
            }

        }catch (Exception e){
            return null;
        }

        return stock;
    }
    public List<Stock> getStockByName(String name) {
        List<Stock> stockList = new ArrayList<>();
        sqlQuery = "SELECT * FROM stock WHERE stockname = '" + name + "'";
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasemodel", DbConfig.DB_USER,DbConfig.DB_PASSWORD);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                Stock stock = new Stock(resultSet.getInt("Stockid"), resultSet.getInt("Stockvalue"),
                        resultSet.getString("stockname"));
                stockList.add(stock);
            }

        }catch (Exception e){
            return null;
        }

        return stockList;
    }
    public List<Stock> getStocksByValue(int value){
        List<Stock> stockList = new ArrayList<>();
        sqlQuery = "SELECT * FROM stock WHERE Stockvalue =" + value;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasemodel", DbConfig.DB_USER,DbConfig.DB_PASSWORD);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                Stock stock = new Stock(resultSet.getInt("Stockid"), resultSet.getInt("Stockvalue"),
                        resultSet.getString("stockname"));
                stockList.add(stock);
            }

        }catch (Exception e){
            return null;
        }
        return stockList;

    }

}
