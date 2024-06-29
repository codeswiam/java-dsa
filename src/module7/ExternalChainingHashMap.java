package module7;

import module7.ExternalChainingMapEntry;

import java.util.NoSuchElementException;

/**
 * Your implementation of a ExternalChainingHashMap.
 */
public class ExternalChainingHashMap<K, V> {

    /*
     * The initial capacity of the ExternalChainingHashMap when created with the
     * default constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * The max load factor of the ExternalChainingHashMap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final double MAX_LOAD_FACTOR = 0.67;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private ExternalChainingMapEntry<K, V>[] table;
    private int size;

    /**
     * Constructs a new ExternalChainingHashMap with an initial capacity of INITIAL_CAPACITY.
     */
    public ExternalChainingHashMap() {
        //DO NOT MODIFY THIS METHOD!
        table = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[INITIAL_CAPACITY];
    }

    /**
    * Returns the index where a new key value pair is going to be added
    * */
    private int getIndex(K key) {
        return getIndex(key, table.length);
    }

    private int getIndex(K key, int length) {
        return Math.abs(key.hashCode() % length);
    }

    /**
     * Adds the given key-value pair to the map. If an entry in the map
     * already has this key, replace the entry's value with the new one
     * passed in.
     * <p>
     * In the case of a collision, use external chaining as your resolution
     * strategy. Add new entries to the front of an existing chain, but don't
     * forget to check the entire chain for duplicate keys first.
     * <p>
     * If you find a duplicate key, then replace the entry's value with the new
     * one passed in. When replacing the old value, replace it at that position
     * in the chain, not by creating a new entry and adding it to the front.
     * <p>
     * Before actually adding any data to the HashMap, you should check to
     * see if the table would violate the max load factor if the data was
     * added. Resize if the load factor (LF) is greater than max LF (it is
     * okay if the load factor is equal to max LF). For example, let's say
     * the table is of length 5 and the current size is 3 (LF = 0.6). For
     * this example, assume that no elements are removed in between steps.
     * If another entry is attempted to be added, before doing anything else,
     * you should check whether (3 + 1) / 5 = 0.8 is larger than the max LF.
     * It is, so you would trigger a resize before you even attempt to add
     * the data or figure out if it's a duplicate. Be careful to consider the
     * differences between integer and double division when calculating load
     * factor.
     * <p>
     * When regrowing, resize the length of the backing table to
     * (2 * old length) + 1. You should use the resizeBackingTable method to do so.
     *
     * @param key   The key to add.
     * @param value The value to add.
     * @return null if the key was not already in the map. If it was in the
     * map, return the old value associated with it.
     * @throws java.lang.IllegalArgumentException If key or value is null.
     */
    public V put(K key, V value) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }

        // calculate the index based on the key
        int index = getIndex(key);
        ExternalChainingMapEntry<K, V> head = table[index];

        // if it's not empty, see if there's a duplicate key in the chain
        if (head != null) {
            ExternalChainingMapEntry<K, V> current = head;

            do {
                // if you find a duplicate key, replace the value and return the old one
                if (current.getKey().equals(key)) {
                    V oldValue = current.getValue();
                    current.setValue(value);
                    return oldValue;
                }
                current = current.getNext();
            } while (current != null);
        }

        // if we're here, either the index is empty, or no duplicate key exists

        // calculate the load factor and resize if necessary
        if (getLoadFactor() > MAX_LOAD_FACTOR) {
            resizeBackingTable(table.length * 2 + 1);
        }

        // head == null doesn't create an issue
        ExternalChainingMapEntry<K, V> newElement = new ExternalChainingMapEntry<>(key, value, head);
        table[index] = newElement;
        size++;
        return null;
    }

    /**
     * Returns the load factor if we were to add an element
     * */
    private double getLoadFactor() {
        return (double) (size + 1) / (double) table.length;
    }

    /**
     * Removes the entry with a matching key from the map.
     *
     * @param key The key to remove.
     * @return The value associated with the key.
     * @throws java.lang.IllegalArgumentException If key is null.
     * @throws java.util.NoSuchElementException   If the key is not in the map.
     */
    public V remove(K key) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (key == null) {
            throw new IllegalArgumentException();
        }

        // calculate the index based on the key
        int index = getIndex(key);
        ExternalChainingMapEntry<K, V> head = table[index];

        if (head != null) {
            // this works when the head is the only element
            // and when there's other elements
            if (head.getKey().equals(key)) {
                V oldValue = head.getValue();
                table[index] = head.getNext();
                size--;
                return oldValue;
            }

            ExternalChainingMapEntry<K, V> current = head;

            do {
                // we'll look ahead to see if the next element has the key
                // we've already checked the head
                ExternalChainingMapEntry<K, V> next = current.getNext();
                if (next.getKey().equals(key)) {
                    V oldValue = next.getValue();
                    current.setNext(next.getNext());
                    size--;
                    return oldValue;
                }
                current = current.getNext();
            } while (current.getNext() != null);
        }

        throw new NoSuchElementException();
    }

    /**
     * Helper method stub for resizing the backing table to length.
     * <p>
     * This method should be called in put() if the backing table needs to
     * be resized.
     * <p>
     * You should iterate over the old table in order of increasing index and
     * add entries to the new table in the order in which they are traversed.
     * <p>
     * Since resizing the backing table is working with the non-duplicate
     * data already in the table, you won't need to explicitly check for
     * duplicates.
     * <p>
     * Hint: You cannot just simply copy the entries over to the new table.
     *
     * @param length The new length of the backing table.
     */
    private void resizeBackingTable(int length) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        ExternalChainingMapEntry<K, V>[] newTable = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[length];

        for (ExternalChainingMapEntry<K,V> entry : table) {
            if (entry != null) {
                while (entry != null) {
                    // recalculate index based on new length
                    int newIndex = getIndex(entry.getKey(), length);

                    // add to the front of the chained list at that index
                    entry.setNext(newTable[newIndex]);
                    newTable[newIndex] = entry;

                    entry = entry.getNext();
                }
            }
        }

        table = newTable;
    }

    /**
     * Returns the table of the map.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The table of the map.
     */
    public ExternalChainingMapEntry<K, V>[] getTable() {
        // DO NOT MODIFY THIS METHOD!
        return table;
    }

    /**
     * Returns the size of the map.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the map.
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}

