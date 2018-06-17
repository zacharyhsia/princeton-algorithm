import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A randomized queue is similar to a stack or queue, except that the item removed is chosen uniformly
 * at random from items in the data structure.
 * @param <T> Generic type of items
 */
public class RandomizedQueue<T> implements Iterable<T> {
    private static final int START_SIZE = 1;
    private Object[] queue;
    private int size;

    /**
     * Construct an empty randomized queue.
     */
    public RandomizedQueue() {
        queue = (T[]) new Object[START_SIZE];
        size = 0;
    }

    /**
     * Judge if the queue is empty;
     * @return true if empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Get queue size;
     * @return queue size
     */
    public int size() {
        return size;
    }

    /**
     * Add the item to queue.
     * @param item item to add
     */
    public void enqueue(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        queue[size] = item;
        size++;

        // check if need to resize the array
        if (size > queue.length / 2) {
            resize(queue.length * 2);
        }
    }

    /**
     * Remove and return a random item.
     * @return the item
     */
    public T dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("Cannot dequeue with an empty queue.");
        }

        T res = null;
        int randomIndex = 0;

        while (res == null) {
            randomIndex = StdRandom.uniform(0, size);
            res = (T) queue[randomIndex];
        }

        // mark the object as deleted in array
        queue[randomIndex] = null;

        // check if resizing array is needed
        if (size <= queue.length / 4) {
            resize(queue.length / 4);
        }

        return res;
    }

    /**
     * Return a random item (no remove).
     * @return a random item
     */
    public T sample() {
        if (size == 0) {
            throw new NoSuchElementException("Cannot sample with an empty queue.");
        }

        T res = null;

        while (res == null) {
            int randomIndex = StdRandom.uniform(0, size);
            res = (T) queue[randomIndex];
        }

        return res;
    }

    private void resize(int newSize) {
        Object[] newQueue = (T[]) new Object[newSize];
        int i = 0;
        for (Object item: queue) {
            if (item != null) {
                newQueue[i] = item;
                i++;
            }
        }

        queue = newQueue;
    }

    /**
     * Return an independent iterator over items in random order;
     * @return iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new RandomizedQueueIterator();
    }

    /**
     * The iterator of the RandomizedQueue.
     */
    class RandomizedQueueIterator implements Iterator<T> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            return null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Does not support remove operation.");
        }
    }
}
