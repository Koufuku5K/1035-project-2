package csc1035.project2;

import javax.persistence.*;

@Entity
public class Students {
    @Id
    private String studentID;
    @Column
    private String studentFirstName;
    @Column
    private String studentLastName;

    public Students(String studentID, String studentFirstName, String studentLastName) {
        this.studentID = studentID;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
    }

    public Students() {}

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }
}
