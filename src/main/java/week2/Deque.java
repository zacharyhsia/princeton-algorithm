package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A double-ended queue or deque.
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {
    private ListNode<Item> head;
    private ListNode<Item> tail;
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
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        ListNode<Item> newNode = new ListNode<>(item);

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
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        ListNode<Item> newNode = new ListNode<>(item);

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
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("No such an element");
        }

        ListNode<Item> node = head.next;
        head.next = node.next;
        node.next.pre = head;

        size--;
        return node.value;
    }

    /**
     * Remove and return the last item.
     * @return the last item
     */
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("No such an element");
        }

        ListNode<Item> node = tail.pre;
        node.pre.next = tail;
        tail.pre = node.pre;

        size--;
        return node.value;
    }


    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    /**
     * Iterator of deque.
     */
    private class DequeIterator implements Iterator<Item> {
        private ListNode<Item> cur = head.next;

        @Override
        public boolean hasNext() {
            return size() != 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more element.");
            }

            Item res = cur.value;

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
     * @param <Item> Generic type item.
     */
    private class ListNode<Item> {
        private Item value;
        private ListNode<Item> next;
        private ListNode<Item> pre;

        ListNode(Item item) {
            this.value = item;
        }
    }
}
