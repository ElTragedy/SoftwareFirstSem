package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class RoomDatabase {
    HashMap<Integer, Room> rooms;

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

    public HashMap<Integer, Room> getRooms() {
        return rooms;
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