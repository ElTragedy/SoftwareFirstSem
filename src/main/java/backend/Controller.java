package backend;

import java.util.*;

public class Controller {

    static AccountDatabase accountDatabase = new AccountDatabase();
    static RoomDatabase roomDatabase = new RoomDatabase();
    static ReservationDatabase reservationDatabase = new ReservationDatabase();
    static Account currentAccount = new Account();

    
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
        System.out.println("account created");
        Controller.setCurrentAccount(a);
        if (size == accountDatabase.getSize()){
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean loadAll(){
        accountDatabase.load("accounts.xml");
        roomDatabase.load("rooms.xml");
        reservationDatabase.load("reservations.xml");
        return true;
    }

    /*
     * CreateRoom
     * This function will create a room and add it to the Room Database,
     * it will return a bool if it was successful or not. it will also probably
     * throw an exception if it didnt work.
     */
    public static boolean createRoom(Room r){
        int size = roomDatabase.getRooms().size();
        roomDatabase.addRoom(r);
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

    public static Reservation getReservation(String reservationId){
        return reservationDatabase.getReservationDetails(reservationId);
    }

    public static Vector<Vector<String>> getAvailableRooms(Date start, Date end, String type){
        //TODO: Make this a function inside of room database

        RoomType enumType = (type.equals("Single King")) ? RoomType.singleKing: (type.equals("Double King")) ? RoomType.doubleQueen: RoomType.suite;
        ArrayList<Room> rooms = roomDatabase.getRooms();
        rooms.removeIf(n -> (!n.roomType.equals(enumType)));

        Vector<Vector<String>> output = new Vector<>();
        for(Room i : reservationDatabase.getAvailableRooms(start, end, rooms)){
            output.add(new Vector<>(List.of(Integer.toString(i.getNumber()), i.getRoomType().toString())));
        }

        return output;
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
        reservationDatabase.save();
        roomDatabase.save();
        return true;
    }

    public static Account setCurrentAccount(Account a){
        currentAccount = a;
        return currentAccount;
    }

    public static Account getCurrentAccount(){
        return currentAccount;
    }

    public static ArrayList<Account> getAllAccounts(){
        return accountDatabase.getAccountList();
    }

    public static boolean resetPassword(Account a){
        //getAccount(a.getEmail(),a.getPassword().toCharArray());
        //a.setPassword("password");
        for(Account acc : accountDatabase.getAccountList()){
            if(a.getEmail().equals(acc.getEmail())){
                acc.setPassword("password");
                return true;
            }
        }

        return false;
    }

    public static boolean deleteAccount(Account a){
        for(Account acc : accountDatabase.getAccountList()){
            if(a.getEmail().equals(acc.getEmail())){
                accountDatabase.removeAccount(acc);
                return true;
            }
        }
        return false;
    }

}

