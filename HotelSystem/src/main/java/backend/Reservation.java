package backend;

public class Reservation {
    int reservationID;

    String username;

    Room reserved;

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

    boolean payed;


}
