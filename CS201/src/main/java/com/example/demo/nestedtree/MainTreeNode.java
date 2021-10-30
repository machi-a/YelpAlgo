package com.example.demo.NestedTree;

public class MainTreeNode {
    private double value;
    private SubTreeNode root;
    private MainTreeNode left;
    private MainTreeNode right;


    public MainTreeNode(double value, SubTreeNode root, MainTreeNode left, MainTreeNode right) {
        this.value = value;
        this.root = root;
        this.left = left;
        this.right = right;

    }


    public double getValue() {
        return value;
    }

    public SubTreeNode getRoot() {
        return root;
    }

    public MainTreeNode getLeft() {
        return left;
    }

    public MainTreeNode getRight() {
        return right;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setRoot(SubTreeNode root) {
        this.root = root;
    }

    public void setRight(MainTreeNode right) {
        this.right = right;
    }

    public void setLeft(MainTreeNode left) {
        this.left = left;
    }
}
