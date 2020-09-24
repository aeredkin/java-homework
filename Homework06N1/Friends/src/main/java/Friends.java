import java.util.ArrayList;

public interface Friends {
    void add(String name);

    void remove(Person person);

    ArrayList<Person> getFriends();
}
