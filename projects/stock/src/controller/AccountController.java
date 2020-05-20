package controller;

import domain.AccountInfo;
import manager.AccountManager;

import java.io.Console;
import java.util.Scanner;

public class AccountController {
    private String userId;
    private String email;
    private String password;
    private String confirm;

    private Scanner scanner = new Scanner(System.in);
    private AccountManager accountManager=new AccountManager();
    private AccountInfo accountInfo;

    public AccountController() {
    }

    public void create() {

        System.out.println("Input your UserID");
        userId = scanner.nextLine();
        System.out.println("Input your Email");
        email = scanner.nextLine();
        while (true) {
            System.out.println("Input your password");
            password=scanner.nextLine();
            System.out.println("Confirm your password");
            confirm = scanner.nextLine();
            if (password.equals(confirm)) {
                break;
            } else {
                System.out.println("Your password incorrect! Retry!");
            }
        }
        accountInfo=new AccountInfo(1,email,password,Integer.valueOf(userId));
        int res=accountManager.insertAccount(accountInfo);
        if(res>0){
            System.out.println("Success!");
        } else {
            System.out.println("Fail!");
        }
    }

    public void login() {
            System.out.println("Input your UserID");
            userId = scanner.nextLine();
            System.out.println("Input your Email");
            email = scanner.nextLine();
            System.out.println("Input your password");
            password = scanner.nextLine();
            accountInfo=new AccountInfo(0,email,password,Integer.valueOf(userId));
            boolean res=accountManager.isCorrectAccount(accountInfo);
            if (res) {
                System.out.println("Login Success!");
            } else {
                System.out.println("Invalid Account! Retry!");
            }
    }
}
