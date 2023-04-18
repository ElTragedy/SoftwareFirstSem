package backend;

import java.util.Comparator;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

enum RoomType {
    doubleQueen, singleKing, suite
};

enum RoomStatus {
    available, occupied, reserved 
};

enum RoomCondition {
    smoking, nonSmoking,
}
@XmlRootElement(name = "room")
@XmlAccessorType(XmlAccessType.FIELD)
public class Room implements Comparator<Room> {

    RoomType roomType;
    int number;
    RoomCondition roomCondition;
    RoomStatus roomStatus;

    public Room(String[] data) {
        number = Integer.parseInt(data[0]);
        roomType = RoomType.valueOf(data[1]);
    }

    public Room() {
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

    public void setNumber(int number) {
        this.number = number;
    }

    public void setRoomType(String roomType) {
        this.roomType = RoomType.valueOf(roomType);
    }

    public void setRoomCondition(String roomCondition) {
        this.roomCondition = RoomCondition.valueOf(roomCondition);
    } 

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = RoomStatus.valueOf(roomStatus);
    }

    public RoomCondition getRoomCondition() {
        return roomCondition;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
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