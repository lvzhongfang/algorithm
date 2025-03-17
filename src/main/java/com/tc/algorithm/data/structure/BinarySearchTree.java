package com.tc.algorithm.data.structure;

/**
 * desc Binary Search Tree
 * Property:
 * Let x be a node in a binary search tree. If y is a node in the left subtree of x,
 * then y.key <= x.key. If y is a node in the right subtree of x, then y.key >= x.key.
 * @author lvzf
 * @date 2025/3/17
 */
public class BinarySearchTree<T> {

    private Node<T> root;

    public void inorderTreeWalk (Node<T> root) {
        if (!isNullNode(root)) {
            inorderTreeWalk(root.left);
            System.out.println("key is " + root.key + ", data is " + root.data);
            inorderTreeWalk(root.right);
        }
    }

    public Node<T> treeSearch (Node<T> root, Integer key) {
        if (isNullNode(root) || key.equals(root.getKey())) {
            return root;
        }

        if (key < root.getLeft().getKey()) {
            return treeSearch(root.getLeft(), key);
        } else {
            return treeSearch(root.getRight(), key);
        }
    }

    public Node<T> iterativeTreeSearch (Node<T> root, Integer key) {
        if (isNullNode(root) || key.equals(root.getKey())) {
            return root;
        }

        while (!isNullNode(root) && !key.equals(root.getKey())) {
            if (key < root.getKey()) {
                root = root.getLeft();
            } else {
                root = root.getRight();
            }
        }
        return root;
    }

    public Node<T> treeMinimum (Node<T> root) {
        while (!isNullNode(root.getLeft())) {
            root = root.getLeft();
        }

        return root;
    }

    public Node<T> treeMaximum (Node<T> root) {
        while (!isNullNode(root.getRight())) {
            root = root.getRight();
        }
        return root;
    }

    /**
     * Given a node in a binary search tree, sometimes we need to find its successor
     * in the sorted order determined by an inorder tree walk. If all keys are distinct,
     * the successor of a node x is the node with the smallest key greater than x.key.
     * @param node
     * @return
     */
    public Node<T> treeSuccessor (Node<T> node) {
        if (!isNullNode(node.getRight())) {
            return this.treeMinimum(node.getRight());
        }

        Node<T> y = node.getParent();
        while (!isNullNode(y) && y.getRight() == node) {
            node = y;
            y = y.getParent();
        }

        return y;
    }

    public Node<T> treePredecessor (Node<T> node) {
        if (!isNullNode(node.getLeft())) {
            return this.treeMaximum(node.getLeft());
        }

        Node<T> y = node.getParent();
        while (!isNullNode(y) && node == y.getLeft()) {
            node = y;
            y = y.getParent();
        }

        return y;
    }

    public void treeInsert (BinarySearchTree<T> t, Node<T> key) {
        if (isNullNode(root) || root == null) {
            root = key;
        } else {
            Node<T> y = null;
            Node<T> x = t.root;

            while (!isNullNode(x)) {
                y = x;
                if (key.getKey() < x.getKey()) {
                    x = x.getLeft();
                } else {
                    x = x.getRight();
                }
            }

            key.setParent(y);
            if (isNullNode(y)) {
                t.root = key;
            } else if (key.getKey() < y.getKey()) {
                y.setLeft(key);
            } else {
                y.setRight(key);
            }
        }
    }

    /**
     * The overall strategy for deleting a node z from a binary search tree T has three basic cases but,
     * as we shall see, one of the cases is a bit tricky.
     * 1. If z has no children, then we simply remove it by modifying its parent to replace z with nil as is child.
     * 2. If z has just one child, then we elevate that child to take z's position in the tree by modifying z's parent
     * to replace z by z's child.
     * 3. If z has two children, then we find z's successor y -- which must be in z's right subtree -- and have y take z's position
     * in the tree.
     *    q                      q
     *    |                      |
     *    z       -->            r
     *   / \
     * nil  r
     *
     *      q                   q
     *      |                   |
     *      z         -->       l
     *     / \
     *    l   nil
     *
     *              q                    q
     *              |                    |
     *              z                    y
     *             / \         -->      / \
     *            l   y                l   x
     *               / \
     *              nil x
     *
     *             q                          q         y                            q
     *             |                          |        / \                           |
     *             z                          z      nil  r                          y
     *            / \                        /           /                          / \
     *           l   r      -->              l          x           -->            l   r
     *              /                                                                 /
     *             y                                                                 x
     *            / \
     *           nil x
     *  1. 被删除节点没有左子树，用其右孩子代替删除节点
     *  2. 被删除节点有一个左孩子但是没有右孩子，用左孩子代替被删除节点
     *  3. 被删除节点有两个孩子l与y，y是右孩子且为后继节点，y的右孩子是x，用y替换z（被删除节点），使l成为y的左孩子，x仍为y的右孩子
     *  4. 节点z有两个孩子（左孩子l和右孩子r），并且z的后继y != r 位于以r为根的子树中。用y自己的右孩子x代替y，并置y为r的双亲。然后再
     *  置y为q的孩子和l的双亲
     * @param t
     * @param node
     */
    public void treeDelete (BinarySearchTree<T> t, Node<T> node) {
        if (isNullNode(node.left)) {
            transplant(t, node, node.right);
        } else if (isNullNode(node.right)) {
            transplant(t, node, node.left);
        } else {
            Node<T> y = treeMinimum(node.right);

            if (y.parent != node) {
                transplant(t, y, y.right);
                y.right = node.right;
                y.right.parent = y;
            }

            transplant(t, node, y);
            y.left = node.left;
            y.left.parent = y;
        }
    }

    public void transplant (BinarySearchTree<T> t, Node<T> u, Node<T> v) {
        if (isNullNode(u.parent)) {
            t.root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }

        if (!isNullNode(v)) {
            v.parent = u.parent;
        }
    }

    private boolean isNullNode (Node<T> t) {
        if (t == null) {
            return true;
        }

        return t.getKey() == null;
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    public static class Node<T> {
        private Integer key;

        private T data;

        private Node<T> left;

        private Node<T> right;

        private Node<T> parent = null;

        public Node (Integer key) {
            this.key = key;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getLeft() {
            return left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }

        public Node<T> getParent() {
            return parent;
        }

        public void setParent(Node<T> parent) {
            this.parent = parent;
        }
    }

    public static void main(String[] args) {
        int [] array = {4, 6, 3, 1, 2, 9, 7, 8};

        BinarySearchTree<String> bst = new BinarySearchTree<>();
        Node<String> root = new Node<>(5);
        root.setData("node 5");
        bst.setRoot(root);

        for (int i = 0; i < array.length; i++) {
            Node<String> node = new Node<>(array[i]);
            node.setData("node " + array[i]);
            bst.treeInsert(bst, node);
        }

        bst.inorderTreeWalk(bst.root);
    }
}
