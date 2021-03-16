package csc1035.project2;
import javax.persistence.*;

@Entity
public class User {
    @Id
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
    public User(){}

    public String getUserID() {
        return userID;
    }

    public void setUserID(String id) {
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
