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

    public ReservationDatabase(){
        reservations = new HashMap<>();
        database = new HashMap<>();

        BufferedReader reader = null;

        reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/Reservations.csv")));

        String line = null;
        try {
            reader.readLine(); //for header
            while ((line = reader.readLine()) != null) {

                String[] split = line.split(",");

                Reservation current = new Reservation(split);
                reservations.put(Integer.parseInt(split[0]), current);

                if(database.get(new Room(new String[]{split[2], "suite"})) == null){
                    database.put(new Room(new String[]{split[2], "suite"}), new ArrayList<>(List.of(current)));
                } else{
                    database.get(new Room(new String[]{split[2], "suite"})).add(current);
                }
            }
        } catch(IOException | ParseException e){
            e.printStackTrace();
            System.err.println("CSV Read: FATAL ERROR");
        }
    }

    public Reservation getReservationDetails(int reservationID){
        return reservations.get(reservationID);
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
            reservations.put(r.getReservationID(), r);
            return true;
        }else{
            ArrayList<Reservation> reserveList = database.get(r.getRoom());

            for (Reservation reservation : reserveList) {
                reserved = reservation.overlap(r);
                if(reserved){
                    break;
                }
            }

            if(!reserved){
                reserveList.add(r);
                database.put(r.getRoom(), reserveList);
                reservations.put(r.getReservationID(), r);
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
