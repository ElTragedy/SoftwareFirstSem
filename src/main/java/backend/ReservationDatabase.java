package backend;

/*
* this would normally interface with a database server ie sql, but for now it'll just loads
* from a csv*/

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReservationDatabase {
    HashMap<Integer, ArrayList<Reservation>> database;

    public ReservationDatabase(){
        database = new HashMap<>();

        BufferedReader reader =  new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/Reservations.csv")));

        String line = null;
        try {
            reader.readLine(); //for header
            while ((line = reader.readLine()) != null) {

                String[] split = line.split(",");

                Reservation current = new Reservation(split);

                if(database.get(Integer.parseInt(split[1])) == null){
                    database.put(Integer.parseInt(split[1]), new ArrayList<>(List.of(current)));
                } else{
                    database.get(Integer.parseInt(split[1])).add(current);
                }
            }
        } catch(IOException | ParseException e){
            e.printStackTrace();
            System.err.println("CSV Read: FATAL ERROR");
        }
    }

    public Reservation getReservationDetails(String reservationID){
        for(Reservation r : database.get(Integer.parseInt(reservationID.substring(0, 2), 16))){
            if(r.getReservationID().equals(reservationID)){
                return r;
            }
        }
        return null;
    }

    public boolean attemptUpdate(int reservationIm, Reservation modified){
        return true;
    }

    public void confirmUpdate(){

    }

    public Reservation getUpdateReservation(int reservationID){

        return null;
    }

    public boolean reserveRoom(Reservation r){
        boolean reserved = false;

        if(database.get(r.getRoom().getNumber()) == null){
            database.put(r.getRoom().getNumber(), new ArrayList<>(List.of(r)));
            return true;
        }else{
            ArrayList<Reservation> reserveList = database.get(r.getRoom().getNumber());

            for (Reservation reservation : reserveList) {
                reserved = reservation.overlap(r);
                if(reserved){
                    break;
                }
            }

            if(!reserved){
                reserveList.add(r);
                database.put(r.getRoom().getNumber(), reserveList);
            }
        }

        return !reserved;
    }

    public boolean cancelReservation(int reservationID){
        return false;
    }


    public static void main(String[] args) throws IOException, ParseException {
        ReservationDatabase temp = new ReservationDatabase();
        System.out.println(new Reservation(new String[]{"1111","username1234","312","false","01/01/2023","01/05/2023"}).getReservationID());
    }
}
