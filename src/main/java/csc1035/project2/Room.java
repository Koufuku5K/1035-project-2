package csc1035.project2;

import javax.persistence.*;

@Entity
public class Room {
    @Id
    private String roomNumber;
    @Column
    private String roomType;
    @Column
    private int maxCapacity;
    @Column
    private int socialDistanceCapacity;

    public Room(String roomNumber, String roomType, int maxCapacity, int socialDistanceCapacity) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.maxCapacity = maxCapacity;
        this.socialDistanceCapacity = socialDistanceCapacity;
    }

    public Room() {}

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
}
