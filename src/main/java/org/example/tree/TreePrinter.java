package org.example.tree;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TreePrinter<T extends Comparable<T>> {
    
    void print(Node<T> root) {
        List<List<CoordinateNode<T>>> nodesByHeight = fillNodesByHeight(root);
        for (int height = nodesByHeight.size() - 2; height >= 0; height--) {
            List<CoordinateNode<T>> nodesForHeight = nodesByHeight.get(height);
            for (CoordinateNode<T> node : nodesForHeight) {
                shiftCoordinates(node);
            }
        }
        int leftest = nodesByHeight.stream()
                .map(l -> l.stream().map(n -> n.x).min(Integer::compareTo).orElse(Integer.MAX_VALUE))
                .min(Integer::compareTo).orElse(Integer.MAX_VALUE);
        int lowest = nodesByHeight.stream()
                .map(l -> l.stream().map(n -> n.y).min(Integer::compareTo).orElse(Integer.MAX_VALUE))
                .min(Integer::compareTo).orElse(Integer.MAX_VALUE);
        nodesByHeight.forEach(l -> l.forEach(n -> n.x += Math.abs(leftest)));
        nodesByHeight.forEach(l -> l.forEach(n -> n.y += Math.abs(lowest)));
        int rightest = nodesByHeight.stream()
                .map(l -> l.stream()
                        .filter(n -> n.value != null)
                        .map(CoordinateNode::getRightBound).max(Integer::compareTo).orElse(Integer.MIN_VALUE))
                .max(Integer::compareTo).orElse(Integer.MIN_VALUE);
        int highest = nodesByHeight.stream()
                .map(l -> l.stream().map(n -> n.y).max(Integer::compareTo).orElse(Integer.MIN_VALUE))
                .max(Integer::compareTo).orElse(Integer.MIN_VALUE);

        char[][] coordinates = new char[highest + 1][];
        for (int i = 0; i <= highest; i++) {
            coordinates[i] = new char[rightest + 1];
            Arrays.fill(coordinates[i], ' ');
        }

        for (CoordinateNode<T> node :
                nodesByHeight.stream().flatMap(Collection::stream).filter(n -> n.value != null).collect(Collectors.toList())) {
            int charNum = 0;
            for (int i = node.x; i <= node.getRightBound(); i++) {
                coordinates[node.y][i] = node.value.toString().charAt(charNum++);
            }
        }

        for (int i = highest; i >= 0; i--) {
            System.out.println();
            for (int j = 0; j <= rightest; j++) {
                System.out.print(coordinates[i][j]);
            }
        }
    }

    private List<List<CoordinateNode<T>>> fillNodesByHeight(Node<T> root) {
        List<List<CoordinateNode<T>>> nodesByHeight = new ArrayList<>();
        fillNodesByHeightRec(nodesByHeight, 0, new CoordinateNode<T>(root));
        return nodesByHeight;
    }

    private void fillNodesByHeightRec(List<List<CoordinateNode<T>>> nodesByHeight, Integer height, CoordinateNode<T> current) {
        if (current == null) return;
        if (nodesByHeight.size() - 1 < height) {
            List<CoordinateNode<T>> nodesForHeight = new ArrayList<>();
            nodesForHeight.add(current);
            nodesByHeight.add(nodesForHeight);
        } else {
            List<CoordinateNode<T>> nodesForHeight = nodesByHeight.get(height);
            nodesForHeight.add(current);
        }
        fillNodesByHeightRec(nodesByHeight, height+1, current.left);
        fillNodesByHeightRec(nodesByHeight, height+1, current.right);
    }

    private int findLeftest(CoordinateNode<T> node) {
        Int leftest = new Int(0);
        findLeftestRec(node, leftest);
        return leftest.val;
    }

    private void findLeftestRec(CoordinateNode<T> current, Int leftest) {
        if (current == null || current.value == null) return;
        findLeftestRec(current.left, leftest);
        if (current.x < leftest.val) {
            leftest.val = current.x;
        }
        findLeftestRec(current.right, leftest);
    }

    private int findRightest(CoordinateNode<T> node) {
        Int rightest = new Int(0);
        findRightestRec(node, rightest);
        return rightest.val;
    }

    private void findRightestRec(CoordinateNode<T> current, Int rightest) {
        if (current == null || current.value == null) return;
        findRightestRec(current.left, rightest);
        if (current.getRightBound() > rightest.val) {
            rightest.val = current.getRightBound();
        }
        findRightestRec(current.right, rightest);
    }

    private void shiftCoordinates(CoordinateNode<T> node) {
        if (node.left != null && node.left.value != null) {
            int rightest = findRightest(node.left);
            int shift = -Math.abs(rightest) - 1;
            shiftCoordinates(node.left, shift);
        }
        if (node.right != null && node.right.value != null) {
            int leftest = findLeftest(node.right);
            int shift = node.value.toString().length() + Math.abs(leftest);
            shiftCoordinates(node.right, shift);
        }
    }

    private void shiftCoordinates(CoordinateNode<T> node, int shift) {
        if (node == null) return;
        shiftCoordinates(node.left, shift);
        node.x += shift;
        node.y -= 2;
        shiftCoordinates(node.right, shift);
    }

    @AllArgsConstructor
    private static class CoordinateNode<T> {
        private T value;
        private CoordinateNode<T> left;
        private CoordinateNode<T> right;
        private int x;
        private int y;

        CoordinateNode(Node<T> node) {
            if (node == null) return;
            this.value = node.getValue();
            this.left = new CoordinateNode<>(node.getLeft());
            this.right = new CoordinateNode<>(node.getRight());
            this.x = 0;
            this.y = 0;
        }

        int getRightBound() {
            return x + value.toString().length() - 1;
        }
    }

    @AllArgsConstructor
    private static class Int {
        int val;
    }
}
