package org.example.tree;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Standard binary tree node without parent's info
 */

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
class Node<T> {
    private T value;
    private Node<T> left;
    private Node<T> right;

    Node(T value) {
        this.value = value;
    }
}
