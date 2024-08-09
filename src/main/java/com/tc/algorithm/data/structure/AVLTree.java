package com.tc.algorithm.data.structure;

/**
 * desc 自平衡二叉搜索树
 *
 * @author lvzf
 * @date 2024/8/9
 */
public class AVLTree {

    /**
     * A utility function to get the height of the tree
     * @param node tree node
     * @return height of tree
     */
    public Integer height (AVLNode node) {
        if (node == null) {
            return 0;
        }

        return node.getHeight();
    }

    /**
     * A utility function to right rotate subtree rooted with node
     * Lets node became its left child's right child and lets its left child's right child became its left child
     *   z                                 y
     *  / \     right rotate(z)           / \
     *  y  T1  ----------------->        x   z
     * / \                                  / \
     * x  T2                                T2 T1
     * @param node
     * @return
     */
    public AVLNode rightRotate (AVLNode node) {
        AVLNode left = node.getLeft();
        AVLNode leftRight = left.getRight();

        node.setLeft(leftRight);
        left.setRight(node);

        Integer nodeHeight = 1 + Math.max(this.height(node.getLeft()), this.height(node.getRight()));
        node.setHeight(nodeHeight);

        Integer leftHeight = 1 + Math.max(this.height(left.getLeft()), this.height(left.getRight()));
        left.setHeight(leftHeight);

        return left;
    }

    /**
     * A utility function to left rotate subtree rooted with node
     * Lets node became its right child's left child and lets its right child's left child became its right child
     *          z                            y
     *         / \   left rotate(z)         / \
     *        T1  y  -------------->       z   x
     *           / \                      / \
     *          T2  X                    T1  T2
     * @param node
     * @return
     */
    public AVLNode leftRotate (AVLNode node) {
        AVLNode right = node.getRight();
        AVLNode rightLeft = right.getLeft();

        node.setRight(rightLeft);
        right.setLeft(node);

        Integer nodeHeight = 1 + Math.max(this.height(node.getLeft()), this.height(node.getRight()));
        node.setHeight(nodeHeight);

        Integer rightHeight = 1 + Math.max(this.height(right.getLeft()), this.height(right.getRight()));
        right.setHeight(rightHeight);

        return right;
    }

    public Integer getBalance (AVLNode node) {
        if (node == null) {
            return 0;
        }

        return this.height(node.getLeft()) - this.height(node.getRight());
    }

    public AVLNode insert (AVLNode node, Integer key) {

        // Perform the normal BST insertion
        if (node == null) {
            return new AVLNode(key);
        }

        if (key < node.getKey()) {
            node.setLeft(insert(node.getLeft(), key));
        } else if (key > node.getKey()) {
            node.setRight(insert(node.getRight(), key));
        } else { //Equal keys are not allowed in BST
            return node;
        }

        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));

        int balance = getBalance(node);

        /**
         * If this node becomes unbalanced,
         * then there are 4 cases
         * y is the left child of z and x is the left child of y(Left Left case)
         * y is the left child of z and x is the right child of y(Left Right case)
         * y is the right child of z and x is the right child of y(Right Right case)
         * y is the right child of z and x is the left child of y(Right Left case)
         */

        // Left Left Case
        if (balance > 1 && key < node.getLeft().getKey())
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && key > node.getRight().getKey())
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && key > node.getLeft().getKey()) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.getRight().getKey()) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        return node;
    }

    public void preOrder (AVLNode root) {
        if (root != null) {
            System.out.print(root.getKey() + " ");
            preOrder(root.getLeft());
            preOrder(root.getRight());
        }
    }
    public static void main(String[] args) {
        AVLTree avl = new AVLTree();

        AVLNode root = null;

        root = avl.insert(root, 10);
        root = avl.insert(root, 20);
        root = avl.insert(root, 30);
        root = avl.insert(root, 40);
        root = avl.insert(root, 50);
        root = avl.insert(root, 25);

        System.out.println("Preorder traversal : ");
        avl.preOrder(root);
    }

}
