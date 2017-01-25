/**
 * Interface for a queue.
 *
 * @author John Meikle
 */
public interface Queue<T> {

    /**
     * Check if the queue is empty.
     * @return whether or not the queue is empty.
     */
    public boolean isEmpty();

    /**
     * Enqueue an element to the queue. In other words, add an element to the start of queue.
     * @param element the element to add.
     */
    public void enqueue(T element);

    /**
     * Dequeue an element from the queue. In other words, remove and return the element at the start of queue.
     * @return the element at start of queue.
     */
    public T dequeue();

    /**
     * Get the element at the start of the queue.
     * @return the element at start.
     */
    public T getStart();

}
