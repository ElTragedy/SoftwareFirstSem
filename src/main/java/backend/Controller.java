package backend;

import java.util.Arrays;

public class Controller {

    static AccountDatabase accountDatabase = new AccountDatabase();
    static RoomDatabase roomDatabase = new RoomDatabase();
    static ReservationDatabase reservationDatabase = new ReservationDatabase();

    
    //this will be our main function. This will speak to everything else.
    //this class will class on the others in order to do stuff.

    public static void main(String[] args) {

        System.out.println("hello world");
        //declaration of the AccountDatabase class
        //this calls the xml function from Account database to load the csv
        accountDatabase.load("accounts.xml");
        roomDatabase.load("rooms.xml");
        if(accountDatabase.getSize() == 0){
            System.out.println("no accounts");
        }
        else{
            System.out.println("accounts exist");
        }

        if(roomDatabase.getSize() == 0){
            System.out.println("no rooms");
        }
        else{
            System.out.println("rooms exist");
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

    /*
     * createReservation
     * This function will create a reservation and it will attempt to add it 
     * to the database. This is a static class that will be called by the 
     * black box UI.
     */
    public static boolean createReservation(Reservation r){
        int size = reservationDatabase.getSize();
        return reservationDatabase.reserveRoom(r); 
    }


    public static Room getRoom(/* parameters */){
        //filler
        Room a = null;
        return a;
    }

    public static Room getReservation(/* parameters */){
        //filler
        Room a = null;
        return a;
    }

    public static Account getAccount(String email, char[] password){
        Account account = null;
        for(Account acc : accountDatabase.getAccountList()) {
            if(acc.getEmail().equals(email) && Arrays.equals(acc.getPassword().toCharArray(), password)) {
                account = acc;
                break;
            }
        }
        return account;
    }
    
    public static boolean saveAll(){
        accountDatabase.save();
        reservationDatabase.storeDatabase();
        roomDatabase.save();
        
        
        return true;
    }
    
}

