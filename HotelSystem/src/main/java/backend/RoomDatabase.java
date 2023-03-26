package backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.Scanner;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.TreeSet;
import java.util.regex.*;
import java.util.*;


public class RoomDatabase {
    HashMap<Integer, Room> rooms; 

    public RoomDatabase() throws FileNotFoundException {
        rooms = new HashMap<>();
        File file = new File("Rooms.csv");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split = line.split(",");
            Room room = new Room(RoomType.fromString(split[0]), Integer.parseInt(split[1]), Boolean.parseBoolean(split[2]));
            rooms.put(Integer.parseInt(split[1]), room);
        }
        scanner.close(); 
    }

    public Room getRoomDetails(int roomNumber) {
        return rooms.get(roomNumber);
    }
    public boolean attemptUpdate(int roomNumber, Room modified) {
        return rooms.get(roomNumber) != null;
    }
    public void confirmUpdate() {
        rooms.put(null, null);
    }
    public Room getUpdateRoom(int roomNumber) {
        return rooms.get(roomNumber);
    }
    public boolean cancelReservation(int roomNumber) {
        return rooms.get(roomNumber) != null;
    }
    public Set<Room> getVacantRooms() {
        Set<Room> vacantRooms = new TreeSet<>();
        for (Map.Entry<Integer, Room> entry : rooms.entrySet()) {
            if (entry.getValue().isVacant()) {
                vacantRooms.add(entry.getValue());
            }
        }
        return vacantRooms;
    }
    public Set<Room> getOccupiedRooms() {
        Set<Room> occupiedRooms = new TreeSet<>();
        for (Map.Entry<Integer, Room> entry : rooms.entrySet()) {
            if (!entry.getValue().isVacant()) {
                occupiedRooms.add(entry.getValue());
            }
        }
        return occupiedRooms;
    }
    public Set<Room> getRoomsByType(RoomType roomType) {
        Set<Room> roomsByType = new TreeSet<>();
        for (Map.Entry<Integer, Room> entry : rooms.entrySet()) {
            if (entry.getValue().getRoomType() == roomType) {
                roomsByType.add(entry.getValue());
            }
        }
        return roomsByType;
    }
    
   //public Set<Room> getRoomsByPrice(double price) {
        //Set<Room> roomsByPrice = new TreeSet<>();
        //for (Map.Entry<Integer, Room> entry : rooms.entrySet()) {
            //if (entry.getValue().getPrice() == price) {
                //roomsByPrice.add(entry.getValue());
            //}
        //}
        //return roomsByPrice;
    //}
    public void printAllRooms() {
        for (Map.Entry<Integer, Room> entry : rooms.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
    public void removeRoom(int roomNumber) {
        rooms.remove(roomNumber);
    }


    public static void main(String[] args) throws FileNotFoundException {
        RoomDatabase roomDatabase = new RoomDatabase();
    }
}

