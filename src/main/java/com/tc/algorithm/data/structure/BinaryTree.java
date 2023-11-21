package com.tc.algorithm.data.structure;

import javafx.scene.chart.ValueAxis;

import java.util.LinkedList;
import java.util.Queue;

/**
 * desc binary tree
 *
 * @author lvzf 2023年11月21日
 */
public class BinaryTree {

    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void add (int value) {
        root = this.addRecursive(root, value);
    }
    private Node addRecursive (Node node, int value) {
        if (node == null) {
            return new Node(value);
        } else {
            if (node.getData() < value) {
                node.right = addRecursive(node.getRight(), value);
            } else if (node.getData() > value) {
                node.left = addRecursive(node.getLeft(), value);
            } else {
                //value already exists
            }
        }
        return node;
    }

    public boolean conatinNode (int value) {
        return containNodeRecursive(root, value);
    }

    private boolean containNodeRecursive (Node current, int value) {
        if (current == null) {
            return false;
        }
        if (current.data == value) {
            return true;
        }
        return value < current.data ? containNodeRecursive(current.getLeft(), value) : containNodeRecursive(current.getRight(), value);
    }

    public void delete (int value) {
        root = deleteRecursive(root, value);
    }

    private int findSmallestValue (Node root) {
        return root.left == null ? root.data : findSmallestValue(root.left);
    }

    private Node deleteRecursive (Node current, int value) {
        if (current == null) {
            return null;
        }

        if (current.data == value) {
            // a node has no children - this is simplest case; we just need to replace this node with null in its parent node
            // a node has exactly one child - in the parent node, we replace this node with its only child.
            // a node has two children - this is the most complex case because it requires a tree reorganization
            if (current.left == null && current.right == null) {
                return null;
            }
            // has on child
            if (current.left == null) {
                return current.right;
            }

            if (current.right == null) {
                return current.left;
            }
            // has two children
            int smallestValue = this.findSmallestValue(current.right);
            current.data = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }

        if (value < current.data) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }

        current.right = deleteRecursive(current.right, value);
        return current;
    }

    /**
     * Depth-First Search in order
     * @param node
     */
    public void traverseInOrder (Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(" " + node.data);
            traverseInOrder(node.right);
        }
    }

    public void traversePreOrder (Node node) {
        if (node != null) {
            System.out.print(" " + node.data);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    public void traversePostOrder (Node node) {
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            System.out.print(" " + node.data);
        }
    }

    /**
     * Breadth-First Search
     * This is another common type of traversal that visits all the nodes of a level before going to the next level
     */
    public void traverseLevelOrder () {
        if (root == null) {
            return;
        }

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            Node node = nodes.remove();
            System.out.print(" " + node.data);

            if (node.left != null) {
                nodes.add(node.left);
            }

            if (node.right != null) {
                nodes.add(node.right);
            }
        }
    }

    public static class Node {
        int data;

        private Node left;

        private Node right;

        public Node (int value) {
            this.data = value;
            this.right = this.left = null;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();

        /*bt.add(6);
        bt.add(4);
        bt.add(8);
        bt.add(3);
        bt.add(5);
        bt.add(7);
        bt.add(9);*/

        bt.add(9);
        bt.add(7);
        bt.add(5);
        bt.add(8);
        bt.add(6);
        bt.add(11);
        bt.add(10);
        bt.add(12);
        bt.traverseLevelOrder();
        System.out.println();
        bt.traverseInOrder(bt.root);
        System.out.println();
        bt.delete(7);
        bt.traverseInOrder(bt.root);
        System.out.println();
        bt.traversePreOrder(bt.root);
        System.out.println();
        bt.traversePostOrder(bt.root);
        System.out.println();
        bt.traverseLevelOrder();
        System.out.println();
    }
}
