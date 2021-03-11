import javax.persistence.*;


@Entity(name = "Module")
public class Module {

    @Id
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

    public Module(){
    }

    public Module(String moduleID, String moduleName, int credits, int weeks, int numLectures, double lectureLength, int numPracticals, double practicalLength, int numEnrolled) {
        this.moduleID = moduleID;
        this.moduleName = moduleName;
        this.credits = credits;
        this.weeks = weeks;
        this.numLectures = numLectures;
        this.lectureLength = lectureLength;
        this.numPracticals = numPracticals;
        this.practicalLength = practicalLength;
        this.numEnrolled = numEnrolled;
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
}
