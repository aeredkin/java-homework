import java.util.ArrayList;

public class FriendsImpl implements Friends {
    Users users;
    ArrayList<Person> friends = new ArrayList<>();

    @Override
    public void add(String name) {
        friends.add(users.find(name));
    }

    @Override
    public void remove(Person person) {
        friends.remove(person);
    }

    @Override
    public ArrayList<Person> getFriends() {
        return friends;
    }

    public FriendsImpl(Users users) {
        this.users = users;
    }
}
