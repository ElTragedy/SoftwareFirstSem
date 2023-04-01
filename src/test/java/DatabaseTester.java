import backend.Reservation;
import backend.ReservationDatabase;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.Test;

import java.text.ParseException;

public class DatabaseTester {
    ReservationDatabase tester = new ReservationDatabase();
    @Test
    public void reserveRoomTest() throws ParseException {
        tester.reserveRoom(new Reservation(new String[]{"1111","username1234","312","false","01/01/2023","01/05/2023"}));
        assertNotNull(tester.getReservationDetails(1111));
    }
}
