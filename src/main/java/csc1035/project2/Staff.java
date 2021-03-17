package csc1035.project2;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the table for staff, inherited from user class.
 * @author Kyle Anderson
 * @author William Moses
 * @author Joseph Burley
 * @author Alfie Fields
 */
@Entity(name = "Staff")
public class Staff extends User {

    @ManyToMany
    @JoinTable(
            name = "Teach",
            joinColumns = {@JoinColumn(name = "userID")},
            inverseJoinColumns = {@JoinColumn(name = "moduleID")})
    private Set<Module> modules = new HashSet<>();

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }
}
