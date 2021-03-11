public class Students extends UniversityPerson{
    private String attending;

    public Students(String id, String firstName, String lastName, String attending) {
        super(id, firstName, lastName);
        this.attending = attending;
    }
}
