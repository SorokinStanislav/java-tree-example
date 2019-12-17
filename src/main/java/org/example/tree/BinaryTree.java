package org.example.tree;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Implementation of {@link Tree} interface with usual binary tree
 * @param <T>
 */
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BinaryTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    @Override
    public void add(Node<T> node) {
        if (isEmpty()) {
            root = node;
            return;
        }
        Node<T> current = root;
        while (true) {
            if (node.getValue().compareTo(current.getValue()) >= 0) {
                if (current.getRight() == null) {
                    current.setRight(node);
                    return;
                } else {
                    current = current.getRight();
                }
            } else {
                if (current.getLeft() == null) {
                    current.setLeft(node);
                    return;
                } else {
                    current = current.getLeft();
                }
            }
        }
    }

    @Override
    public Node<T> search(T value) {
        if (isEmpty()) {
            return null;
        }
        Node<T> current = root;
        while (current != null) {
            if (value.compareTo(current.getValue()) == 0) {
                return current;
            }
            if (value.compareTo(current.getValue()) >= 0) {
                current = current.getRight();
            } else {
                current = current.getLeft();
            }
        }
        return null;
    }

    @Override
    public void print() {
        if (isEmpty()) {
            System.out.println("Tree is empty");
            return;
        }
        TreePrinter<T> tTreePrinter = new TreePrinter<>();
        tTreePrinter.print(root);
    }

    private boolean isEmpty() {
        return root == null;
    }
}
