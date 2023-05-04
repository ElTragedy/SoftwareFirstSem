package backend;

import java.text.SimpleDateFormat;
import javax.mail.MessagingException;

import frontend.UIBlackBox;

import java.util.*;

public class Controller {

    static AccountDatabase accountDatabase = new AccountDatabase();
    static RoomDatabase roomDatabase = new RoomDatabase();
    static ReservationDatabase reservationDatabase = new ReservationDatabase();
    static Account currentAccount = new Account();

    static EmailService emailService = new EmailService("softarechefs@outlook.com", "$oftwareChefs!");

    
    //this will be our main function. This will speak to everything else.
    //this class will class on the others in order to do stuff.

    public static void main(String[] args) {

        System.out.println("hello world");
        //declaration of the AccountDatabase class
        //this calls the xml function from Account database to load the csv
        accountDatabase.load("accounts.xml");
        roomDatabase.load("rooms.xml");
        reservationDatabase.load("reservations.xml");

        UIBlackBox.startProgram();
    
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

    /**
     * Retrieves the Room object corresponding to the given room number from the RoomDatabase.
     *
     * @param roomNum the room number of the room to retrieve
     * @return the Room object corresponding to the given room number, or null if it doesn't exist
     */
    public static Room getRoom(int roomNum){
        return roomDatabase.getRoom(roomNum);
    }

    /**
     * Updates the Room object corresponding to the given room number in the RoomDatabase.
     *
     * @param roomNum the room number of the room to update
     * @param room the updated Room object
     */
    public static void updateRoom(int roomNum, Room room){
        roomDatabase.updateRoom(roomNum, room);
    }

    /**
     * Checks whether a room with the given room number exists in the RoomDatabase.
     *
     * @param roomNum the room number to check for existence
     * @return true if a room with the given room number exists, false otherwise
     */
    public static boolean roomExists(int roomNum) {
        return roomDatabase.roomExists(roomNum);
    }

    public static boolean deleteReservation(String email, String roomNumber, String startDate, String endDate){
        return reservationDatabase.cancelReservation(email, roomNumber, startDate, endDate);
    }

    /**
     * Retrieves the Reservation object corresponding to the given reservation ID from the ReservationDatabase.
     *
     * @param reservationId the ID of the reservation to retrieve
     * @return the Reservation object corresponding to the given reservation ID, or null if it doesn't exist
     */
    public static Reservation getReservation(String reservationId){
        return reservationDatabase.getReservationDetails(reservationId);
    }

    /**
     * Retrieves a list of available rooms for the given date range and room type.
     *
     * @param start the start date of the date range
     * @param end the end date of the date range
     * @param type the room type to filter by (either "Single King", "Double Queen", or "Suite")
     * @return a Vector of Vectors representing the available rooms, where each inner Vector contains the room number, room type, and room condition as Strings
     */
    public static Vector<Vector<String>> getAvailableRooms(Date start, Date end, String type){
        RoomType enumType = (type.equals("Single King")) ? RoomType.singleKing: (type.equals("Double Queen")) ? RoomType.doubleQueen: RoomType.suite;
        ArrayList<Room> rooms = roomDatabase.getRooms();
        rooms.removeIf(n -> (!n.roomType.equals(enumType)));

        rooms = reservationDatabase.getAvailableRooms(start, end, rooms);
        Vector<Vector<String>> output = new Vector<>();
        for(Room i : rooms){
            String roomType = i.getRoomType().equals(RoomType.suite) ? "Suite" : i.roomType.equals(RoomType.singleKing) ? "Single King" : "Double Queen";
            String roomCondition = String.valueOf(i.getRoomCondition());
            output.add(new Vector<>(List.of(Integer.toString(i.getNumber()), roomType, roomCondition)));
        }

        return output;
    }

    /**
     * Returns a list of reservations associated with a given email.
     * @param email The email of the account for which reservations should be returned.
     * @return A Vector of Vectors, where each Vector represents a reservation and contains the room number,
     *         room type, check-in date, and check-out date.
     */
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

    /**
     * Returns the account associated with a given email.
     * @param email The email of the account to return.
     * @return The Account object associated with the given email, or null if no such account exists.
     */
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

    /**
     * Returns the account associated with a given email and password.
     * @param email The email of the account to return.
     * @param password The password of the account to return.
     * @return The Account object associated with the given email and password, or null if no such account exists.
     */
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

    /**
     * Checks if an account with the given email exists.
     * @param email The email to check for.
     * @return true if an account with the given email exists, false otherwise.
     */
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

    /**
     * Checks if an account with the given email and password exists.
     * @param email The email to check for.
     * @param password The password to check for.
     * @return true if an account with the given email and password exists, false otherwise.
     */
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

    /**
     * Saves all accounts, reservations, and rooms to the database.
     * @return Always returns true.
     */
    public static boolean saveAll(){
        accountDatabase.save();
        reservationDatabase.save();
        roomDatabase.save();
        return true;
    }

    /**
     * Sets the current account.
     * @param a The account to set as the current account.
     * @return The current account.
     */
    public static Account setCurrentAccount(Account a){
        currentAccount = a;
        return currentAccount;
    }

    /**
     * Retrieves the current account that is logged in.
     *
     * @return the current account that is logged in
     */
    public static Account getCurrentAccount(){
        return currentAccount;
    }

    /**
     * Sends an email to the specified email address with the given subject and body.
     *
     * @param toEmail the email address to send the email to
     * @param subject the subject of the email
     * @param body the body of the email
     *
     * @throws MessagingException if there is an error while sending the email
     */
    public static void sendEmail(String toEmail, String subject, String body) throws MessagingException {
        emailService.send(toEmail, subject, body);
    }

    /**
     * Retrieves a list of all accounts in the system.
     *
     * @return a list of all accounts in the system
     */
    public static ArrayList<Account> getAllAccounts(){
        return accountDatabase.getAccountList();
    }

    /**
     * Resets the password for the specified account to the given new password.
     *
     * @param a the account to reset the password for
     * @param newPassword the new password to set for the account
     *
     * @return true if the password was reset successfully, false otherwise
     */
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

    /**
     * Deletes the specified account from the system.
     *
     * @param a the account to delete
     *
     * @return true if the account was deleted successfully, false otherwise
     */
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

