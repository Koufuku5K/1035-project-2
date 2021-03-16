package csc1035.project2;
import javax.persistence.*;

@Entity
public class Take{
    @Id
    private String userID;
    @Id
    private String moduleID;


    public Take(String userID, String moduleID) {
        this.userID = userID;
        this.moduleID = moduleID;
    }
    public Take(){}


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