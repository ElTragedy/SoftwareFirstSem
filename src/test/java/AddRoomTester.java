import backend.Room;
import backend.RoomDatabase;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;


public class AddRoomTester {
    RoomDatabase tester = new RoomDatabase();

    public AddRoomTester() throws IOException {
    }

    @Test
    public void addRoomTest() throws Exception {
        //tester.addRoom(new Room(new String[]{"222", "singleKing"}));

        //HashMap<Integer, Room> rooms = tester.getRooms();

        //assertNotNull(rooms.get(222));
    }
}
