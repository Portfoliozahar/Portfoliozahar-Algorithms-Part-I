package src.queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int size;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node<Item> node = new Node<>();
        node.item = item;
        node.next = first;
        node.prev = null;

        if (isEmpty()) {
            last = node;
        }
        else {
            first.prev = node;
        }

        first = node;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node<Item> node = new Node<>();
        node.item = item;
        node.next = null;
        node.prev = last;

        if (isEmpty()) {
            first = node;
        }
        else {
            last.next = node;
        }

        last = node;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = first.item;
        first = first.next;
        size--;

        if (isEmpty()) {
            last = null;
        }
        else {
            first.prev = null;
        }

        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = last.item;
        last = last.prev;
        size--;

        if (isEmpty()) {
            first = null;
        }
        else {
            last.next = null;
        }

        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator(first);
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current;

        DequeIterator(Node<Item> start) {
            current = start;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();

        System.out.println("isEmpty: " + deque.isEmpty());
        System.out.println("size: " + deque.size());

        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(3);
        deque.addLast(4);

        System.out.println("isEmpty: " + deque.isEmpty());
        System.out.println("size: " + deque.size());

        System.out.print("iterator: ");
        Iterator<Integer> iterator = deque.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        Iterator<Integer> removeTest = deque.iterator();
        try {
            removeTest.remove();
        }
        catch (UnsupportedOperationException e) {
            System.out.println("iterator.remove(): UnsupportedOperationException");
        }

        System.out.println("removeFirst: " + deque.removeFirst());
        System.out.println("removeLast: " + deque.removeLast());
        System.out.println("removeFirst: " + deque.removeFirst());
        System.out.println("removeLast: " + deque.removeLast());

        System.out.println("isEmpty after removals: " + deque.isEmpty());
        System.out.println("size after removals: " + deque.size());

        try {
            deque.removeFirst();
        }
        catch (NoSuchElementException e) {
            System.out.println("empty removeFirst(): NoSuchElementException");
        }

        try {
            deque.removeLast();
        }
        catch (NoSuchElementException e) {
            System.out.println("empty removeLast(): NoSuchElementException");
        }

        Iterator<Integer> emptyIterator = deque.iterator();
        try {
            emptyIterator.next();
        }
        catch (NoSuchElementException e) {
            System.out.println("empty iterator.next(): NoSuchElementException");
        }

        try {
            deque.addFirst(null);
        }
        catch (IllegalArgumentException e) {
            System.out.println("addFirst(null): IllegalArgumentException");
        }

        try {
            deque.addLast(null);
        }
        catch (IllegalArgumentException e) {
            System.out.println("addLast(null): IllegalArgumentException");
        }
    }
}
