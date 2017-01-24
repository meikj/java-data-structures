import java.util.Iterator;

/**
 * Implementation of a doubly-linked list.
 *
 * @param <T> the type of elements to store in the list.
 * @author John Meikle
 */
public class LinkedList<T> implements Iterable<T> {

    /**
     * Represents a node in a doubly-linked list, storing pointers to both prev and next nodes, and an element.
     */
    public class Node {
        Node prev;
        Node next;
        T element;
        public Node(T element) { this.element = element; }
    }

    private Node head;
    private Node tail;

    /**
     * Construct a new LinkedList.
     */
    public LinkedList() { }

    /**
     * Check if the list is empty.
     * @return whether or not the list is empty.
     */
    public boolean isEmpty() {
        return head != null;
    }

    /**
     * Get the head (start) of the list.
     * @return the head element.
     * @throws IllegalStateException if the list is empty.
     */
    public T getHead() throws IllegalStateException {
        if (isEmpty())
            throw new IllegalStateException("Can't get head of empty list.");
        return head.element;
    }

    /**
     * Get the tail (end) of the list.
     * @return the tail element.
     * @throws IllegalStateException if the list is empty.
     */
    public T getTail() throws IllegalStateException {
        if (isEmpty())
            throw new IllegalStateException("Can't get tail of empty list.");
        return tail.element;
    }

    /**
     * Add am element to the start of the list.
     * @param element the element.
     */
    public void addStart(T element) {
        Node node = new Node(element);
        if (head != null) {
            head.prev = node;
            node.next = head;
        }
        head = node;

        // Ensure tail is set if not
        if (tail == null)
            tail = head;
    }

    /**
     * Add am element to the end of the list.
     * @param element the element.
     */
    public void addEnd(T element) {
        Node node = new Node(element);
        if (tail != null) {
            tail.next = node;
            node.prev = tail;
        }
        tail = node;

        // Ensure head is set if not
        if (head == null)
            head = tail;
    }

    /**
     * Add an element before the specified element in the list.
     * @param element the element on which to add from.
     * @param newElement the new element.
     * @throws IllegalArgumentException if the element does not exist, or if either its previous or next node is null.
     */
    public void addBefore(T element, T newElement) throws IllegalArgumentException {
        Node node = find(element);
        if (node == null)
            throw new IllegalArgumentException("Can't add before non-existent element in list.");
        Node newNode = new Node(newElement);
        Node prev = node.prev;
        if (prev != null)
            prev.next = newNode;
        newNode.next = node;
        newNode.prev = prev;
        node.prev = newNode;

        // Ensure head is updated
        if (node == head)
            head = newNode;
    }

    /**
     * Add an element after the specified element in the list.
     * @param element the element on which to add from.
     * @param newElement the new element.
     * @throws IllegalArgumentException if the element does not exist, or if either its previous or next node is null.
     */
    public void addAfter(T element, T newElement) throws IllegalArgumentException {
        Node node = find(element);
        if (node == null)
            throw new IllegalArgumentException("Can't add after non-existent element in list.");
        Node newNode = new Node(newElement);
        Node next = node.next;
        if (next != null)
            next.prev = newNode;
        newNode.prev = node;
        newNode.next = next;
        node.next = newNode;

        // Ensure tail is updated
        if (node == tail)
            tail = newNode;
    }

    /**
     * Delete an element from the list.
     * @param element the element.
     * @throws IllegalArgumentException if the element does not exist, or if either its previous or next node is null.
     */
    public void delete(T element) throws IllegalArgumentException {
        Node node = find(element);
        if (node == null || (node.prev == null && node.next == null))
            throw new IllegalArgumentException("Can't delete non-existent element in list.");
        Node prev = node.prev;
        Node next = node.next;
        if (prev != null)
            prev.next = next;
        if (next != null)
            next.prev = prev;
    }

    /**
     * Check if an element exists in the list.
     * @param element the element.
     * @return whether or not the element is in the list.
     */
    public boolean exists(T element) {
        return find(element) != null;
    }

    /**
     * Find an element in the list.
     * @param element the element.
     * @return the Node of the element or null if not found.
     */
    public Node find(T element) {
        Node curr = head;
        while (curr != null) {
            if (curr.element.equals(element))
                return curr;
            curr = curr.next;
        }
        return null;
    }

    /**
     * Iterate over the elements of the list.
     * @return an iterator.
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node curr = head;
            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T element = curr.element;
                curr = curr.next;
                return element;
            }
        };
    }

}
