package backend;

/*
* this would normally interface with a database server ie sql, but for now it'll just loads
* from a csv*/

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ReservationDatabase {
    HashMap<Integer, Reservation> reservations;
    HashMap<Room, ArrayList<Reservation>> database;

    public ReservationDatabase() throws IOException, ParseException {
        reservations = new HashMap<>();

        BufferedReader reader = null;

        reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/Reservations.csv")));

        String line = null;
        while ((line = reader.readLine()) != null) {

            String[] split = line.split(",");

            Reservation current = new Reservation(split);
            reservations.put(Integer.parseInt(split[0]), current);

            database.computeIfAbsent(new Room(new String[]{split[2], "0"}), k -> new ArrayList<>());

            database.get(new Room(new String[]{split[2], "0"})).add(current);
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

    public boolean reserveRoom(Reservation r){
        boolean reserved = false;

        if(database.get(r.getRoom()) == null){
            database.put(r.getRoom(), new ArrayList<>(List.of(r)));
            return true;
        }else{
            ArrayList<Reservation> reservations = database.get(r.getRoom());

            for (Reservation reservation : reservations) {
                reserved = reservation.overlap(r);
                if(reserved){
                    break;
                }
            }

            if(!reserved){
                reservations.add(r);
                database.put(r.getRoom(), reservations);
            }
        }

        return !reserved;
    }

    public boolean cancelReservation(int reservationID){

        return reservations.get(reservationID) != null;
    }



    public static void main(String[] args) throws IOException, ParseException {
        ReservationDatabase temp = new ReservationDatabase();

    }
}
