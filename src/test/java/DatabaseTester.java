import backend.Reservation;
import backend.ReservationDatabase;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class DatabaseTester {
    ReservationDatabase tester;

    @BeforeEach
    public void init(){
        tester = new ReservationDatabase();
        tester.load("reservations.xml");
    }
    @Test
    public void reserveRoomTest() throws ParseException {
        Reservation tmp = new Reservation(new String[]{"username1234","99","false","01/01/2023","01/05/2023"});
        tester.reserveRoom(tmp);
        assertNotNull(tester.getReservationDetails(tmp.getReservationID()));
    }
}
