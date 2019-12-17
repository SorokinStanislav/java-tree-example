package org.example.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BinaryTreeTest {

    @Test
    void addInEmptyTree() {
        BinaryTree<Integer> tree = new BinaryTree<>();

        tree.add(new Node<>(5));

        assertEquals(new Integer(5), tree.getRoot().getValue());
        assertNull(tree.getRoot().getLeft());
        assertNull(tree.getRoot().getRight());
    }

    @Test
    void addInNonEmptyTree() {
        Node<Integer> node1 = new Node<>(3);
        Node<Integer> node2 = new Node<>(10);
        Node<Integer> root = new Node<>(5, node1, node2);
        BinaryTree<Integer> tree = new BinaryTree<>(root);

        tree.add(new Node<>(7));

        assertEquals(new Integer(7), tree.getRoot().getRight().getLeft().getValue());
        assertNull(tree.getRoot().getRight().getLeft().getLeft());
        assertNull(tree.getRoot().getRight().getLeft().getRight());
    }

    @Test
    void addInNonEmptyEqualValueTree() {
        Node<Integer> node1 = new Node<>(3);
        Node<Integer> node2 = new Node<>(10);
        Node<Integer> root = new Node<>(5, node1, node2);
        BinaryTree<Integer> tree = new BinaryTree<>(root);

        tree.add(new Node<>(5));

        assertEquals(new Integer(5), tree.getRoot().getRight().getLeft().getValue());
        assertNull(tree.getRoot().getRight().getLeft().getLeft());
        assertNull(tree.getRoot().getRight().getLeft().getRight());
    }

    @Test
    void searchInEmptyTree() {
        BinaryTree<Integer> tree = new BinaryTree<>();

        Integer element = 5;
        Node<Integer> result = tree.search(element);

        assertNull(result);
    }

    @Test
    void searchInNonEmptyTree() {
        Node<Integer> node1 = new Node<>(3);
        Node<Integer> node2 = new Node<>(10);
        Node<Integer> root = new Node<>(5, node1, node2);
        BinaryTree<Integer> tree = new BinaryTree<>(root);

        Integer element = 10;
        Node<Integer> result = tree.search(element);
        Node<Integer> expected = new Node<>(10);
        assertEquals(expected, result);
    }

    @Test
    void searchInNonEmptyTreeWithNoResult() {
        Node<Integer> node1 = new Node<>(3);
        Node<Integer> node2 = new Node<>(10);
        Node<Integer> root = new Node<>(5, node1, node2);
        BinaryTree<Integer> tree = new BinaryTree<>(root);

        Integer element = 11;
        Node<Integer> result = tree.search(element);
        assertNull(result);
    }

    @Test
    void searchInNonEmptyTreeWithEquals() {
        Node<Integer> node5 = new Node<>(5);
        Node<Integer> node1 = new Node<>(3);
        Node<Integer> node2 = new Node<>(10, node5, null);
        Node<Integer> root = new Node<>(5, node1, node2);
        BinaryTree<Integer> tree = new BinaryTree<>(root);

        Integer element = 5;
        Node<Integer> result = tree.search(element);
        assertEquals(root, result);
    }
}
