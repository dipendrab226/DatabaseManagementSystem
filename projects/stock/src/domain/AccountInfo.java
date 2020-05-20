package domain;

public class AccountInfo {
    /*  `Accountid` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Email` VARCHAR(45) NOT NULL,
  `Paasword` VARCHAR(45) NOT NULL,
  `ruser_RUserid` INT(10) UNSIGNED NOT NULL,*/


    private int accountID;
    private String email;
    private String password;
    private int userID;
    public AccountInfo(int accountID, String email, String password, int userID){
        this.accountID = accountID;
        this.email = email;
        this.password = password;
        this.userID = userID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
