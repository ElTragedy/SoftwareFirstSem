package backend;

public class ReservationController {
    private Account account;
    private ReservationDatabase data;


    ReservationController(Account user, ReservationDatabase database) {
        this.account = user;
        this.data = database;
    }

    void addReservation(Reservation reservation) {
        if (this.account.isReal()) {
            if (!data.isReal(reservation)) {
                data.confirmUpdate(reservation);
            }
        }
    }

    void removeReservation(Reservation reservation) {
        if (this.account.isReal()) {
            if (data.isReal(reservation)) {
                data.cancelReservation(reservation.getReservationID());
            }
        }
    }
}
