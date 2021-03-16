package csc1035.project2;

import javax.persistence.*;
import java.util.List;

/**
 * This class represents a table for available rooms. It has methods relating to
 * the rooms.
 *
 * @author everyone
 *
 */
@Entity(name = "Room")
public class Room {

    @Id
    @Column(updatable = false, nullable = false)
    private String roomNumber;

    @Column
    private String roomType;

    @Column
    private int maxCapacity;

    @Column
    private int socialDistanceCapacity;

    @OneToMany(mappedBy = "roomNumber")
    private List<Booking> room;

    /**
     *
     * This is the constructor method that compiles the parameter values with the
     * field variables.
     *
     * @param roomNumber this represents the room number of the room.
     * @param roomType this represents the type of the room.
     * @param maxCapacity this represents the normal maximum capacity of the room.
     * @param socialDistanceCapacity this represents the maximum social distancing capacity of the room.
     * @param room ------------------------------------------------------------
     */
    public Room(String roomNumber, String roomType, int maxCapacity, int socialDistanceCapacity, List<Booking> room) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.maxCapacity = maxCapacity;
        this.socialDistanceCapacity = socialDistanceCapacity;
        this.room = room;
    }

    public Room(){
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getSocialDistanceCapacity() {
        return socialDistanceCapacity;
    }

    public void setSocialDistanceCapacity(int socialDistanceCapacity) {
        this.socialDistanceCapacity = socialDistanceCapacity;
    }

    public List<Booking> getRoom() {
        return room;
    }

    public void setRoom(List<Booking> room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", roomType='" + roomType + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", socialDistanceCapacity=" + socialDistanceCapacity +
                '}';
    }
}
