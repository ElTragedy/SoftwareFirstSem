package backend;

import java.io.IOException;


public class Controller {

    static AccountDatabase accountDatabase = new AccountDatabase();
    static RoomDatabase roomDatabase;
    static {
        try {
            roomDatabase = new RoomDatabase();
        } catch (IOException e) {
            System.out.println("RoomDatabase failed to load");
        }
    }
    //this will be our main function. This will speak to everything else.
    //this class will class on the others in order to do stuff.

    public static void main(String[] args) {


        System.out.println("hello world");
        //declaration of the AccountDatabase class
        //this calls the xml function from Account database to load the csv
        accountDatabase.load("accounts.xml");
        if(accountDatabase.getSize() == 0){
            System.out.println("no accounts");
        }
        else{
            System.out.println("accounts exist");
        }
        try{
            roomDatabase.printAll();
        }
        catch (Exception e){
            System.out.println("no accounts");
        }
    
    }

    /*
     * CreateAccount
     * This function will create an account and add it to the Account Database,
     * it will return a bool if it was successful or not.
     */
    public static boolean createAccount(Account a){
        int size = accountDatabase.getSize();
        accountDatabase.insertAccount(a);
        if (size == accountDatabase.getSize()){
            return false;
        }
        else{
            return true;
        }
    }

    /*
     * CreateRoom
     * This function will create a room and add it to the Room Database,
     * it will return a bool if it was successful or not. it will also probably
     * throw an exception if it didnt work.
     */
    public static boolean createRoom(Room r){
        int size = roomDatabase.getRooms().size();
        try {
            roomDatabase.addRoom(r);
        } catch (Exception e) {
            return false;
        }
        if (size == roomDatabase.getRooms().size()){
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean createReservation(Reservation r){
        return false;
    }

    
}

