package csc1035.project2;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class represents the tables for bookings. It has methods relating to
 * bookings.
 *
 * @author everyone
 *
 */
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

    /**
     *
     * This is the constructor method that compiles the parameter values with the
     * field variables.
     *
     * @param userID this represents the user ID from the user table.
     * @param roomNumber this represents the room number from the room table.
     * @param moduleID this represents the module ID from the module table.
     * @param startTime this represents the start time for a room.
     * @param duration this represents the duration when the room will be used.
     * @param date this represents the date the room will be used.
     * @param isSociallyDistant this represents whether the room is socially distant.
     * @param bookingType this represents the type of booking.
     * @param numPeople this represents the number of people in the room.
     */
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

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateToParse = getDate().getTime();
        String date = dateFormat.format(dateToParse);

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date timeToParse = getStartTime().getTime();
        String time = timeFormat.format(timeToParse);

        return "Booking{" +
                "bookingID=" + bookingID +
                ", userID=" + userID.getuserID() +
                ", roomNumber=" + roomNumber.getRoomNumber() +
                ", moduleID=" + moduleID.getModuleID() +
                ", startTime=" + time +
                ", duration=" + duration +
                ", date=" + date +
                ", isSociallyDistant=" + isSociallyDistant +
                ", bookingType='" + bookingType + '\'' +
                ", numPeople=" + numPeople +
                '}';
    }
}
