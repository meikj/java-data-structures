import java.util.Arrays;
import java.util.Iterator;

/**
 * Implementation of an array list (vector).
 *
 * @param <T> the type of elements to store in the list.
 * @author John Meikle
 */
public class ArrayList<T> implements Iterable<T> {

    public static final int DEFAULT_INITIAL_CAPACITY = 10;

    private Object[] array;
    private int capacity;
    private int size;

    /**
     * Construct an empty array list with an initial capacity.
     * @param initialCapacity the initial capacity.
     */
    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Can't set initial capacity of list to less than 0.");
        capacity = initialCapacity;
        array = new Object[capacity];
    }

    /**
     * Construct an empty array list with default initial capacity (see DEFAULT_INITIAL_CAPACITY).
     */
    public ArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * Get the size of the list.
     * @return the number of elements in the list.
     */
    public int getSize() {
        return size;
    }

    /**
     * Get the underlying element array.
     * @return the array.
     */
    public Object[] getArray() {
        return array;
    }

    /**
     * Check if the list is empty.
     * @return whether or not the list is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Add an element to the end of the list.
     * @param element the element.
     */
    public void add(T element) {
        if (size >= capacity)
            enlargeArray();
        array[size] = element;
        size++;
    }

    /**
     * Find an element in the list.
     * @param element the element.
     * @return the index.
     */
    public int find(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element))
                return i;
        }
        return -1;
    }

    /**
     * Get the element at the specified index.
     * @param index the index.
     * @return the element.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        return (T) array[index];
    }

    /**
     * Delete the element from the list, shifting elements down.
     * @param index the index of the element.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public void delete(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        array[index] = null;
        for (int i = index; i < size; i++) {
            if (i < (size - 1)) {
                Object nextElement = array[i + 1];
                array[i] = nextElement;
                array[i + 1] = null;
            }
        }
        size--;
    }

    /**
     * Delete the element from the list, shifting elements down.
     * @param element the element.
     * @throws IllegalArgumentException if the element does not exist in the list.
     */
    public void delete(T element) throws IllegalArgumentException {
        int index = find(element);
        if (index == -1)
            throw new IllegalArgumentException("Can't delete element that does not exist in list.");
        delete(index);
    }

    /**
     * Check if an element exists in the list.
     * @return whether or not the element exists.
     */
    public boolean exists(T element) {
        return find(element) != -1;
    }

    /**
     * Enlarge the array by a factor of 2 (i.e. double the capacity).
     */
    private void enlargeArray() {
        capacity *= 2;
        array = Arrays.copyOf(array, capacity);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < size && array[index] != null;
            }

            @Override
            public T next() {
                return (T) array[index++];
            }
        };
    }

}
