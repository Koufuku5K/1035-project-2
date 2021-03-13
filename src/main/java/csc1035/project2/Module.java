package csc1035.project2;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Module")
public class Module{

    @Id
    @Column(updatable = false, nullable = false)
    private String moduleID;

    @Column
    private String moduleName;

    @Column
    private int credits;

    @Column
    private int weeks;

    @Column
    private int numLectures;

    @Column
    private double lectureLength;

    @Column
    private int numPracticals;

    @Column
    private double practicalLength;

    @Column
    private int numEnrolled;

    @OneToMany(mappedBy = "moduleID")
    private List<Booking> bookings;

    @ManyToMany(mappedBy = "modules")
    private Set<Staff> staff = new HashSet<>();

    @ManyToMany(mappedBy = "students")
    private Set<Student> students = new HashSet<>();

    public Module(String moduleID, String moduleName, int credits, int weeks, int numLectures, double lectureLength,
                  int numPracticals, double practicalLength, int numEnrolled, List<Booking> bookings) {
        this.moduleID = moduleID;
        this.moduleName = moduleName;
        this.credits = credits;
        this.weeks = weeks;
        this.numLectures = numLectures;
        this.lectureLength = lectureLength;
        this.numPracticals = numPracticals;
        this.practicalLength = practicalLength;
        this.numEnrolled = numEnrolled;
        this.bookings = bookings;
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

    public int getNumLectures() {
        return numLectures;
    }

    public void setNumLectures(int numLectures) {
        this.numLectures = numLectures;
    }

    public double getLectureLength() {
        return lectureLength;
    }

    public void setLectureLength(double lectureLength) {
        this.lectureLength = lectureLength;
    }

    public int getNumPracticals() {
        return numPracticals;
    }

    public void setNumPracticals(int numPracticals) {
        this.numPracticals = numPracticals;
    }

    public double getPracticalLength() {
        return practicalLength;
    }

    public void setPracticalLength(double practicalLength) {
        this.practicalLength = practicalLength;
    }

    public int getNumEnrolled() {
        return numEnrolled;
    }

    public void setNumEnrolled(int numEnrolled) {
        this.numEnrolled = numEnrolled;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Set<Staff> getStaff() {
        return staff;
    }

    public void setStaff(Set<Staff> staff) {
        this.staff = staff;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
