public class Room {
    private String roomNumber;
    private String roomType;
    private int maxCapacity;
    private int socialDistanceCapacity;

    public Room(String roomNumber, String roomType, int maxCapacity, int socialDistanceCapacity) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.maxCapacity = maxCapacity;
        this.socialDistanceCapacity = socialDistanceCapacity;
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
}
