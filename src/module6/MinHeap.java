package module6;

import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap.
     * <p>
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MinHeap.
     * <p>
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MinHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     * <p>
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!

        if (data == null) {
            throw new IllegalArgumentException();
        }

        // resize the backingArray if full
        if (backingArray.length == size + 1) {
            resize();
        }

        backingArray[size + 1] = data;
        size++;

        // up-heap the added element (can be replaced with upheap(size);)
        int index = size;
        do {
            int parentIndex = index / 2;
            if (backingArray[index].compareTo(backingArray[parentIndex]) < 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        } while (index > 1);
    }

    private void upheap(int index) {
        if (index == 1) {
            return;
        }

        int parentIndex = index/2;
        if (backingArray[index].compareTo(backingArray[parentIndex]) < 0) {
            swap(index, parentIndex);
            upheap(parentIndex);
        }
    }

    private void swap(int i, int j) {
        T temp = backingArray[j];
        backingArray[j] = backingArray[i];
        backingArray[i] = temp;
    }

    private void resize() {
        T[] newArray = (T[]) new Comparable[backingArray.length * 2];

        for (int i = 0; i < backingArray.length; i++) {
            newArray[i] = backingArray[i];
        }

        backingArray = newArray;
    }

    private int numOfChildren(int index) {
        if (2 * index > size) {
            return 0;
        } else if (2 * index + 1 > size) {
            return 1;
        }
        return 2;
    }

    private int minIndex(int i, int j) {
        return backingArray[i].compareTo(backingArray[j]) < 0 ? i : j;
    }


    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     * <p>
     * Method should run in O(log n) time.
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
    public T remove() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!

        if (size == 0) {
            throw new NoSuchElementException();
        }

        // replace the top element with the last element
        T dataRemoved = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;

        // down heap the element that we added at the root
        // all of this can be replaced with downheap(1)
        int currIndex = 1;
        do {
            int numChildren = numOfChildren(currIndex); // how many children it has
            // find out the index of the min number amongst the parent and its children
            int minIndex;
            if (numChildren == 0) {
                break;
            } else if (numChildren == 1) {
                minIndex = minIndex(currIndex, currIndex * 2);
            } else {
                minIndex = minIndex(currIndex, minIndex(currIndex * 2, currIndex * 2 + 1));
            }
            // swap if minimum is not the parent
            if (minIndex != currIndex) {
                swap(minIndex, currIndex);
                currIndex = minIndex;
            } else {
                break;
            }

        } while (currIndex <= size / 2);

        return dataRemoved;
    }



    private void downheap(int currIndex) {
        if (currIndex > size / 2) {
            return;
        }

        int numChildren = numOfChildren(currIndex);

        int minIndex;
        if (numChildren != 0) {
            if (numChildren == 1) {
                minIndex = minIndex(currIndex, currIndex * 2);
            } else {
                minIndex = minIndex(currIndex, minIndex(currIndex * 2, currIndex * 2 + 1));
            }

            if (minIndex != currIndex) {
                swap(minIndex, currIndex);
                downheap(minIndex);
            }
        }
    }


    /**
     * Returns the backing array of the heap.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}

