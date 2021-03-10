package csc1035.project2;

class Module{
    private String moduleID;
    private String moduleName;
    private int credits;
    private int weeks;

    public Module(String moduleID, String moduleName, int credits, int weeks) {
        this.moduleID = moduleID;
        this.moduleName = moduleName;
        this.credits = credits;
        this.weeks = weeks;
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
}