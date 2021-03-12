package csc1035.project2;
import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "user")
    private List<Booking> user;

    public User(String id, String firstName, String lastName, List<Booking> user) {
        this.userID = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

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
