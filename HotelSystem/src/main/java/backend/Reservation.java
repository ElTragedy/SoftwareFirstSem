package backend;

import javax.swing.text.DateFormatter;
import javax.swing.text.html.parser.Parser;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

public class Reservation {
    int reservationID;

    String username;

    Room reserved;

    Date checkIn;

    Date checkOut;

    public Reservation(String []data) throws ParseException {
        reservationID = Integer.parseInt(data[0]);
        username = data[1];
        payed = Boolean.parseBoolean(data[2]);
        checkIn = new SimpleDateFormat("MM/dd/yyyy").parse(data[3]);
        checkOut = new SimpleDateFormat("MM/dd/yyyy").parse(data[4]);

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

    public Room getReserved() {
        return reserved;
    }

    public void setReserved(Room reserved) {
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

    boolean payed;


}
