package org.example.tree;

/**
 * Tree interface using for basic implementation of all tree types
 * @param <T> Type of a values contained in tree
 */
public interface Tree<T extends Comparable<T>> {

    /**
     * Add new element to the tree
     * @param node adding node
     */
    void add(Node<T> node);

    /**
     * Get tree node by provided value
     * @param value that should be searched
     * @return tree node or null if value not found
     */
    Node<T> search(T value);

    /**
     * Beautiful printing of the tree to stdout
     */
    void print();
}
