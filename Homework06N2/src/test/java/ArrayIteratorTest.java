import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ArrayIteratorTest {
    @Test
    public void testHasNext() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        ArrayIterator<Integer> iterator = new ArrayIterator<>(list);
        Assert.assertTrue(iterator.hasNext());
        iterator.next();
        Assert.assertTrue(iterator.hasNext());
        iterator.next();
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void testNext() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        ArrayIterator<Integer> iterator = new ArrayIterator<>(list);
        Assert.assertEquals(list.get(0), iterator.next());
        Assert.assertEquals(list.get(1), iterator.next());
    }

    @Test
    public void testRemove() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        ArrayIterator<Integer> iterator = new ArrayIterator<>(list);
        iterator.next();
        iterator.remove();
        Assert.assertEquals(list.get(0), new Integer(2));
    }
}