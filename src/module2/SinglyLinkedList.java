package module2;

import java.util.NoSuchElementException;

/**
 * Your implementation of a Singly-Linked List.
 */
public class SinglyLinkedList<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private module2.SinglyLinkedListNode<T> head;
    private module2.SinglyLinkedListNode<T> tail;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the element to the front of the list.
     * <p>
     * Method should run in O(1) time.
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException();
        }

        if (size == 0) {
            head = new SinglyLinkedListNode<>(data);
            tail = head;
        } else {
            SinglyLinkedListNode<T> newElement = new SinglyLinkedListNode<>(data, head.getNext());
            newElement.setNext(head);
            head = newElement;
        }

        size++;
    }

    /**
     * Adds the element to the back of the list.
     * <p>
     * Method should run in O(1) time.
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException();
        }

        if (size == 0) {
            addToFront(data);
            return;
        }

        SinglyLinkedListNode<T> newElement = new SinglyLinkedListNode<>(data);
        tail.setNext(newElement);
        tail = newElement;

        size++;
    }

    /**
     * Removes and returns the first data of the list.
     * <p>
     * Method should run in O(1) time.
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (size == 0) {
            throw new NoSuchElementException();
        }

        // retrieve the data at the front of the list
        T dataRemoved = head.getData();

        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
        }

        size--;

        return dataRemoved;
    }

    /**
     * Removes and returns the last data of the list.
     * <p>
     * Method should run in O(n) time.
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (size == 0) {
            throw new NoSuchElementException();
        } else if (size == 1) {
            return removeFromFront();
        }

        // retrieve the data at the back of the list
        T dataRemoved = tail.getData();

        // assign the new tail
        SinglyLinkedListNode<T> current = head;
        while (current.getNext().getNext() != null) {
            current = current.getNext();
        }

        current.setNext(null);
        tail = current;

        size--;

        return dataRemoved;
    }

    /**
     * Returns the head node of the list.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public module2.SinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail node of the list.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public module2.SinglyLinkedListNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
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