package frontend;


import backend.*;
import backend.Room;
import frontend.UI.loginUI;

import javax.mail.MessagingException;
import java.util.*;
import java.util.Date;

public class UIBlackBox {
    //this is where you will send commands to the Controller from the UI
    //and vice versa. The UI should only communicate with the Blackbox and nothing
    //else. 


    /**
     * Create an account
     * @param id
     * @param firstName
     * @param lastName
     * @param DOB
     * @param sex
     * @param phoneNumber
     * @param email
     * @param password
     * @param address
     * @param zipcode
     * @param city
     * @param state
     * @param country
     * @return account created
     */
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

    /**
     * Create an account with a specified access
     * @param id
     * @param firstName
     * @param lastName
     * @param DOB
     * @param sex
     * @param phoneNumber
     * @param email
     * @param password
     * @param address
     * @param zipcode
     * @param city
     * @param state
     * @param country
     * @param access
     * @return
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

    /**
     * Create a room
     * @param type
     * @param roomNum
     * @param roomCondition
     * @param roomStatus
     * @return
     */
    public static boolean createRoom(String type, int roomNum, String roomCondition, String roomStatus){
        //make the room
        Room r = new Room(type, roomNum, roomCondition, roomStatus);
        //return the result of the controller
        return Controller.createRoom(r); 
    }


    /**
     * Create a reservation
     * @param username
     * @param roomNumber
     * @param payed
     * @param checkIn
     * @param checkOut
     * @return
     */
    public static boolean createReservation(String username, int roomNumber, boolean payed, Date checkIn, Date checkOut){

        Controller.createReservation(username, roomNumber, payed, checkIn, checkOut);

        return false;
    }

    /**
     * get a room with a specified room number
     * @param roomNum
     * @return
     */
    public static Room getRoom(int roomNum){
        //filler
        return Controller.getRoom(roomNum);
    }

    /**
     * Update a room with a specified room number and room object
     * @param roomNum
     * @param room
     */
    public static void updateRoom(int roomNum, Room room){
        Controller.updateRoom(roomNum, room);
    }

    /**
     * check to see if a room exists
     * @param roomNum
     * @return
     */
    public static boolean roomExists(int roomNum){return Controller.roomExists(roomNum);}

    /**
     * get a reservation from an id
     * @param reservationId
     * @return
     */
    public static Reservation getReservation(String reservationId){
        return Controller.getReservation(reservationId);
    }

    /**
     * get available rooms from a start-end date
     * @param start
     * @param end
     * @param roomType
     * @return
     */
    public static Vector<Vector<String>> getAvailableRooms(Date start, Date end, String roomType){
        return Controller.getAvailableRooms(start, end, roomType);
    }

    /**
     * get all reservations held by a user specified by an email
     * @param email
     * @return
     */
    public static Vector<Vector<String>> getReservationsForUser(String email){
        return Controller.getReservationsByEmail(email);
    }

    //public static Account getCurrentUser(){
        //return Controller.getCurrentUser();
    //}

    /**
     * Get an account from a specified email
     * @param email
     * @return
     */
    public static Account getAccount(String email){
        Account a = null;
        a = Controller.getAccount(email);
        return a;
    }

    /**
     * get an account from a specified email and password
     * @param email
     * @param password
     * @return
     */
    public static Account getAccount(String email, char[] password){
        Account a = null;
        a = Controller.getAccount(email, password);
        return a;
    }

    /**
     * Check to see if an account with a specified email exists
     * @param email
     * @return
     */
    public static boolean accountExists(String email){return Controller.accountExists(email);}

    /**
     * Check to see if an account with a specified email and password exists
     * @param email
     * @param password
     * @return
     */
    public static boolean accountExists(String email, char[] password){return Controller.accountExists(email, password);}

    /**
     * Send an email from the system
     * @param toEmail
     * @param subject
     * @param body
     * @throws MessagingException
     */
    public static void sendEmail(String toEmail, String subject, String body) throws MessagingException {
        Controller.sendEmail(toEmail, subject, body);
    }

    /**
     * Save all data
     * @return
     */
    public static boolean saveAll(){
        return Controller.saveAll();
    }

    /**
     * Load all data
     * @return
     */
    public static boolean loadAll(){
        return Controller.loadAll();
    }

    /**
     * Set current account for specified context
     * @param a
     * @return
     */
    public static Account setCurrentAccount(Account a){
        return Controller.setCurrentAccount(a);
    }

    /**
     * Get current account
     * @return
     */
    public static Account getCurrentAccount(){
        return Controller.getCurrentAccount();
    }

    /**
     * Get all accounts
     * @return
     */
    public static ArrayList<Account> getAllAccounts(){
        return Controller.getAllAccounts();
    }

    /**
     * Reset password of a certain account
     * @param a
     * @param newPassword
     * @return
     */
    public static boolean resetPassword(Account a, String newPassword){
        return Controller.resetPassword(a, newPassword);
    }

    /**
     * Delete an account
     * @param a
     * @return
     */
    public static boolean deleteAccount(Account a){
        return Controller.deleteAccount(a);
    }

    /**
     * Start the program up
     */
    public static void startProgram(){
        loginUI loginUI = new loginUI();
        loginUI.createAndShowGui();
    }

}
    
