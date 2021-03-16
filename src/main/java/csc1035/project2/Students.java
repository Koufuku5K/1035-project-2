package csc1035.project2;
import javax.persistence.*;

@Entity
public class Students extends User {
    @Column
    private String attending;

    public Students(String id, String firstName, String lastName, String attending) {
        super(id, firstName, lastName);
        this.attending = attending;
    }
    public Students(){}
    public String getAttending() {
        return attending;
    }

    public void setAttending(String attending) {
        this.attending = attending;
    }
}
