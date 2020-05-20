package manager;

import connector.StockConnector;
import domain.Stock;

import java.util.ArrayList;
import java.util.List;

public class StockManager {
    private StockConnector stockConnector;

    public StockManager(){
        this.stockConnector = new StockConnector();

    }

    public int insertStock(Stock stock) {
        return stockConnector.insertStock(stock);
    }
    public int updateStock(Stock stock) {
        return stockConnector.updateStock(stock);
    }
    public int deleteStock(Stock stock) {
        return stockConnector.deleteStock(stock);
    }
    public List<String> getStocksByName(String name){
        List<Stock> stockList = stockConnector.getStockByName(name);
        return getStrListFromStock(stockList);
    }
    public List<String> getStocksByValue(int value){
        List<Stock> stockList = stockConnector.getStocksByValue(value);
        return getStrListFromStock(stockList);
    }
    public List<String> getStocksById(int id){
        List<Stock> stockList = new ArrayList<>();
        stockList.add(stockConnector.getStockById(id));
        return getStrListFromStock(stockList);
    }


    public List<String> getStrListFromStock(List<Stock> stockList) {
        List<String> stringList = new ArrayList<>();
        String temp;
        /*  `Stockid` INT(11) NOT NULL,
  `Stockvalue` INT(11) NOT NULL,
  `stockname` VARCHAR(45) NULL,*/
        for(int i=0; i<stockList.size();i++){
            temp = "stockid: " + stockList.get(i).getStockId();
            stringList.add(temp);
            temp = "stockname: " + stockList.get(i).getStockName();
            stringList.add(temp);
            temp = "stockvalue: " + stockList.get(i).getStockValue();
            stringList.add(temp);
        }
        return stringList;
    }
}
