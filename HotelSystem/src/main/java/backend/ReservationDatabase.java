package backend;

/*
* this would normally interface with a database server ie sql, but for now it'll just load
* from a csv*/

import java.util.HashSet;

public class ReservationDatabase {
    HashSet<Reservation> reservations;

    public ReservationDatabase(){
        reservations = new HashSet<>();

        //TODO: Load from CSV

    }

    public Reservation getReservationDetails(int reservationID){
        return new Reservation();
    }

    public boolean attemptUpdate(int reservationIm, Reservation modified){
        return true;
    }

    public void confirmUpdate(){
        reservations.add(new Reservation());
    }

    public Reservation getUpdateReservation(int reservationID){
        return new Reservation();
    }

    public boolean cancelReservation(int reservationID){
        return reservations.remove(reservationID);
    }


}
