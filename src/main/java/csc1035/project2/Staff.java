package csc1035.project2;
import javax.persistence.*;

@Entity
public class Staff extends UniversityPerson {
    @Column
    private String teaching;

    public Staff(String id, String firstName, String lastName, String teaching) {
        super(id, firstName, lastName);
        this.teaching = teaching;
    }

    public String getTeaching() {
        return teaching;
    }

    public void setTeaching(String teaching) {
        this.teaching = teaching;
    }
}
