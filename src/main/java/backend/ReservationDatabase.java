package backend;

/*
* this would normally interface with a database server ie sql, but for now it'll just loads
* from a csv*/

import java.io.*;
import java.text.ParseException;
import java.util.HashMap;

public class ReservationDatabase {
    HashMap<Integer, Reservation> reservations;

    public ReservationDatabase() throws IOException, ParseException {
        reservations = new HashMap<>();

        BufferedReader reader = null;

        reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/Reservations.csv")));

        String line = null;
        while ((line = reader.readLine()) != null) {
            String[] split = line.split(",");
            reservations.put(Integer.parseInt(split[0]), new Reservation(split));
        }
    }

    public Reservation getReservationDetails(int reservationID){
        return null;
    }

    public boolean attemptUpdate(int reservationIm, Reservation modified){
        return true;
    }

    public void confirmUpdate(){
        reservations.put(null, null);
    }

    public Reservation getUpdateReservation(int reservationID){

        return null;
    }

    public boolean cancelReservation(int reservationID){
        return reservations.get(reservationID) != null;
    }

    public static void main(String[] args) throws IOException, ParseException {
        ReservationDatabase temp = new ReservationDatabase();

    }
}