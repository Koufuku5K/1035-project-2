public class Staff {
    private String staffID;
    private String staffFirstName;
    private String staffSecondName;

    public Staff(String staffID, String staffFirstName, String staffSecondName) {
        this.staffID = staffID;
        this.staffFirstName = staffFirstName;
        this.staffSecondName = staffSecondName;
    }

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
