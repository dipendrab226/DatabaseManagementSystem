package domain;

import java.sql.Date;

public class RUser {
    /*
    *   `RUserid` INT(10) UNSIGNED NOT NULL,
  `Firstname` VARCHAR(45) NOT NULL,
  `Lastname` VARCHAR(45) NOT NULL,
  `DOB` DATE NOT NULL,
  `Age` INT(11) NOT NULL,
  `Category_Cid` INT(11) NULL,
  `bank_Bankid` INT(11) NULL,
  `perk_Perkid` INT(11) NULL,
  `level_Levelid` INT(11) NULL,*/
    private int rUserID;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private int age;
    private int categoryCId;
    private int bankBankID;
    private int perkPerkID;
    private int levelLevelID;

    public RUser() {

    }
    public RUser(int rUserID, String firstName, String lastName, Date birthDay,
                 int age, int categoryCId, int bankBankID, int perkPerkID, int levelLevelID) {
        this.rUserID = rUserID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.age= age;
        this.categoryCId = categoryCId;
        this.bankBankID = bankBankID;
        this.perkPerkID = perkPerkID;
        this.levelLevelID = levelLevelID;
    }
    public RUser(int rUserID, String firstName, String lastName, Date birthDay,
                 int age) {
        this.rUserID = rUserID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.age= age;
    }

    public int getrUserID() {
        return rUserID;
    }

    public void setrUserID(int rUserID) {
        this.rUserID = rUserID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCategoryCId() {
        return categoryCId;
    }

    public void setCategoryCId(int categoryCId) {
        this.categoryCId = categoryCId;
    }

    public int getBankBankID() {
        return bankBankID;
    }

    public void setBankBankID(int bankBankID) {
        this.bankBankID = bankBankID;
    }

    public int getPerkPerkID() {
        return perkPerkID;
    }

    public void setPerkPerkID(int perkPerkID) {
        this.perkPerkID = perkPerkID;
    }

    public int getLevelLevelID() {
        return levelLevelID;
    }

    public void setLevelLevelID(int levelLevelID) {
        this.levelLevelID = levelLevelID;
    }
}
