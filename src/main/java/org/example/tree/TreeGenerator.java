package org.example.tree;

import java.util.List;

/**
 * Helper class for trees creations
 */
public class TreeGenerator {

    private TreeGenerator() {};

    /**
     * Add elements to provided tree
     * @param tree where elements will be added
     * @param elements for adding
     * @param <T> type of tree values
     */
    static <T extends Comparable<T>> void of(Tree<T> tree, List<T> elements) {
        for (T element : elements) {
            tree.add(new Node<>(element));
        }
    }

    /**
     * Create {@link BinaryTree} usual binary tree with provided elements
     * @param elements for adding
     * @param <T> type of tree values
     * @return new binary tree
     */
    static <T extends Comparable<T>> Tree<T> of(List<T> elements) {
        // TODO: change to balanced tree implementation
        Tree<T> tree = new BinaryTree<>();
        of(tree, elements);
        return tree;
    }
}
