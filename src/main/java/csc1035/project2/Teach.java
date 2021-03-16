package csc1035.project2;
import javax.persistence.*;

@Entity
public class Teach {
    @Id
    private String userID;
    @Id
    private String moduleID;


    public Teach(String userID, String moduleID) {
        this.userID = userID;
        this.moduleID = moduleID;
    }
    public Teach(){}


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getModuleID() {
        return moduleID;
    }

    public void setModuleID(String moduleID) {
        this.moduleID = moduleID;
    }
}
