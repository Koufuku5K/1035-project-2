package csc1035.project2;
import javax.persistence.*;

@Entity
public class UniversityPerson {
    @Id
    private String id;
    @Column
    private String firstName;
    @Column
    private String lastName;

    public UniversityPerson(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public UniversityPerson(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
