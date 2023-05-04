package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.io.FileWriter;
import java.util.ArrayList;
import jakarta.xml.bind.*;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "roomDatabase")
@XmlAccessorType(XmlAccessType.FIELD)
public class RoomDatabase {

    @XmlElementWrapper(name = "rooms")
    @XmlElement(name = "room")
    private ArrayList<Room> rooms;

    /**
     * Constructor for the RoomDatabase class that initializes the rooms list to an empty ArrayList.
     */
    public RoomDatabase()  {
        rooms = new ArrayList<Room>();
    }

    /**
     * Returns a copy of the list of rooms.
     *
     * @return a new ArrayList containing all the Room objects in the database
     */
    public ArrayList<Room> getRooms() {
        return new ArrayList<>(rooms);
    }

    /**
     * Saves all the rooms in the database to a specified XML file.
     *
     */
    public void save() {
        xmlRoom.doSave("rooms.xml",this); 
    }

    /**
     * Returns the number of rooms in the database.
     *
     * @return the number of rooms in the database
     */
    public int getSize() {
        return rooms.size();
    }

    /**
     * Loads the rooms from the specified XML file and replaces the current list of rooms in the database with the new one.
     *
     * @param s the name of the file to load from
     */
    public void load(String s){
        RoomDatabase a = xmlRoom.load(s);
        this.rooms = a.getRooms();
    }

    /**
     * Returns the Room object with the specified room number.
     *
     * @param roomNum the room number of the room to return
     * @return the Room object with the specified room number, or null if it doesn't exist
     */
    public Room getRoom(int roomNum){
        for(Room i : rooms){
            if(i.getNumber() == roomNum){
                return i;
            }
        }
        return null;
    }

    /**
     * Removes the Room object with the specified room number from the database.
     *
     * @param roomNum the room number of the room to remove
     */
    public void removeRoom(int roomNum){
        rooms.remove(roomNum);
    }

    /**
     * Updates the Room object with the specified room number in the database to the new Room object.
     *
     * @param roomNum the room number of the room to update
     * @param newRoom the new Room object to replace the existing one with
     */
    public void updateRoom(int roomNum, Room newRoom){
        int i = 0;
        for (Room r : rooms){
            if (r.getNumber() == roomNum) {
                rooms.set(i, newRoom);
            }
            i++;
        }
    }

    /**
     * Checks if a Room object with the specified room number exists in the database.
     *
     * @param roomNum the room number to check for existence
     * @return true if a Room object with the specified room number exists in the database, false otherwise
     */
    public boolean roomExists(int roomNum){
        boolean exists = false;
        for (Room r : rooms){
            if (r.getNumber() == roomNum){
                exists = true;
                break;
            }
        }
        return exists;
    }

    /**
     * Returns the RoomCondition of the Room object with the specified room number in the database.
     *
     * @param roomNum the room number of the Room object
     * @return the RoomCondition of the Room object with the specified room number
     */
    public RoomCondition getRoomCondition(int roomNum){
        return rooms.get(roomNum).getRoomCondition();
    }

    /**
     * Returns the status of a room in the database.
     *
     * @param roomNum the number of the room
     * @return the RoomStatus of the room with the given room number
     */
    public RoomStatus getRoomStatus(int roomNum){
        return rooms.get(roomNum).getRoomStatus();
    }

    /**
     * Sets the condition of a room in the database.
     *
     * @param roomNum the number of the room
     * @param roomCondition the new condition of the room
     */
    public void setRoomCondition(int roomNum, String roomCondition){
        rooms.get(roomNum).setRoomCondition(roomCondition);
    }

    /**
     * Sets the status of a room in the database.
     *
     * @param roomNum the number of the room
     * @param roomStatus the new status of the room
     */
    public void setRoomStatus(int roomNum, String roomStatus){
        rooms.get(roomNum).setRoomStatus(roomStatus);
    }

    /**
     * Adds a new room to the database.
     *
     * @param newRoom the new room to add
     */
    public void addRoom(Room newRoom){
        rooms.add(newRoom);
    }

    /**
     * Prints all the rooms in the database to the console.
     */
    public void printAll(){
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    public static void main(String[] args) {
        RoomDatabase rd = new RoomDatabase();

    }

}