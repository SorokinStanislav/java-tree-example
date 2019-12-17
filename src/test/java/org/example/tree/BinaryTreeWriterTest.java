package org.example.tree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class BinaryTreeWriterTest {

    @Test
    void printTree() {
        Node<Integer> node7 = new Node<>(13);
        Node<Integer> node8 = new Node<>(17);
        Node<Integer> node9 = new Node<>(155);
        Node<Integer> node10 = new Node<>(303);
        Node<Integer> node3 = new Node<>(3, null, node7);
        Node<Integer> node4 = new Node<>(91, node8, null);
        Node<Integer> node5 = new Node<>(171, node9, null);
        Node<Integer> node6 = new Node<>(205, node10, null);
        Node<Integer> node1 = new Node<>(15, node3, node4);
        Node<Integer> node2 = new Node<>(190, node5, node6);
        Node<Integer> root = new Node<>(100, node1, node2);

        Tree<Integer> tree = new BinaryTree<>(root);

        tree.print();
    }

    @Test
    void printLinkedListTree() {
        Tree<Integer> tree = TreeGenerator.of(Arrays.asList(100, 90, 80, 70, 60, 50, 40, 30, 20, 10, 0));

        tree.print();
    }

    @Test
    void printEmptyTree() {
        Tree<Integer> tree = new BinaryTree<>();

        tree.print();
    }
}
