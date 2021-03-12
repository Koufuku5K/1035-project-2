package csc1035.project2;
import javax.persistence.*;

@Entity(name = "Staff")
public class Staff extends User {

    public Staff(String userID, String firstName, String lastName) {
        super(userID, firstName, lastName);
    }

    public Staff() {
        super();
    }
}
