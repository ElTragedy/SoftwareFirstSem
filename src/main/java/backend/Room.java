package backend;

import java.util.Comparator;

enum RoomType {
    doubleQueen, singleKing, suite
};
public class Room implements Comparator<Room> {
    RoomType roomType;
    int number;

    public Room(String[] data) {
        number = Integer.parseInt(data[0]);
        roomType = RoomType.valueOf(data[1]);
    }

    public Room(String roomType, int number) {
        this.roomType = RoomType.valueOf(roomType);
        this.number = number;
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

    @Override
    public int compare(Room r1, Room r2) {
        return r1.number - r2.number;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomType=" + roomType +
                ", number=" + number +
                '}';
    }
}