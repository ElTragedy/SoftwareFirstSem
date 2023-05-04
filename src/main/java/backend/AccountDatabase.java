package backend;

import java.util.ArrayList;
import jakarta.xml.bind.*;
import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "accounts")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountDatabase {

    @XmlElementWrapper(name = "users")
    @XmlElement(name = "account")
    private ArrayList<Account> accountList;

    /**
     * Loads the account database from a specified XML file
     * @param s The name of the file to load from
     */
    public void load(String s){
        AccountDatabase a = xmlAccount.load(s);
        this.accountList = a.getAccountList();
    }

    /**
     * Saves the account database to an XML file and prints a confirmation message
     */
    public void save(){
        xmlAccount.doSave("accounts.xml", this);
        System.out.println("Saved to accounts.xml");
    }

    /**
     * Constructs a new, empty account database
     */
    public AccountDatabase() {
        accountList = new ArrayList<Account>();
    }

    /**
     * Removes the specified account from the account list
     * @param account The account to be removed
     */
    public void removeAccount(Account account) {
        accountList.remove(account);
    }

    /**
     * Constructs an account database with the specified account list
     * @param accountList The account list to be used
     */
    public AccountDatabase(ArrayList<Account> accountList) {
        this.accountList = accountList;
    }

    /**
     * Sets the account list to the specified account list
     * @param accountList The account list to be set
     */
    public void setAccountList(ArrayList<Account> accountList) {
        this.accountList = accountList;
    }

    /**
     * Returns the number of accounts in the account list
     * @return The number of accounts in the account list
     */
    public int getSize() {
        return accountList.size();
    }

    /**
     * Returns the account at the specified index
     * @param i The index of the account to be returned
     * @return The account at the specified index
     */
    public Account getAccount(int i) {
        return accountList.get(i);
    }

    /**
     * Inserts the specified account into the account list
     * @param account The account to be inserted
     */
    public void insertAccount(Account account) {
        accountList.add(account);
    }

    /**
     * Returns the account list
     * @return The account list
     */
    public ArrayList<Account> getAccountList() {
        return accountList;
    }

    /**
     * Prints all the accounts in the account list to the console
     */
    public void printAll(){
        for (Account account : accountList) {
            System.out.println(account);
        }
    }

    /**
     * Calculates the hash code value for this AccountDatabase object. The hash code value is generated using the
     * accountList field. If the accountList is null, the hash code value will be 0.
     *
     * @return the hash code value for this AccountDatabase object
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accountList == null) ? 0 : accountList.hashCode());
        return result;
    }

    /**
     * Indicates whether some other object is "equal to" this one. The equality is determined based on the accountList
     * field. If the other object is not an instance of AccountDatabase or if the accountList of the other object is not
     * equal to this accountList, then the method returns false.
     *
     * @param obj the object to compare for equality
     * @return true if this object is equal to the specified object, false otherwise
     */
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



