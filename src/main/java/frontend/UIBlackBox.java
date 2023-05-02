package frontend;


import backend.*;
import backend.Room;

import javax.mail.MessagingException;
import java.util.*;
import java.util.Date;

public class UIBlackBox {
    //this is where you will send commands to the Controller from the UI
    //and vice versa. The UI should only communicate with the Blackbox and nothing
    //else. 

    
    //this creates an account object and attempts to save it to the database
    //through the controllers. If successful it returns true, if not it returns false
    public static boolean createAccount(String id, String firstName, String lastName,
        String DOB, String sex, String phoneNumber, String email,
        String password, String address, String zipcode, String city,
        String state, String country){

        //make the account
        Account a = new Account (id, firstName, lastName, DOB, sex, phoneNumber, email,
        password,  address,  zipcode,  city,  state,  country);

        //attempt to contact the controller.
        return Controller.createAccount(a);

    }

    /*
     * This is the create account that specifies the access level
     */
    public static boolean createAccount(String id, String firstName, String lastName,
        String DOB, String sex, String phoneNumber, String email,
        String password, String address, String zipcode, String city,
        String state, String country, String access){

        //make the account
        Account a = new Account (id, firstName, lastName, DOB, sex, phoneNumber, email,
        password,  address,  zipcode,  city,  state,  country, access);

        //attempt to contact the controller.
        return Controller.createAccount(a);

    }

    public static boolean createRoom(String type, int roomNum, String roomCondition, String roomStatus){
        //make the room
        Room r = new Room(type, roomNum, roomCondition, roomStatus);
        //return the result of the controller
        return Controller.createRoom(r); 
    }

    //public Reservation(String username, Room reserved, boolean payed, 
        //Date checkIn, Date checkOut)
    public static boolean createReservation(String username, Room reserved, boolean payed, Date checkIn, Date checkOut){
        
        Reservation r = new Reservation(username,reserved,payed, checkIn,checkOut);

        Controller.createReservation(r);

        return false;
    }

    public static Room getRSoom(/* parameters */){
        //filler
        Room a = null;
        return a;
    }

    public static Reservation getReservation(String reservationId){
        return Controller.getReservation(reservationId);
    }

    public static Vector<Vector<String>> getAvailableRooms(Date start, Date end, String roomType){
        return Controller.getAvailableRooms(start, end, roomType);
    }

    public static Vector<Vector<String>> getReservationsForUser(String username){
        return Controller.getReserveByUser(username);
    }

    //public static Account getCurrentUser(){
        //return Controller.getCurrentUser();
    //}

    public static Account getAccount(String email, char[] password){
        Account a = null;
        a = Controller.getAccount(email, password);
        return a;
    }

    public static void sendEmail(String toEmail, String subject, String body) throws MessagingException {
        Controller.sendEmail(toEmail, subject, body);
    }

    public static boolean saveAll(){
        return Controller.saveAll();
    }

    public static boolean loadAll(){
        return Controller.loadAll();
    }

    public static Account setCurrentAccount(Account a){
        return Controller.setCurrentAccount(a);
    }

    public static Account getCurrentAccount(){
        return Controller.getCurrentAccount();
    }

    public static ArrayList<Account> getAllAccounts(){
        return Controller.getAllAccounts();
    }

    public static boolean resetPassword(Account a, String newPassword){
        return Controller.resetPassword(a, newPassword);
    }

    public static boolean deleteAccount(Account a){
        return Controller.deleteAccount(a);
    }

}
    
