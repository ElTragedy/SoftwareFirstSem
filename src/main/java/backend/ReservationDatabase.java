package backend;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.*;

import java.io.*;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;


public class ReservationDatabase {

    HashMap<Integer, ArrayList<Reservation>> database;

    public ReservationDatabase() {
        try{
            JAXBContext context = JAXBContext.newInstance(ReservationMap.class);
            Unmarshaller um = context.createUnmarshaller();

            xmlToDatabase((ReservationMap) um.unmarshal(this.getClass().getResourceAsStream("/inReservations.xml")));
        }catch (JAXBException e){
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

        if(database.get(r.getRoomNumber()) == null){
            database.put(r.getRoomNumber(), new ArrayList<>(List.of(r)));

            return true;
        }else{
            ArrayList<Reservation> reserveList = database.get(r.getRoomNumber());

            for (Reservation reservation : reserveList) {
                reserved = reservation.overlap(r);
                if(reserved){
                    break;
                }
            }

            if(!reserved){
                reserveList.add(r);
                database.put(r.getRoomNumber(), reserveList);
            }
        }

        return !reserved;
    }

    public boolean cancelReservation(int reservationID){
        return false;
    }

    public void storeDatabase() {
        try{
            JAXBContext context = JAXBContext.newInstance(ReservationMap.class);

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(mapToXML(database), new File((Paths.get("src", "main", "resources", "outReservations.xml")).toAbsolutePath().toUri()));

        } catch(JAXBException e){
        }
    }

    private ReservationMap mapToXML(HashMap<Integer, ArrayList<Reservation>> data){
        ReservationMap output = new ReservationMap();

        HashMap<Integer, ReservationList> temp = output.getMap();

        data.forEach((i, n) -> {
            ReservationList tmp = new ReservationList();

            tmp.setReserveList(n);
            temp.put(i, tmp);
        });

        output.setMap(temp);

        return output;
    }

    private void xmlToDatabase(ReservationMap data){
        database = new HashMap<>();

        HashMap<Integer, ReservationList> tmpDatabase = data.getMap();

        tmpDatabase.forEach((n, i) -> {
            database.put(n, i.getReserveList());
        });

        System.out.println(database.get(123));
    }

    @XmlRootElement (name="reservations")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class ReservationList{
        public ArrayList<Reservation> getReserveList() {
            return reserveList;
        }

        public void setReserveList(ArrayList<Reservation> reserveList) {
            this.reserveList = reserveList;
        }

        @XmlElement
        private ArrayList<Reservation> reserveList = new ArrayList<>();
    }

    @XmlRootElement (name="roomReservations")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class ReservationMap{

        @XmlElement
        private HashMap<Integer, ReservationList> reserveMap = new HashMap<>();

        public HashMap<Integer, ReservationList> getMap() {
            return reserveMap;
        }

        public void setMap(HashMap<Integer, ReservationList> temp) {
            this.reserveMap = temp;
        }
    }

    public static void main(String[] args) throws ParseException{
        ReservationDatabase temp = new ReservationDatabase();
        temp.reserveRoom(new Reservation(new String[]{"username1234","321","false","01/05/2023","01/10/2023"}));

        temp.storeDatabase();
    }
}
