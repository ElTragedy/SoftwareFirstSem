package frontend;


import backend.*;
import backend.Room;
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

    public static boolean createRoom(String type, int roomNum, String roomCondition, String roomStatus){
        //make the room
        Room r = new Room(type, roomNum, roomCondition, roomStatus);
        //return the result of the controller
        return Controller.createRoom(r); 
    }

    //public Reservation(String username, Room reserved, boolean payed, 
        //Date checkIn, Date checkOut)
    public static boolean createReservation(String username, Room reserved, boolean payed, 
        Date checkIn, Date checkOut){
        
        Reservation r = new Reservation(username,reserved,payed, 
        checkIn,checkOut);

        Controller.createReservation(r);

        return false;
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

    //public static Account getCurrentUser(){
        //return Controller.getCurrentUser();
    //}

    public static Account getAccount(String email, char[] password){
        Account a = null;
        a = Controller.getAccount(email, password);
        return a;
    }

    public static void sendEmail(String email) {
        // call controller class
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


}
    
