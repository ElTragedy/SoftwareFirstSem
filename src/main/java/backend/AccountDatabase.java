package backend;

import java.util.ArrayList;
import jakarta.xml.bind.*;
import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "accounts")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountDatabase {

    public void load(){
        xmlAccount xml = new xmlAccount();
        AccountDatabase a = xml.load("accounts.xml");
        this.accountList = a.getAccountList();
    }

    @XmlElementWrapper(name = "users")
    @XmlElement(name = "account")
    private ArrayList<Account> accountList;

    public AccountDatabase() {
        accountList = new ArrayList<Account>();
    }

    public AccountDatabase(ArrayList<Account> accountList) {
        this.accountList = accountList;
    }

    public void setAccountList(ArrayList<Account> accountList) {
        this.accountList = accountList;
    }

    public int getSize() {
        return accountList.size();
    }

    public Account getAccount(int i) {
        return accountList.get(i);
    }

    public void insertAccount(Account account) {
        accountList.add(account);
    }

    public ArrayList<Account> getAccountList() {
        return accountList;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accountList == null) ? 0 : accountList.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AccountDatabase other = (AccountDatabase) obj;
        if (accountList == null) {
            if (other.accountList != null)
                return false;
        } else if (!accountList.equals(other.accountList))
            return false;
        return true;
    }

}



