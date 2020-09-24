import java.util.ArrayList;
import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T> {
    protected final ArrayList<T> list;
    protected int currentPosition;

    public ArrayIterator(ArrayList<T> list) {
        this.list = list;
        this.currentPosition = -1;
    }

    @Override
    public boolean hasNext() {
        return currentPosition < list.size() - 1;
    }

    @Override
    public T next() {
        return list.get(++currentPosition);
    }

    @Override
    public void remove() {
        list.remove(currentPosition);
    }
}
