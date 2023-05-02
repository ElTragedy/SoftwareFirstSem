package backend;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


@XmlRootElement(name = "reservation")
@XmlAccessorType(XmlAccessType.FIELD)
public class Reservation { //TODO: Make reservation ID equal to room number as hex + ReservationHash(In Hex)

    String email;
    Room reserved;
    int roomNumber;
    boolean payed;
    Date checkIn;

    Date checkOut;

    public Reservation(){

    }

    public Reservation(String []data) throws ParseException {
        email = data[0];
        roomNumber = Integer.parseInt(data[1]);
        payed = Boolean.parseBoolean(data[2]);
        checkIn = new SimpleDateFormat("MM/dd/yyyy").parse(data[3]);
        checkOut = new SimpleDateFormat("MM/dd/yyyy").parse(data[4]);
    }

    public Reservation(String email, Room reserved, boolean payed,
                       Date checkIn, Date checkOut){
        this.email = email;
        this.roomNumber = reserved.getNumber();
        this.payed = payed;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(email, that.email) &&
            Objects.equals(roomNumber, that.roomNumber) &&
            Objects.equals(checkIn, that.checkIn) && 
            Objects.equals(checkOut, that.checkOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, roomNumber, checkIn, checkOut);
    }

    public boolean overlap(Reservation r){
        if(r.checkIn.compareTo(checkOut)  >= 0 || r.checkOut.compareTo(checkIn) <= 0){
            return false;
        }
        return true;
    }


    public String getReservationID() {
        return ((Integer.toHexString(roomNumber).length() == 2) ? "0": "")
         + Integer.toHexString(roomNumber).toUpperCase()
         + Integer.toHexString(this.hashCode()).toUpperCase();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Room reserved) {
        this.roomNumber = reserved.getNumber();
    }
    public void setRoomNumber(int reserved) {
        this.roomNumber = reserved;
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

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }
}
