package csc1035.project2;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Student")
public class Student extends User{

    @ManyToMany
    @JoinTable(
            name = "Take",
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
