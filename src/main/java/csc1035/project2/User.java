package csc1035.project2;
import javax.persistence.*;

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

    public User(String id, String firstName, String lastName) {
        this.userID = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {
    }

    public String getId() {
        return userID;
    }

    public void setId(String id) {
        this.userID = id;
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
}
