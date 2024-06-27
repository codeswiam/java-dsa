package module5;

import module5.BSTNode;
import org.w3c.dom.Node;

import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the tree.
     * <p>
     * This must be done recursively.
     * <p>
     * The new data should become a leaf in the tree.
     * <p>
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     * <p>
     * Should be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to add to the tree.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException();
        }
        root = add(root, data);
    }

    private BSTNode<T> add(BSTNode<T> current, T data) {
        if (current == null) {
            size++;
            return new BSTNode<>(data);
        } else if (data.compareTo(current.getData()) < 0) {
            current.setLeft(add(current.getLeft(), data));
        } else if (data.compareTo(current.getData()) > 0) {
            current.setRight(add(current.getRight(), data));
        }
        return current;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     * <p>
     * This must be done recursively.
     * <p>
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the SUCCESSOR to
     * replace the data. You should use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     * <p>
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     * <p>
     * Hint: Should you use value equality or reference equality?
     * <p>
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If data is null.
     * @throws java.util.NoSuchElementException   If the data is not in the tree.
     */
    public T remove(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException();
        }
        BSTNode<T> dummy = new BSTNode<>(null);
        root = remove(root, data, dummy);
        return dummy.getData();
    }

    private BSTNode<T> remove(BSTNode<T> current, T data, BSTNode<T> dummy) {
        if (current == null) {
            throw new NoSuchElementException();
        } else if (data.compareTo(current.getData()) < 0) {
            current.setLeft(remove(current.getLeft(), data, dummy));
        } else if (data.compareTo(current.getData()) > 0) {
            current.setRight(remove(current.getRight(), data, dummy));
        } else {
            dummy.setData(current.getData());
            size--;
            if (current.getRight() == null && current.getLeft() == null) {
                return null;
            } else if (current.getRight() == null) {
                return current.getLeft();
            } else if (current.getLeft() == null) {
                return current.getRight();
            } else {
                BSTNode<T> dummy2 = new BSTNode<>(null);
                current.setRight(removeSuccessor(current.getRight(), dummy2));
                current.setData(dummy2.getData());
            }
        }
        return current;
    }

    private BSTNode<T> removeSuccessor(BSTNode<T> current, BSTNode<T> dummy) {
        if (current.getLeft() == null) {
            dummy.setData(current.getData());
            return current.getRight();
        } else {
            current.setLeft(removeSuccessor(current.getLeft(), dummy));
            return current;
        }
    }

    /**
     * Returns the root of the tree.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
