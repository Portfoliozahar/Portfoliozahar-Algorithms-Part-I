package src.queues;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[2];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (size == items.length) {
            resize(2 * items.length);
        }

        items[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int randomIndex = StdRandom.uniformInt(size);
        Item item = items[randomIndex];

        items[randomIndex] = items[size - 1];
        items[size - 1] = null;
        size--;

        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return items[StdRandom.uniformInt(size)];
    }

    // return a random item in random order
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private class RandomizedIterator implements Iterator<Item> {
        private final Item[] shuffled;
        private int current;

        RandomizedIterator() {
            shuffled = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                shuffled[i] = items[i];
            }

            StdRandom.shuffle(shuffled);
            current = 0;
        }

        public boolean hasNext() {
            return current < shuffled.length;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return shuffled[current++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    // unit testing
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        System.out.println("isEmpty: " + queue.isEmpty());
        System.out.println("size: " + queue.size());

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);

        System.out.println("isEmpty: " + queue.isEmpty());
        System.out.println("size: " + queue.size());
        System.out.println("sample: " + queue.sample());

        System.out.print("iterator: ");
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        Iterator<Integer> removeTest = queue.iterator();
        try {
            removeTest.remove();
        }
        catch (UnsupportedOperationException e) {
            System.out.println("iterator.remove(): UnsupportedOperationException");
        }

        System.out.println("dequeue: " + queue.dequeue());
        System.out.println("dequeue: " + queue.dequeue());
        System.out.println("size after dequeues: " + queue.size());

        while (!queue.isEmpty()) {
            System.out.println("dequeue: " + queue.dequeue());
        }

        try {
            queue.dequeue();
        }
        catch (NoSuchElementException e) {
            System.out.println("empty dequeue(): NoSuchElementException");
        }

        try {
            queue.sample();
        }
        catch (NoSuchElementException e) {
            System.out.println("empty sample(): NoSuchElementException");
        }

        try {
            queue.enqueue(null);
        }
        catch (IllegalArgumentException e) {
            System.out.println("enqueue(null): IllegalArgumentException");
        }

        Iterator<Integer> emptyIterator = queue.iterator();
        try {
            emptyIterator.next();
        }
        catch (NoSuchElementException e) {
            System.out.println("empty iterator.next(): NoSuchElementException");
        }
    }
}
