package manager;

import connector.AccountConnector;
import domain.AccountInfo;

public class AccountManager {
    private AccountConnector accountConnector;

    public AccountManager() {
        this.accountConnector = new AccountConnector();
    }
    public int insertAccount(AccountInfo accountInfo) {
        return accountConnector.insertAccount(accountInfo);
    }
    public boolean isCorrectAccount(AccountInfo accountInfo){
        return accountConnector.isRightAccount(accountInfo);
    }
}
