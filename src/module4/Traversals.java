package module4;

import module4.TreeNode;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Your implementation of the pre-order, in-order, and post-order
 * traversals of a tree.
 */
public class Traversals<T extends Comparable<? super T>> {

    /**
     * DO NOT ADD ANY GLOBAL VARIABLES!
     */

    /**
     * Given the root of a binary search tree, generate a
     * pre-order traversal of the tree. The original tree
     * should not be modified in any way.
     * <p>
     * This must be done recursively.
     * <p>
     * Must be O(n).
     *
     * @param root The root of a BST.
     * @return List containing the pre-order traversal of the tree.
     */
    public List<T> preorder(TreeNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        List<T> preorderTree = new ArrayList<>();
        if (root != null) {
            preorderTree.add(root.getData());
            preorderTree.addAll(preorder(root.getLeft()));
            preorderTree.addAll(preorder(root.getRight()));
        }
        return preorderTree;
    }

    /**
     * Given the root of a binary search tree, generate an
     * in-order traversal of the tree. The original tree
     * should not be modified in any way.
     * <p>
     * This must be done recursively.
     * <p>
     * Must be O(n).
     *
     * @param root The root of a BST.
     * @return List containing the in-order traversal of the tree.
     */
    public List<T> inorder(TreeNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        List<T> inorderTree = new ArrayList<>();
        if (root != null) {
            inorderTree.addAll(inorder(root.getLeft()));
            inorderTree.add(root.getData());
            inorderTree.addAll(inorder(root.getRight()));
        }
        return inorderTree;
    }

    /**
     * Given the root of a binary search tree, generate a
     * post-order traversal of the tree. The original tree
     * should not be modified in any way.
     * <p>
     * This must be done recursively.
     * <p>
     * Must be O(n).
     *
     * @param root The root of a BST.
     * @return List containing the post-order traversal of the tree.
     */
    public List<T> postorder(TreeNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        List<T> postorderTree = new ArrayList<>();
        if (root != null) {
            postorderTree.addAll(postorder(root.getLeft()));
            postorderTree.addAll(postorder(root.getRight()));
            postorderTree.add(root.getData());
        }
        return postorderTree;
    }
}
