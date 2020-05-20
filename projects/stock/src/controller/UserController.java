package controller;

import domain.RUser;
import manager.UserManager;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class UserController {

    private UserManager userManager=new UserManager();
    private RUser rUser;

    private Scanner scanner = new Scanner(System.in);

    public UserController(){}

    public void search(){
        System.out.print("Enter user by name or id: ");
        String userName=scanner.nextLine();
        List<String> res=userManager.getUsersByName(userName);
        if(!(res==null)){
            for (int i=0;i<res.size();i++){
                System.out.println(res.get(i));
            }
        } else {
            System.out.println("There is no result!");
        }
    }
    public void insert(){
        System.out.print("Enter firstName: ");
        String firstName=scanner.nextLine();
        System.out.print("Enter lastName: ");
        String lastName=scanner.nextLine();
        System.out.print("Enter DOB: ");
        String dob=scanner.nextLine();
        System.out.print("Enter age: ");
        String age=scanner.nextLine();
        rUser=new RUser(0,firstName,lastName, Date.valueOf(dob),Integer.valueOf(age),0,0,0,0);
        int res=userManager.insertRUser(rUser);
        if(res>0){
            System.out.println("Success!");
        } else {
            System.out.println("Fail!");
        }
    }
    public void update(){
        System.out.print("Enter userId: ");
        String userId=scanner.nextLine();
        System.out.print("Enter firstName: ");
        String firstName=scanner.nextLine();
        System.out.print("Enter lastName: ");
        String lastName=scanner.nextLine();
        System.out.print("Enter DOB: ");
        String dob=scanner.nextLine();
        System.out.print("Enter age: ");
        String age=scanner.nextLine();
        rUser=new RUser(Integer.valueOf(userId),firstName,lastName,Date.valueOf(dob),Integer.valueOf(age),0,0,0,0);
        int res=userManager.updateRUser(rUser);
        if(res>0){
            System.out.println("Success!");
        } else {
            System.out.println("Fail!");
        }
    }
    public void delete(){
        System.out.print("Enter userId: ");
        String userId=scanner.nextLine();
        rUser=new RUser(Integer.valueOf(userId),"","",null,0,0,0,0,0);
        int res=userManager.deleteRUser(rUser);
        if(res>0){
            System.out.println("Success!");
        } else {
            System.out.println("Fail!");
        }
    }
}
