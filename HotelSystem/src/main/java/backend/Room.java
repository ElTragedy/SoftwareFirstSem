package backend;
enum RoomType {
    doubleQueen, singleKing, suite
}
public class Room {
    RoomType roomType;
    int number;
    boolean vacant;
    Account occupant;
    public Room(RoomType roomType, int number, boolean vacant) {
        this.roomType = roomType;
        this.number = number;
        this.vacant = vacant;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isVacant() {
        return vacant;
    }

    public void setVacant(boolean vacant) {
        this.vacant = vacant;
    }


    public Account getOccupant() {
        return occupant;
    }

    public void setOccupant(Account occupant) {
        this.occupant = occupant;
    }
}