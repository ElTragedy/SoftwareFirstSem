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

    public RoomDatabase()  {
        rooms = new ArrayList<Room>();
    }

    public ArrayList<Room> getRooms() {
        return new ArrayList<>(rooms);
    }

    //save all the rooms to /Rooms.csv
    public void save() {
        xmlRoom.doSave("rooms.xml",this); 
    }

    public int getSize() {
        return rooms.size();
    }

    public void load(String s){
        RoomDatabase a = xmlRoom.load(s);
        this.rooms = a.getRooms();
    }

    public Room getRoom(int roomNum){
        for(Room i : rooms){
            if(i.getNumber() == roomNum){
                return i;
            }
        }
        return null;
    }

    public void removeRoom(int roomNum){
        rooms.remove(roomNum);
    }
    
    public void updateRoom(int roomNum, Room newRoom){
        rooms.set(roomNum, newRoom);
    }

    public RoomCondition getRoomCondition(int roomNum){
        return rooms.get(roomNum).getRoomCondition();
    }
    
    public RoomStatus getRoomStatus(int roomNum){
        return rooms.get(roomNum).getRoomStatus();
    }

    public void setRoomCondition(int roomNum, String roomCondition){
        rooms.get(roomNum).setRoomCondition(roomCondition);
    }

    public void setRoomStatus(int roomNum, String roomStatus){
        rooms.get(roomNum).setRoomStatus(roomStatus);
    }

    public void addRoom(Room newRoom){
        rooms.add(newRoom);
    } 

    public void printAll(){
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    public static void main(String[] args) {
        RoomDatabase rd = new RoomDatabase();

    }

}