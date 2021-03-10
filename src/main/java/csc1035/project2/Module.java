package csc1035.project2;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Module")
class Module{

    @Id
    @Column(updatable = false, nullable = false)
    private String moduleID;

    @Column
    private String moduleName;

    @Column
    private int credits;

    @Column
    private int weeks;

    @OneToMany(mappedBy = "module")
    private List<Booking> module;

    public Module(String moduleID, String moduleName, int credits, int weeks, List<Booking> module) {
        this.moduleID = moduleID;
        this.moduleName = moduleName;
        this.credits = credits;
        this.weeks = weeks;
        this.module = module;
    }

    public Module(){
    }

    public String getModuleID() {
        return moduleID;
    }

    public void setModuleID(String moduleID) {
        this.moduleID = moduleID;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getWeeks() {
        return weeks;
    }

    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }

    public List<Booking> getModule() {
        return module;
    }

    public void setModule(List<Booking> module) {
        this.module = module;
    }
}