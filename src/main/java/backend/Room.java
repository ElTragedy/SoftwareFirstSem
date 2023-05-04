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

    /**
     * Constructs a Room object using an array of string data.
     *
     * @param data an array of string data representing the room number and type
     */
    public Room(String[] data) {
        number = Integer.parseInt(data[0]);
        roomType = RoomType.valueOf(data[1]);
    }

    /**
     * Constructs a new Room object with default values.
     */
    public Room() {
    }

    /**
     * Constructs a Room object with the specified type and number.
     *
     * @param roomType a string representing the room type
     * @param number an integer representing the room number
     */
    public Room(String roomType, int number) {
        this.roomType = RoomType.valueOf(roomType);
        this.number = number;
    }

    /**
     * Constructs a Room object with the specified type, number, condition, and availability status.
     *
     * @param roomType a string representing the room type
     * @param number an integer representing the room number
     * @param condition a RoomCondition object representing the condition of the room
     */
    public Room(String roomType, int number, RoomCondition condition) {
        this.roomType = RoomType.valueOf(roomType);
        this.number = number;
        this.roomCondition = condition;
        this.roomStatus = RoomStatus.available;
    }

    /**
     * Constructs a Room object with the specified type, number, condition, and availability status.
     *
     * @param roomType a string representing the room type
     * @param number an integer representing the room number
     * @param roomCondition a string representing the condition of the room
     * @param roomStatus a string representing the availability status of the room
     */
    public Room(String roomType, int number, String roomCondition, String roomStatus) {
        this.roomType = RoomType.valueOf(roomType);
        this.number = number;
        this.roomCondition = RoomCondition.valueOf(roomCondition);
        this.roomStatus = RoomStatus.valueOf(roomStatus);
    }

    /**
     * Returns the room type.
     *
     * @return the room type
     */
    public RoomType getRoomType() {
        return roomType;
    }

    /**
     * Sets the room type.
     *
     * @param roomType the room type to set
     */
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    /**
     * Sets the room number.
     *
     * @param number the room number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Sets the room type using a string representation of the type.
     *
     * @param roomType a string representing the room type to set
     */
    public void setRoomType(String roomType) {
        this.roomType = RoomType.valueOf(roomType);
    }

    /**
     * Sets the room condition using a string representation of the condition.
     *
     * @param roomCondition a string representing the room condition to set
     */
    public void setRoomCondition(String roomCondition) {
        this.roomCondition = RoomCondition.valueOf(roomCondition);
    }

    /**
     * Sets the room status using a string representation of the status.
     *
     * @param roomStatus a string representing the room status to set
     */
    public void setRoomStatus(String roomStatus) {
        this.roomStatus = RoomStatus.valueOf(roomStatus);
    }

    /**
     * Returns the room condition.
     *
     * @return the room condition
     */
    public RoomCondition getRoomCondition() {
        return roomCondition;
    }

    /**
     * Returns the room status.
     *
     * @return the room status
     */
    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    /**
     * Get the room number.
     *
     * @return the room number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Compare two rooms by their room numbers.
     *
     * @param r1 the first room
     * @param r2 the second room
     * @return -1 if r1 is less than r2, 0 if r1 is equal to r2, 1 if r1 is greater than r2
     */
    @Override
    public int compare(Room r1, Room r2) {
        return r1.number - r2.number;
    }

    /**
     * Returns a string representation of the Room object, including its room type and number.
     *
     * @return a string representation of the Room object
     */
    @Override
    public String toString() {
        return "Room{" +
                "roomType=" + roomType +
                ", number=" + number +
                '}';
    }
}