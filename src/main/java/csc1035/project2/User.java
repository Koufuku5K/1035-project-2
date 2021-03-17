package csc1035.project2;
import javax.persistence.*;
import java.util.List;

/**
 * This class represents a table for users. It has methods relating to the users and
 * is the parent of Staff and Students class.
 * @author Kyle Anderson
 * @author William Moses
 * @author Joseph Burley
 * @author Alfie Fields
 */
@Entity(name = "User")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @Column
    private String userID;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @OneToMany(mappedBy = "userID")
    private List<Booking> user;

    /**
     * This is the constructor methods that compiles the parameter values with the
     * field values.
     * @param id this represents the ID of the user.
     * @param firstName this represents the first name of the user.
     * @param lastName this represents the last name of the user.
     * @param user list of bookings a user as made.
     */
    public User(String id, String firstName, String lastName, List<Booking> user) {
        this.userID = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * No parameter constructor for initialising and empty object.
     */
    public User() {
    }

    public String getuserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Booking> getUser() {
        return user;
    }

    public void setUser(List<Booking> user) {
        this.user = user;
    }
}
