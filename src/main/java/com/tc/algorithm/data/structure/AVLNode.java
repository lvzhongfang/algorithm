package com.tc.algorithm.data.structure;

/**
 * desc TODO
 *
 * @author lvzf
 * @date 2024/8/9
 */
public class AVLNode extends Node {

    private AVLNode left;

    private AVLNode right;

    public AVLNode (Integer key) {
        super(key, 1);
        this.left = null;
        this.right = null;
    }

    public AVLNode getLeft() {
        return left;
    }

    public void setLeft(AVLNode left) {
        this.left = left;
    }

    public AVLNode getRight() {
        return right;
    }

    public void setRight(AVLNode right) {
        this.right = right;
    }

    public static void main(String[] args) {
        AVLNode node = new AVLNode(1);
        System.out.println("Height is : " + node.getHeight());
    }
}
