import org.junit.Before;
import org.junit.Test;
import week2.Deque;

import java.util.Iterator;

import static org.junit.Assert.*;

public class DequeTest {
    Deque<String> deque;

    @Before
    public void setUp() {
        deque = new Deque<>();
    }
    @Test
    public void isEmpty() throws Exception {
        assertTrue(deque.isEmpty());
        deque.addFirst("string");
        assertFalse(deque.isEmpty());
    }

    @Test
    public void size() throws Exception {
        assertEquals(deque.size(), 0);
        deque.addFirst("string");
        assertEquals(deque.size(), 1);
        deque.removeFirst();
        assertEquals(deque.size(), 0);
    }

    @Test
    public void addFirst() throws Exception {
        deque.addFirst("first");
        deque.addFirst("second");
        Iterator<String> iterator = deque.iterator();
        assertEquals(iterator.next(), "second");
        assertEquals(iterator.next(), "first");
    }

    @Test
    public void addLast() throws Exception {
        deque.addLast("first");
        deque.addLast("second");
        Iterator<String> iterator = deque.iterator();
        assertEquals(iterator.next(), "first");
        assertEquals(iterator.next(), "second");
    }

    @Test
    public void removeFirst() throws Exception {
        deque.addLast("first");
        deque.addLast("second");
        assertEquals("first", deque.removeFirst());
    }

    @Test
    public void removeLast() throws Exception {
        deque.addLast("first");
        deque.addLast("second");
        assertEquals("second", deque.removeLast());
    }
}