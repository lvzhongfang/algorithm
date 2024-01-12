package com.tc.algorithm.data.structure;

/**
 * desc red black tree
 *
 * @author lvzf 2023年11月24日
 */
public class RedBlackTree {

    public static final int BLACK = 0;

    public static final int RED = 1;

    private Node root;

    /**
     * Function performing right rotation of the passed node
     *
     *         X           Y
     *        / \         / \
     *       Y   Z  -->  Ya  X
     *      / \             / \
     *     Ya Yb          Yb  Z
     * @param node need right rotation node
     */
    public void rightRotate (Node node) {
        Node l = node.left;
        node.left = l.right;
        if (node.left != null) {
            node.left.parent = node;
        }
        l.parent = node.parent;

        if (node.parent == null) {
            root = l;
        } else if (node == node.parent.left) {
            node.parent.left = l;
        } else {
            node.parent.right = l;
        }

        l.right = node;
        node.parent = l;
    }

    /**
     * Function performing left rotation of the passed node
     *
     *         X                Y
     *        / \              / \
     *       Z   Y   -->      X   Yb
     *          / \          / \
     *         Ya  Yb       Z   Ya
     * @param node need left rotation node
     */
    public void leftRotate (Node node) {
        Node r = node.right;
        node.right = r.left;

        if (node.right != null) {
            node.right.parent = node;
        }
        r.parent = node.parent;

        if (node.parent == null) {
            root = r;
        } else if (node == node.parent.right) {
            node.parent.right = r;
        } else {
            node.parent.left = r;
        }
        r.left = node;
        node.parent = r;
    }

    public void rbInsert (int value) {
        Node z = new Node(value, null, RedBlackTree.RED);
        Node y = null;
        Node x = root;

        while (x != null) {
            y = x;
            if (value < x.data) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;
        if (y == null) {
            root = z;
        } else {
            if (value < y.data) {
                y.left = z;
            } else {
                y.right = z;
            }
        }

        this.rbInsertFixUp(z);
    }

    public void rbInsertFixUp (Node node) {
        while (node.parent.colour == RedBlackTree.RED) {
            //父节点是左子树
            if (node.parent == node.parent.parent.left) {
                //uncle node
                Node y = node.parent.parent.right;
                if (y.colour == RedBlackTree.RED) {
                    node.parent.colour = RedBlackTree.BLACK;
                    y.colour = RedBlackTree.BLACK;
                    node.parent.parent.colour = RedBlackTree.RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        this.leftRotate(node);
                    }

                    node.parent.colour = RedBlackTree.BLACK;
                    node.parent.parent.colour = RedBlackTree.RED;
                    this.rightRotate(node.parent.parent);
                }
            } else {
                //父节点是右子树
                Node y = node.parent.parent.left;
                if (y.colour == RedBlackTree.RED) {
                    node.parent.colour = RedBlackTree.BLACK;
                    y.colour = RedBlackTree.BLACK;
                    node.parent.parent.colour = RedBlackTree.RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        this.rightRotate(node);
                    }

                    node.parent.colour = RedBlackTree.BLACK;
                    node.parent.parent.colour = RedBlackTree.RED;
                    this.leftRotate(node.parent.parent);
                }
            }
        }
        root.colour = RedBlackTree.BLACK;
    }

    public void fixUp (Node root, Node pt) {

    }

    public void add (int value, Node parent, Integer colour) {
        root = this.addRecursive(parent, value, colour);
    }

    private Node addRecursive (Node node, int value, int colour) {
        if (node == null) {
            return new Node(value, null, RedBlackTree.BLACK);
        } else {
            if (node.data > value) {
                node.left = this.addRecursive(node.left, value, colour);
                node.left.parent = node;
            } else {
                node.right = this.addRecursive(node.right, value, colour);
                node.right.parent = node;
            }
        }
        return node;
    }

    public static class Node {
        private Node left;

        private Node right;

        private Node parent;

        private int data;

        private int colour;

        public Node (int value, Node parent, int colour) {
            this.data = value;
            this.right = this.left = null;
            this.parent = parent;
            this.colour = colour;
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

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public int getColour() {
            return colour;
        }

        public void setColour(int colour) {
            this.colour = colour;
        }
    }
}
