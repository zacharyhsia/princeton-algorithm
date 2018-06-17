import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A double-ended queue or deque.
 * @param <T>
 */
public class Deque<T> implements Iterable<T> {
    private ListNode<T> head;
    private ListNode<T> tail;
    private int size;

    /**
     * Construct and empty deque.
     */
    public Deque() {
        head = new ListNode<>(null);
        tail = new ListNode<>(null);
        size = 0;

        head.next = tail;
        head.pre = null;

        tail.pre = head;
        tail.next = null;
    }

    /**
     * Check if the deque is empty.
     * @return true if empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Get the current size of the deque.
     * @return size of the deque
     */
    public int size() {
        return size;
    }

    /**
     * Add an item to the front/head.
     * @param item item to add.
     */
    public void addFirst(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        ListNode<T> newNode = new ListNode<>(item);

        newNode.next = head.next;
        newNode.pre = head;

        head.next.pre = newNode;
        head.next = newNode;

        size++;
    }

    /**
     * Add an item to the end/tail.
     * @param item item to add
     */
    public void addLast(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        ListNode<T> newNode = new ListNode<>(item);

        newNode.next = tail;
        newNode.pre = tail.pre;

        tail.pre.next = newNode;
        tail.pre = newNode;

        size++;
    }

    /**
     * Remove and return the first item.
     * @return the first item
     */
    public T removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("No such an element");
        }

        ListNode<T> node = head.next;
        head.next = node.next;
        node.next.pre = head;

        size--;
        return node.value;
    }

    /**
     * Remove and return the last item.
     * @return the last item
     */
    public T removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("No such an element");
        }

        ListNode<T> node = tail.pre;
        node.pre.next = tail;
        tail.pre = node.pre;

        size--;
        return node.value;
    }


    @Override
    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    /**
     * Iterator of deque.
     */
    class DequeIterator implements Iterator<T> {
        private ListNode<T> cur = head.next;

        @Override
        public boolean hasNext() {
            return size() != 0;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more element.");
            }

            T res = cur.value;

            cur = cur.next;

            return res;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Does not support remove operation.");
        }
    }

    /**
     * ListNode.
     * @param <T> Generic type item.
     */
    class ListNode<T> {
        private T value;
        private ListNode<T> next;
        private ListNode<T> pre;

        ListNode(T item) {
            this.value = item;
        }
    }
//    Throw a java.lang.IllegalArgumentException if the client calls either addFirst() or addLast() with a null argument.
//    Throw a java.util.NoSuchElementException if the client calls either removeFirst() or removeLast when the deque is empty.
//    Throw a java.util.NoSuchElementException if the client calls the next() method in the iterator when there are no more items to return.
//    Throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator.

}
