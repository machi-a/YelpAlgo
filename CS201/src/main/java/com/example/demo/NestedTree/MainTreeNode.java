package com.example.demo.nestedtree;

public class MainTreeNode {
    private String value;
    private SubTreeNode root;
    private MainTreeNode left;
    private MainTreeNode right;
    private int height;


    public MainTreeNode(String value, SubTreeNode root, MainTreeNode left, MainTreeNode right) {
        this.value = value;
        this.root = root;
        this.left = left;
        this.right = right;

    }

//
//    public double getValue() {

    public int getHeight() {
        return height;
    }
//        return value;
//    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
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

//    public void setValue(double value) {
//        this.value = value;
//    }

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
