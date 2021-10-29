package com.example.demo;

public class TreeNode {

    private double value;
    private TreeNode refToAnotherTree;
    private TreeNode left;

    public TreeNode(double value, TreeNode refToAnotherTree, TreeNode left, TreeNode right) {
        this.value = value;
        this.refToAnotherTree = refToAnotherTree;
        this.left = left;
        this.right = right;

    }

    private TreeNode right;
    private TreeNode parent;


    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setRefToAnotherTree(TreeNode refToAnotherTree) {
        this.refToAnotherTree = refToAnotherTree;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getRefToAnotherTree() {
        return refToAnotherTree;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public TreeNode getParent() {
        return parent;
    }


}
