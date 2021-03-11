package csc1035.project2;

import javax.persistence.*;

@Entity
public class Staff {
    @Id
    private String staffID;
    @Column
    private String staffFirstName;
    @Column
    private String staffSecondName;

    public Staff(String staffID, String staffFirstName, String staffSecondName) {
        this.staffID = staffID;
        this.staffFirstName = staffFirstName;
        this.staffSecondName = staffSecondName;
    }

    public Staff() {}

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getStaffFirstName() {
        return staffFirstName;
    }

    public void setStaffFirstName(String staffFirstName) {
        this.staffFirstName = staffFirstName;
    }

    public String getStaffSecondName() {
        return staffSecondName;
    }

    public void setStaffSecondName(String staffSecondName) {
        this.staffSecondName = staffSecondName;
    }
}
