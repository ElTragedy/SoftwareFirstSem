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
        database = null;
    }

    public Reservation getReservationDetails(String reservationID){
        if(database.get(roomNumFromID(reservationID)) != null){
            for(Reservation r : database.get(roomNumFromID(reservationID))){
                if(r.getReservationID().equals(reservationID)){
                    return r;
                }
            }
        }

        return null;
    }

    public void confirmUpdate(){
        save();
    }

    public boolean reserveRoom(Reservation r){
        boolean reserved = false;

        if(database.get(r.getRoomNumber()) == null){
            database.put(r.getRoomNumber(), new ArrayList<>(List.of(r)));

            return true;
        } else{
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

    public boolean cancelReservation(String reservationID){
        return database.get(roomNumFromID(reservationID)).removeIf((n) -> n.getReservationID().equals(reservationID));
    }

    public void save() {
        try{
            JAXBContext context = JAXBContext.newInstance(ReservationMap.class);

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(mapToXML(database), new File((Paths.get("src", "main", "resources", "reservations.xml")).toAbsolutePath().toUri()));

        } catch(JAXBException e){
        }
    }

    public void load(String filename){
        try{
            JAXBContext context = JAXBContext.newInstance(ReservationMap.class);
            Unmarshaller um = context.createUnmarshaller();

            xmlToDatabase((ReservationMap) um.unmarshal(this.getClass().getResourceAsStream("/" + filename)));
        }catch (JAXBException e){
        }
    }

    public List<Reservation> getReservationsByRoomNum(Integer roomNum){
        if(database.get(roomNum) == null){
            return new ArrayList<>();
        } else{
            return database.get(roomNum);
        }
    }

    public int getSize(){
        return database.size();
    }

    public String attemptUpdate(String reservationID, Reservation modified){
        Reservation hold = getReservationDetails(reservationID);
        cancelReservation(reservationID);

        if(reserveRoom(modified)){
            return modified.getReservationID();
        }
        return null;
    }

    private int roomNumFromID(String ID){
        return Integer.parseInt(ID.substring(0, 3), 16);
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
    }

    public ArrayList<Room> getAvailableRooms(Date start, Date end, ArrayList<Room> rooms) {
        ArrayList<Room> out = rooms;
        for(Room i : rooms){
            if(database.get(i.getNumber()) != null){
                for(Reservation k : database.get(i.getNumber())){
//                    if(end.compareTo(k.checkIn)  > 0 && start.compareTo(k.checkOut) < 0){
//                        out.removeIf(n -> n.getNumber() == k.roomNumber);
//                    }
                }
            }
        }
        return out;
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
        temp.load("reservations.xml");
        temp.reserveRoom(new Reservation(new String[]{"username1234","321","false","01/05/2023","01/10/2023"}));

        temp.save();
    }
}
