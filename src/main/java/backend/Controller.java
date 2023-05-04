package backend;

import java.text.SimpleDateFormat;
import javax.mail.MessagingException;
import java.util.*;

public class Controller {

    static AccountDatabase accountDatabase = new AccountDatabase();
    static RoomDatabase roomDatabase = new RoomDatabase();
    static ReservationDatabase reservationDatabase = new ReservationDatabase();
    static Account currentAccount = new Account();

    static EmailService emailService = new EmailService(System.getenv("EMAIL"), System.getenv("PASSWORD"));

    
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
    public static boolean createReservation(String username, int roomNumber, boolean payed, Date checkIn, Date checkOut){
        return reservationDatabase.reserveRoom(new Reservation(username, roomDatabase.getRoom(roomNumber), payed, checkIn, checkOut));
    }


    public static Room getRoom(int roomNum){
        return roomDatabase.getRoom(roomNum);
    }

    public static void updateRoom(int roomNum, Room room){
        roomDatabase.updateRoom(roomNum, room);
    }

    public static boolean roomExists(int roomNum){
        return roomDatabase.roomExists(roomNum);
    }

    public static Reservation getReservation(String reservationId){
        return reservationDatabase.getReservationDetails(reservationId);
    }

    public static Vector<Vector<String>> getAvailableRooms(Date start, Date end, String type){
        //TODO: Make this a function inside of room database

        RoomType enumType = (type.equals("Single King")) ? RoomType.singleKing: (type.equals("Double Queen")) ? RoomType.doubleQueen: RoomType.suite;
        ArrayList<Room> rooms = roomDatabase.getRooms();
        rooms.removeIf(n -> (!n.roomType.equals(enumType)));

        Vector<Vector<String>> output = new Vector<>();
        for(Room i : rooms){
            String roomType = i.getRoomType().equals(RoomType.suite) ? "Suite" : i.roomType.equals(RoomType.singleKing) ? "Single King" : "Double Queen";
            String roomCondition = String.valueOf(i.getRoomCondition());
            output.add(new Vector<>(List.of(Integer.toString(i.getNumber()), roomType, roomCondition)));
        }

        return output;
    }

    public static Vector<Vector<String>> getReservationsByEmail(String email){
        ArrayList<Reservation> reservations = reservationDatabase.getReservationsByEmail(email);

        Vector<Vector<String>> out = new Vector<>();
        for(Reservation i : reservations){
            Room room = roomDatabase.getRoom(i.getRoomNumber());

            String roomType = room.getRoomType().equals(RoomType.suite) ? "Suite" : room.roomType.equals(RoomType.singleKing) ? "Single King" : "Double Queen";

            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

            out.add(new Vector<>(List.of(Integer.toString(room.number), roomType, dateFormatter.format(i.getCheckIn()), dateFormatter.format(i.getCheckOut()))));
        }

        return out;
    }

    public static Account getAccount(String email){
        Account account = null;
        for(Account acc : accountDatabase.getAccountList()) {
            if(acc.getEmail().equals(email)) {
                account = acc;
                break;
            }
        }
        return account;
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

    public static boolean accountExists(String email){
        boolean exists = false;
        for(Account acc : accountDatabase.getAccountList()) {
            if(acc.getEmail().equals(email)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    public static boolean accountExists(String email, char[] password){
        boolean exists = false;
        for(Account acc : accountDatabase.getAccountList()) {
            if(acc.getEmail().equals(email)&& Arrays.equals(acc.getPassword().toCharArray(), password)) {
                exists = true;
                break;
            }
        }
        return exists;
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


    public static void sendEmail(String toEmail, String subject, String body) throws MessagingException {
        emailService.send(toEmail, subject, body);
    }

    public static ArrayList<Account> getAllAccounts(){
        return accountDatabase.getAccountList();
    }

    public static boolean resetPassword(Account a, String newPassword){
        //getAccount(a.getEmail(),a.getPassword().toCharArray());
        //a.setPassword("password");
        for(Account acc : accountDatabase.getAccountList()){
            if(a.getEmail().equals(acc.getEmail())){
                acc.setPassword(newPassword);
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

