package backend;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.*;

import java.io.*;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class ReservationDatabase {

    HashMap<Integer, ArrayList<Reservation>> database;

    /**
     * Constructor for the ReservationDatabase class.
     * Initializes the database to null.
     */
    public ReservationDatabase() {
        database = null;
    }

    /**
     * Retrieves details for the given reservation ID.
     *
     * @param reservationID the ID of the reservation to retrieve
     * @return the Reservation object for the given ID, or null if not found
     */
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

    /**
     * Retrieves a list of reservations associated with the given email address.
     *
     * @param email the email address to search for
     * @return an ArrayList of Reservation objects associated with the email
     */
    public ArrayList<Reservation> getReservationsByEmail(String email){
        ArrayList<Reservation> out = new ArrayList<>();

        Iterator<HashMap.Entry<Integer, ArrayList<Reservation>>> iterator = database.entrySet().iterator();
        while(iterator.hasNext()){
            HashMap.Entry<Integer, ArrayList<Reservation>> entry = iterator.next();
            for(Reservation i : entry.getValue()){
                if(i.email.equals(email)){
                    out.add(i);
                }
            }
        }

        return out;
    }

    /**
     * Attempts to reserve a room for the given Reservation object.
     *
     * @param r the Reservation object to reserve
     * @return true if the reservation was successful, false otherwise
     */
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

        if(database.get(0) != null){
            System.out.println("BAD");
        }

        save();

        return !reserved;
    }

    /**
     * Cancels the reservation with the given ID.
     *
     * @param email, roomNumber, startDate, and endDate of the reservation you want to cancel
     * @return true if the reservation was cancelled, false otherwise
     */
    public boolean cancelReservation(String email, String roomNumber, String startDate, String endDate){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Reservation> reservations = database.get(Integer.parseInt(roomNumber));

        boolean tmp = reservations.removeIf(n ->
                n.email.equals(email) &&
                        format.format(n.getCheckIn()).equals(startDate) &&
                        format.format(n.getCheckOut()).equals(endDate) &&
                        Integer.toString(n.getRoomNumber()).equals(roomNumber));

        database.put(Integer.parseInt(roomNumber), reservations);

        save();

        return tmp;
    }

    /**
     * Saves the current state of the database to an XML file.
     */
    public void save() {
        try{
            JAXBContext context = JAXBContext.newInstance(ReservationMap.class);

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(mapToXML(database), new File((Paths.get("src", "main", "resources", "reservations.xml")).toAbsolutePath().toUri()));

        } catch(JAXBException e){
        }
    }

    /**
     * Loads a database from an XML file.
     *
     * @param filename the name of the file to load from
     */
    public void load(String filename){
        try{
            JAXBContext context = JAXBContext.newInstance(ReservationMap.class);
            Unmarshaller um = context.createUnmarshaller();

            xmlToDatabase((ReservationMap) um.unmarshal(this.getClass().getResourceAsStream("/" + filename)));
        }catch (JAXBException e){
        }
    }

    /**
     * Retrieves a list of reservations associated with the given room number.
     *
     * @param roomNum the room number to search for
     * @return an ArrayList of Reservation objects associated with the room number
     */
    public List<Reservation> getReservationsByRoomNum(Integer roomNum){
        if(database.get(roomNum) == null){
            return new ArrayList<>();
        } else{
            return database.get(roomNum);
        }
    }

    /**
     * Retrieves a list of available rooms for the given date range.
     *
     * @param start the start date of the date range
     * @param end the end date of the date range
     * @param rooms an ArrayList of all available rooms
     * @return an ArrayList of available Room objects for the given date range
     */
    public ArrayList<Room> getAvailableRooms(Date start, Date end, ArrayList<Room> rooms) {
        ArrayList<Room> out = new ArrayList<>(rooms);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        for(Room i : rooms){
            if(database.get(i.getNumber()) != null){
                for(Reservation k : database.get(i.getNumber())){
                    if(format.format(end).compareTo(format.format(k.checkIn)) > 0 && format.format(start).compareTo(format.format(k.checkOut)) < 0){
                        out.removeIf(n -> n.getNumber() == k.roomNumber);
                    }
                }
            }
        }
        return out;
    }

    /**
     * Returns the size of the database.
     *
     * @return the size of the database
     */
    public int getSize(){
        return database.size();
    }

    private int roomNumFromID(String ID){
        return Integer.parseInt(ID.substring(0, 3), 16);
    }

    /**
     * Converts the database to an XML format.
     *
     * @param data the HashMap representation of the database
     * @return a ReservationMap object representing the database in XML format
     */
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

    /**
     * Converts an XML file to a HashMap database.
     *
     * @param data the ReservationMap object representing the database in XML format
     */
    private void xmlToDatabase(ReservationMap data){
        database = new HashMap<>();

        HashMap<Integer, ReservationList> tmpDatabase = data.getMap();

        tmpDatabase.forEach((n, i) -> {
            database.put(n, i.getReserveList());
        });
    }

    /**
     * This class represents a list of reservations.
     * It is annotated with JAXB annotations to enable
     * marshalling and unmarshalling to/from XML.
     */
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

    /**
     * This class represents a map of room reservations.
     * It is annotated with JAXB annotations to enable
     * marshalling and unmarshalling to/from XML.
     */
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
