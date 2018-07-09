package week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A randomized queue is similar to a stack or queue, except that the item removed is chosen uniformly
 * at random from items in the data structure.
 * @param <Item> Generic type of items
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int START_SIZE = 1;
    private static final int ENLARGE_THRESHOLD = 2;
    private static final int SHRINK_THRESHOLD = 4;
    private Object[] queue;
    private int size;

    /**
     * Construct an empty randomized queue.
     */
    public RandomizedQueue() {
        queue = (Item[]) new Object[START_SIZE];
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
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        queue[size] = item;
        size++;

        // check if need to resize the array
        if (size > queue.length / ENLARGE_THRESHOLD) {
            resize(queue.length * 2);
        }
    }

    /**
     * Remove and return a random item.
     * @return the item
     */
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("Cannot dequeue with an empty queue.");
        }

        Item res = null;
        int randomIndex = 0;

        while (res == null) {
            randomIndex = StdRandom.uniform(0, size);
            res = (Item) queue[randomIndex];
        }

        // mark the object as deleted in array
        queue[randomIndex] = null;
        size--;

        // check if resizing array is needed
        if (size <= queue.length / SHRINK_THRESHOLD) {
            resize(queue.length / 2);
        }

        return res;
    }

    /**
     * Return a random item (no remove).
     * @return a random item
     */
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException("Cannot sample with an empty queue.");
        }

        Item res = null;

        while (res == null) {
            int randomIndex = StdRandom.uniform(0, size);
            res = (Item) queue[randomIndex];
        }

        return res;
    }

    private void resize(int newSize) {
        Object[] newQueue = (Item[]) new Object[newSize];
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
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    /**
     * Itemhe iterator of the week2.RandomizedQueue.
     */
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int[] randomIndexes = new int[size];
        private int cur = 0;

        RandomizedQueueIterator() {
            for (int i = 0; i < size; i++) {
                randomIndexes[i] = i;
            }

            StdRandom.shuffle(randomIndexes);
        }

        @Override
        public boolean hasNext() {
            return cur != size;
        }

        @Override
        public Item next() {
            Item res = (Item) queue[randomIndexes[cur]];
            cur++;
            return res;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Does not support remove operation.");
        }
    }
}
