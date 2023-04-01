package backend;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {
    int reservationID;

    String username;

    Room reserved;
    boolean payed;

    Date checkIn;

    Date checkOut;

    public Reservation(String []data) throws ParseException {
        reservationID = Integer.parseInt(data[0]);
        username = data[1];
        reserved = new Room(new String[]{data[2], "suite"});
        payed = Boolean.parseBoolean(data[3]);
        checkIn = new SimpleDateFormat("MM/dd/yyyy").parse(data[4]);
        checkOut = new SimpleDateFormat("MM/dd/yyyy").parse(data[5]);
    }

    public Reservation(int id, String username, Room reserved, boolean payed, Date checkIn, Date checkOut){
        this.reservationID = id;
        this.username = username;
        this.reserved = reserved;
        this.payed = payed;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public boolean overlap(Reservation r){
        if(r.checkIn.compareTo(checkOut)  <= 0 || r.checkOut.compareTo(checkIn) >= 0){
            return false;
        }
        return true;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Room getRoom() {
        return reserved;
    }

    public void getRoom(Room reserved) {
        this.reserved = reserved;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }
}
