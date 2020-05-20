package main;

import connector.DatabaseCreator;
import controller.AccountController;
import controller.StockController;
import controller.UserController;
import java.util.Scanner;

public class Main {
    static AccountController accountController = new AccountController();
    static UserController userController = new UserController();
    static StockController stockController = new StockController();
    static DatabaseCreator databaseCreator=new DatabaseCreator();
    static Scanner scanner = new Scanner(System.in);
    static String menuNum, subMenuNum;
    public static void main(String[] args) {
        try {
            if(!databaseCreator.isCreatedDB()){
                databaseCreator.dbCreator();
            }
        }
        catch (Exception e){
        }
        displayMenu();
        while (true) {
            menuNum = scanner.nextLine();
            switch (menuNum) {
                case "1":
                    accountController.create();
                    break;
                case "2":
                    accountController.login();
                    break;
                case "3":
                    search();
                    break;
                case "4":
                    insert();
                    break;
                case "5":
                    update();
                    break;
                case "6":
                    delete();
                    break;
                case "7":
                    break;
                default:
                    System.out.println("Invalid statement!");
                    displayMenu();
                    break;
            }
            if (menuNum.equals("7")) {
                System.out.println("Program finished");
                break;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("User Menu");
        System.out.println("1. Create Account");
        System.out.println("2. Login");
        System.out.println("3. Search");
        System.out.println("4. Insert");
        System.out.println("5. Update");
        System.out.println("6. Delete");
        System.out.println("7. Exit");
    }

    private static void displaySubMenu(String menu) {
        System.out.println("1. " + menu + " stock");
        System.out.println("2. " + menu + " user");
        System.out.println("3. Exit");
    }

    private static void search() {
        displaySubMenu("Search");
        while (true) {
            subMenuNum = scanner.nextLine();
            switch (subMenuNum) {
                case "1":
                    stockController.search();
                    break;
                case "2":
                    userController.search();
                    break;
                case "3":
                    break;
                default:
                    System.out.println("Invalid statement!");
                    displaySubMenu("Search");
                    break;
            }
            if (subMenuNum.equals("3")) {
                System.out.println("SubMenu collapsed");
                break;
            }
        }
    }

    private static void insert() {
        displaySubMenu("Insert");
        while (true) {
            subMenuNum = scanner.nextLine();
            switch (subMenuNum) {
                case "1":
                    stockController.insert();
                    break;
                case "2":
                    userController.insert();
                    break;
                case "3":
                    break;
                default:
                    System.out.println("Invalid statement!");
                    displaySubMenu("Insert");
                    break;
            }
            if (subMenuNum.equals("3")) {
                System.out.println("SubMenu collapsed");
                break;
            }
        }
    }

    private static void update() {
        displaySubMenu("Update");
        while (true) {
            subMenuNum = scanner.nextLine();
            switch (subMenuNum) {
                case "1":
                    stockController.update();
                    break;
                case "2":
                    userController.update();
                    break;
                case "3":
                    break;
                default:
                    System.out.println("Invalid statement!");
                    displaySubMenu("Update");
                    break;
            }
            if (subMenuNum.equals("3")) {
                System.out.println("SubMenu collapsed");
                break;
            }
        }
    }

    private static void delete() {
        displaySubMenu("Delete");
        while (true) {
            subMenuNum = scanner.nextLine();
            switch (subMenuNum) {
                case "1":
                    stockController.delete();
                    break;
                case "2":
                    userController.delete();
                    break;
                case "3":
                    break;
                default:
                    System.out.println("Invalid statement!");
                    displaySubMenu("Delete");
                    break;
            }
            if (subMenuNum.equals("3")) {
                System.out.println("SubMenu collapsed");
                break;
            }
        }
    }
}
