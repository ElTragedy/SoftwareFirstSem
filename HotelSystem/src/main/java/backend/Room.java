package backend;

enum RoomType {
    doubleQueen, singleKing, suite;

    public static RoomType fromString(String str) {
        if (str.equalsIgnoreCase("doubleQueen")) {
            return doubleQueen;
        } else if (str.equalsIgnoreCase("singleKing")) {
            return singleKing;
        } else if (str.equalsIgnoreCase("suite")) {
            return suite;
        } else {
            return null;
        }
    }
};
public class Room implements Comparator<Room> {
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

    @Override
    public int compare(Room r1, Room r2) {
        return r1.number - r2.number;
    }
}