package csc1035.project2;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity(name = "Booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int bookingID;

    @ManyToOne
    @JoinColumn(nullable = false, name = "userID")
    private User userID;

    @ManyToOne
    @JoinColumn(nullable = false, name = "roomNumber")
    private Room roomNumber;

    @ManyToOne
    @JoinColumn(name = "moduleID")
    private Module moduleID;

    @Column
    private Calendar startTime;

    @Column
    private int duration;

    @Column
    private Calendar date;

    @Column
    private boolean isSociallyDistant;

    @Column
    private String bookingType;

    @Column
    private int numPeople;

    public Booking(User userID, Room roomNumber, Module moduleID, Calendar startTime, int duration,
                   Calendar date, boolean isSociallyDistant, String bookingType, int numPeople) {
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

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public Room getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Room roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Module getModuleID() {
        return moduleID;
    }

    public void setModuleID(Module moduleID) {
        this.moduleID = moduleID;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public boolean getIsSociallyDistant() {
        return isSociallyDistant;
    }

    public void setIsSociallyDistant(boolean sociallyDistant) {
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
