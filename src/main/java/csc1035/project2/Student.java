package csc1035.project2;
import javax.persistence.*;

@Entity(name = "Student")
public class Student extends User{

    public Student(String userID, String firstName, String lastName) {
        super(userID, firstName, lastName);
    }

    public Student() {
        super();
    }
}
