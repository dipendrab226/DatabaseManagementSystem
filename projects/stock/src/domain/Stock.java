package domain;

public class Stock {
    /*  `Stockid` INT(11) NOT NULL,
  `Stockvalue` INT(11) NOT NULL,
  `stockname` VARCHAR(45) NULL,*/
    private int stockId;
    private int stockValue;
    private String stockName;
    public Stock() {

    }
    public Stock(int stockId, int stockValue, String stockName) {
        this.stockId = stockId;
        this.stockValue = stockValue;
        this.stockName = stockName;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getStockValue() {
        return stockValue;
    }

    public void setStockValue(int stockValue) {
        this.stockValue = stockValue;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
}
