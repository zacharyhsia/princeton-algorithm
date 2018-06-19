import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class RandomizedQueueTest {
    private RandomizedQueue<String> randomizedQueue;

    @Before
    public void setUp() throws Exception {
        randomizedQueue = new RandomizedQueue<>();
    }

    @Test
    public void isEmpty() throws Exception {
        assertTrue(randomizedQueue.isEmpty());
        randomizedQueue.enqueue("hello");
        assertFalse(randomizedQueue.isEmpty());
    }

    @Test
    public void size() throws Exception {
        assertEquals(randomizedQueue.size(), 0);
        randomizedQueue.enqueue("zach");
        assertEquals(randomizedQueue.size(), 1);
        randomizedQueue.enqueue("handsome");
        assertEquals(randomizedQueue.size(), 2);
    }

    @Test
    public void dequeue() throws Exception {
        assertEquals(randomizedQueue.size(), 0);
        randomizedQueue.enqueue("zach");
        assertEquals(randomizedQueue.size(), 1);
        randomizedQueue.enqueue("handsome");
        assertEquals(randomizedQueue.size(), 2);
        randomizedQueue.sample();
        randomizedQueue.sample();
        assertEquals(randomizedQueue.size(), 2);
        randomizedQueue.dequeue();
        assertEquals(randomizedQueue.size(), 1);
        randomizedQueue.dequeue();
        assertEquals(randomizedQueue.size(), 0);
    }

    @Test
    public void iterator() throws Exception {
        randomizedQueue.enqueue("zach");
        randomizedQueue.enqueue("handsome");

        for (String str : randomizedQueue) {
            System.out.println(str);
        }
    }

}