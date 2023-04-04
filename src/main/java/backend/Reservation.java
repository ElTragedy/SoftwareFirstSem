package backend;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Reservation { //TODO: Make reservation ID equal to room number as hex + ReservationHash(In Hex)

    String username;

    Room reserved;
    boolean payed;

    Date checkIn;

    Date checkOut;

    public Reservation(String []data) throws ParseException {
        username = data[1];
        reserved = new Room(new String[]{data[2], "suite"});
        payed = Boolean.parseBoolean(data[3]);
        checkIn = new SimpleDateFormat("MM/dd/yyyy").parse(data[4]);
        checkOut = new SimpleDateFormat("MM/dd/yyyy").parse(data[5]);
    }

    public Reservation(String username, Room reserved, boolean payed, Date checkIn, Date checkOut){
        this.username = username;
        this.reserved = reserved;
        this.payed = payed;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(username, that.username) && Objects.equals(reserved, that.reserved) && Objects.equals(checkIn, that.checkIn) && Objects.equals(checkOut, that.checkOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, reserved, checkIn, checkOut);
    }

    public boolean overlap(Reservation r){
        if(r.checkIn.compareTo(checkOut)  <= 0 || r.checkOut.compareTo(checkIn) >= 0){
            return false;
        }
        return true;
    }


    public String getReservationID() {
        return ((Integer.toHexString(reserved.number).length() == 2) ? "0": "") + Integer.toHexString(reserved.number).toUpperCase() + Integer.toHexString(this.hashCode()).toUpperCase();
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
