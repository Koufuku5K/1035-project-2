package csc1035.project2;
import javax.persistence.*;

@Entity
public class Students extends UniversityPerson {
    @Column
    private String attending;

    public Students(String id, String firstName, String lastName, String attending) {
        super(id, firstName, lastName);
        this.attending = attending;
    }
}
