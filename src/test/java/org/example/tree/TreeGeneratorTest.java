package org.example.tree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeGeneratorTest {

    @Test
    void generate() {
        Tree<Integer> actualTree = TreeGenerator.of(Arrays.asList(5,10,3,5));

        Node<Integer> node5 = new Node<>(5);
        Node<Integer> node1 = new Node<>(3);
        Node<Integer> node2 = new Node<>(10, node5, null);
        Node<Integer> root = new Node<>(5, node1, node2);
        Tree<Integer> expectedTree = new BinaryTree<>(root);

        assertEquals(expectedTree, actualTree);
    }

    @Test
    void generateEmpty() {
        Tree<Integer> actualTree = TreeGenerator.of(Collections.<Integer>emptyList());

        Tree<Integer> expectedTree = new BinaryTree<>();

        assertEquals(expectedTree, actualTree);
    }

    @Test
    void generateIntoExistent() {
        Tree<Integer> actualTree = new BinaryTree<>(new Node<>(5));
        TreeGenerator.of(actualTree, Arrays.asList(10,3,5));

        Node<Integer> node5 = new Node<>(5);
        Node<Integer> node1 = new Node<>(3);
        Node<Integer> node2 = new Node<>(10, node5, null);
        Node<Integer> root = new Node<>(5, node1, node2);
        Tree<Integer> expectedTree = new BinaryTree<>(root);

        assertEquals(expectedTree, actualTree);
    }
}
