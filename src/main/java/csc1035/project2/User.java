package csc1035.project2;
import javax.persistence.*;
<<<<<<< HEAD
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

    @OneToMany(mappedBy = "userID")
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
=======

@Entity
public class User {
    @Id
    private String id;
    @Column
    private String firstName;
    @Column
    private String lastName;

    public User(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
<<<<<<<< HEAD:src/main/java/csc1035/project2/User.java
    public User(){}
========
>>>>>>>> Rename UniversityPerson to User:src/main/java/csc1035/project2/UniversityPerson.java

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
>>>>>>> Rename UniversityPerson to User
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
<<<<<<< HEAD

    public List<Booking> getUser() {
        return user;
    }

    public void setUser(List<Booking> user) {
        this.user = user;
    }
=======
>>>>>>> Rename UniversityPerson to User
}
