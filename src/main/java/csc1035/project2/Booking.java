package csc1035.project2;

import javax.persistence.*;

@Entity(name = "Booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int bookingID;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User.userID userID;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Room roomNumber;

    @ManyToOne
    @JoinColumn
    private Module moduleID;

    @Column
    private String startTime;

    @Column
    private int duration;

    @Column
    private String date;

    @Column
    private boolean isSociallyDistant;

    @Column
    private String bookingType;

    @Column
    private int numPeople;

    public Booking(String userID, String roomNumber, String moduleID, String startTime, int duration,
                   String date, boolean isSociallyDistant, String bookingType, int numPeople) {
        this.userID = userID;
        this.roomNumber = roomNumber;
        this.moduleID = moduleID;
        this.startTime = startTime;
        this.duration = duration;
        this.date = date;
        this.isSociallyDistant = isSociallyDistant;
        this.bookingType = bookingType;
        this.numPeople = numPeople;
    }

    public Booking(){
    }

    public int getBookingID() {
        return bookingID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getModuleID() {
        return moduleID;
    }

    public void setModuleID(String moduleID) {
        this.moduleID = moduleID;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isSociallyDistant() {
        return isSociallyDistant;
    }

    public void setSociallyDistant(boolean sociallyDistant) {
        isSociallyDistant = sociallyDistant;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public int getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(int numPeople) {
        this.numPeople = numPeople;
    }
}
