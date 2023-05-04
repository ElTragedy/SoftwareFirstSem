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
    /**
     * Constructs a Reservation object from an array of data.
     *
     * @param data an array of reservation data in the following format:
     *             [email, roomNumber, payed, checkIn, checkOut]
     * @throws ParseException if the checkIn or checkOut dates are not in the correct format
     */
    public Reservation(String []data) throws ParseException {
        email = data[0];
        roomNumber = Integer.parseInt(data[1]);
        payed = Boolean.parseBoolean(data[2]);
        checkIn = new SimpleDateFormat("MM/dd/yyyy").parse(data[3]);
        checkOut = new SimpleDateFormat("MM/dd/yyyy").parse(data[4]);
    }

    /**
     * Constructs a Reservation object with the given data.
     *
     * @param email the email associated with the reservation
     * @param reserved the room reserved
     * @param payed whether the reservation has been paid for
     * @param checkIn the date the reservation starts
     * @param checkOut the date the reservation ends
     */
    public Reservation(String email, Room reserved, boolean payed,
                       Date checkIn, Date checkOut){
        this.email = email;
        this.roomNumber = reserved.getNumber();
        this.payed = payed;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    /**
     * Checks if the current Reservation object is equal to another object.
     *
     * @param o the object to compare to
     * @return true if the objects are equal, false otherwise
     */
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

    /**
     * Generates a hash code for the current Reservation object.
     *
     * @return the hash code of the Reservation object
     */
    @Override
    public int hashCode() {
        return Objects.hash(email, roomNumber, checkIn, checkOut);
    }

    /**
     * Checks if this Reservation overlaps with another Reservation.
     *
     * @param r the Reservation to compare to
     * @return true if the Reservations overlap, false otherwise
     */
    public boolean overlap(Reservation r){
        if(r.checkIn.compareTo(checkOut)  >= 0 || r.checkOut.compareTo(checkIn) <= 0){
            return false;
        }
        return true;
    }

    /**
     * Generates a unique reservation ID for the current Reservation object.
     *
     * @return the unique reservation ID
     */
    public String getReservationID() {
        return ((Integer.toHexString(roomNumber).length() == 2) ? "0": "")
         + Integer.toHexString(roomNumber).toUpperCase()
         + Integer.toHexString(this.hashCode()).toUpperCase();
    }

    /**
     * Gets the email associated with the current Reservation object.
     *
     * @return the email associated with the Reservation object
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email associated with the current Reservation object.
     *
     * @param email the new email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the room number associated with the current Reservation object.
     *
     * @return the room number associated with the Reservation object
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Sets the room number associated with the current Reservation object.
     *
     * @param reserved the Room object representing the reserved room
     */
    public void setRoomNumber(Room reserved) {
        this.roomNumber = reserved.getNumber();
    }

    /**
     * Sets the room number associated with the current Reservation object.
     *
     * @param reserved the new room number to set
     */
    public void setRoomNumber(int reserved) {
        this.roomNumber = reserved;
    }


    /**
     * Checks if the current Reservation object has been paid for.
     *
     * @return true if the reservation has been paid for, false otherwise
     */
    public boolean isPayed() {
        return payed;
    }

    /**
     * Sets whether the current Reservation object has been paid for.
     *
     * @param payed whether the reservation has been paid for
     */
    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    /**
     * Returns the check-in date of this Reservation object.
     * @return the check-in date
     */
    public Date getCheckIn() {
        return checkIn;
    }

    /**
     * Sets the check-in date of this Reservation object.
     * @param checkIn the check-in date to set
     */
    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }


    /**
     * Returns the check-out date of this Reservation object.
     * @return the check-out date
     */
    public Date getCheckOut() {
        return checkOut;
    }

    /**
     * Sets the check-out date of this Reservation object.
     * @param checkOut the check-out date to set
     */
    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }
}
