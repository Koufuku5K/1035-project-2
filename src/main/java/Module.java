class Module{
    private String moduleID;
    private String moduleName;
    private int credits;
    private int weeks;

    private int numLectures;
    private double lectureLength;

    private int numPracticals;
    private double practicalLength;

    private int numEnrolled;


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
