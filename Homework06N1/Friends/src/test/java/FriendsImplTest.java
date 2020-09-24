import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FriendsImplTest {
    Friends friends;

    @Mock
    Users users;

    @Before
    public void setUp() {
        friends = new FriendsImpl(users);
        when(users.find("FirstName1 LastName1")).thenReturn(new PersonImpl(0, "FirstName1", "LastName1"));
        friends.add("FirstName1 LastName1");
    }

    @Test
    public void add() {
        verify(users).find("FirstName1 LastName1");
    }

    @Test
    public void remove() {
        friends.remove(friends.getFriends().get(0));
        Assert.assertEquals(friends.getFriends().size(), 0);
    }
}