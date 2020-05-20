package manager;

import connector.RUserConnector;
import domain.RUser;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private RUserConnector rUserConnect;

    public UserManager() {
        rUserConnect = new RUserConnector();
    }

    public int insertRUser(RUser rUser){
        return rUserConnect.insertRUser(rUser);
    }
    public int updateRUser(RUser user) {
        return rUserConnect.updateUser(user);
    }
    public int deleteRUser(RUser user) {
        return rUserConnect.deleteUser(user);
    }
    public List<String> getUsersByID(int userId){
        List<RUser> userList = new ArrayList<>();
        userList.add(rUserConnect.getRUserById(userId));
        return getStrListFromUser(userList);
    }
    public List<String> getUsersByName(String name){
        List<RUser> userList = new ArrayList<>();
        userList =rUserConnect.getRUserByName(name);
        return getStrListFromUser(userList);

    }
    public List<String> getStrListFromUser(List<RUser> userList) {

        List<String> userStrList = new ArrayList<>();
        String temp;
        for(int i=0; i<userList.size();i++){
            temp = "RUserid: " + userList.get(i).getrUserID();
            userStrList.add(temp);
            temp = "First Name: " + userList.get(i).getFirstName();
            userStrList.add(temp);
            temp = "Last Name: " + userList.get(i).getLastName();
            userStrList.add(temp);
            temp = "DOB: " + userList.get(i).getBirthDay().toString();
            userStrList.add(temp);
            temp = "Age: " + userList.get(i).getAge();
            userStrList.add(temp);
/*            temp = "Category Id: " + userList.get(i).getCategoryCId();
            userStrList.add(temp);
            temp = ""*/
        }
        return userStrList;

    }
}
