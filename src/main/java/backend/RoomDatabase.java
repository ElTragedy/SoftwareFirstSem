package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class RoomDatabase {
    static HashMap<Integer, Room> rooms;

    public RoomDatabase() throws IOException {
        rooms = new HashMap<>();

        BufferedReader reader = null;

        reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/Rooms.csv")));

        // skip header
        reader.readLine();

        String line = null;
        while ((line = reader.readLine()) != null) {
            String[] split = line.split(",");
            rooms.put(Integer.parseInt(split[0]), new Room(split));
        }
    }

    public static HashMap<Integer, Room> getRooms() {
        return rooms;
    }

    public static Room getRoom(int roomNum){
        return rooms.get(roomNum);
    }

    public static void addRoom(Room newRoom) throws Exception {
        if(rooms.get(newRoom.getNumber()) != null){
            throw new Exception("Room number already exists");
        }
        rooms.put(newRoom.getNumber(), newRoom);
    }

    public void printAll() throws IOException {
        RoomDatabase.getRooms().forEach((k, v) -> System.out.println(k + ": " + v));
    }

    public static void main(String[] args) {
        try {
            RoomDatabase rd = new RoomDatabase();
            rd.getRooms().forEach((k, v) -> System.out.println(k + ": " + v));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}