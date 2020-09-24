public class PersonImpl implements Person {
    private int id;
    private String firstName;
    private String lastName;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public PersonImpl(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
