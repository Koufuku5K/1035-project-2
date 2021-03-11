public class Staff extends UniversityPerson{
    private String teaching;

    public Staff(String id, String firstName, String lastName, String teaching) {
        super(id, firstName, lastName);
        this.teaching = teaching;
    }
}
