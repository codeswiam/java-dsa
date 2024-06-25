package module1;

import java.util.NoSuchElementException;

/**
 * Your implementation of a Module1.ArrayList.
 */
public class ArrayList<T> {

    /*
     * The initial capacity of the Module1.ArrayList.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 9;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size = 0;

    private void resize() {
        T[] newArray = (T[]) new Object[size * 2];

        int i = 0;
        for (T element: backingArray) {
            newArray[i] = element;
            i++;
        }

        backingArray = newArray;
    }

    /**
     * This is the constructor that constructs a new Module1.ArrayList.
     * <p>
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast an Object[] to a T[] to get the generic typing.
     */
    public ArrayList() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Adds the data to the front of the list.
     * <p>
     * This add may require elements to be shifted.
     * <p>
     * Method should run in O(n) time.
     *
     * @param data the data to add to the front of the list
     * @throws IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException();
        }

        // check if array needs to be resized
        if (size == backingArray.length) {
            resize();
        }

        // shift elements
        for (int i = size; i > 0; i--) {
            backingArray[i] = backingArray[i-1];
        }

        // add to the front
        backingArray[0] = data;

        // increase size
        size++;
    }

    /**
     * Adds the data to the back of the list.
     * <p>
     * Method should run in amortized O(1) time.
     *
     * @param data the data to add to the back of the list
     * @throws IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException();
        }

        // check if array needs to be resized
        if (size == backingArray.length) {
            resize();
        }

        // add to the back
        backingArray[size] = data;

        // increase size
        size++;
    }

    /**
     * Adds the data to the specified index.
     * <p>
     * Must be O(1) for index size and O(n) for all other cases.
     * <p>
     * ASSUMPTIONS:
     * - You may assume that the backingArray will not need to be resized.
     * - You may assume that the index is valid [0, size].
     * - You may assume that the data will never be null.
     *
     * @param index the index at which to add the new data
     * @param data  the data to add at the specified index
     */
    public void addAtIndex(int index, T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!

        if (index != size) {
            for (int i = size; i > index; i--) {
                backingArray[i] = backingArray[i-1];
            }
        }

        backingArray[index] = data;

        size++;
    }

    /**
     * Removes and returns the first data of the list.
     * <p>
     * Do not shrink the backing array.
     * <p>
     * This remove may require elements to be shifted.
     * <p>
     * Method should run in O(n) time.
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!

        if (size == 0) {
            throw new NoSuchElementException();
        }

        T frontData = backingArray[0];

        // shift elements
        for (int i = 0; i < size - 1; i++) {
            backingArray[i] = backingArray[i+1];
        }

        backingArray[size-1] = null;

        // decrement
        size--;

        return frontData;
    }

    /**
     * Removes and returns the last data of the list.
     * <p>
     * Do not shrink the backing array.
     * <p>
     * Method should run in O(1) time.
     *
     * @return the data formerly located at the back of the list
     * @throws NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!

        if (size == 0) {
            throw new NoSuchElementException();
        }

        T backData = backingArray[size-1];

        backingArray[size-1] = null;

        size--;

        return backData;
    }

    /**
     * Returns the backing array of the list.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the list.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}

