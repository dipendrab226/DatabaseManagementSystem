package controller;

import domain.Stock;
import manager.StockManager;

import java.util.List;
import java.util.Scanner;

public class StockController {
    private StockManager stockManager=new StockManager();
    private Stock stock;
    private Scanner scanner = new Scanner(System.in);

    public StockController(){}

    public void search(){
        System.out.print("Enter stock by name: ");
        String stockName=scanner.nextLine();
        List<String> res= stockManager.getStocksByName(stockName);
        if(!(res==null)){
            for(int i=0;i<res.size();i++){
                System.out.println(res.get(i));
            }
        } else {
            System.out.println("There is no result!");
        }
    }
    public void insert(){
        System.out.print("Enter stockname: ");
        String stockName=scanner.nextLine();
        System.out.print("Enter stockid: ");
        String stockId=scanner.nextLine();
        System.out.print("Enter stockvalue: ");
        String stockValue=scanner.nextLine();
        stock=new Stock(Integer.valueOf(stockId),Integer.valueOf(stockValue),stockName);
        int res=stockManager.insertStock(stock);
        if(res>0){
            System.out.println("Success!");
        } else {
            System.out.println("Fail!");
        }

    }
    public void update(){
        System.out.print("Enter stockid: ");
        String stockId=scanner.nextLine();
        System.out.print("Enter stockname: ");
        String stockName=scanner.nextLine();
        System.out.print("Enter stockvalue: ");
        String stockValue=scanner.nextLine();
        stock=new Stock(Integer.valueOf(stockId),Integer.valueOf(stockValue),stockName);
        int res=stockManager.updateStock(stock);
        if(res>0){
            System.out.println("Success!");
        } else {
            System.out.println("Fail!");
        }

    }
    public void delete(){
        System.out.print("Enter stockid: ");
        String stockId=scanner.nextLine();
        stock=new Stock(Integer.valueOf(stockId),Integer.valueOf(0),"");
        int res=stockManager.deleteStock(stock);
        if(res>0){
            System.out.println("Success!");
        } else {
            System.out.println("Fail!");
        }
    }
}
