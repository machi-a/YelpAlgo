package com.example.demo.NestedTree;



public class SubTreeNode {

    private double value;
    private SubTreeNode left;
    private SubTreeNode right;
    private SubTreeNode parent;

    public SubTreeNode(double value, SubTreeNode left, SubTreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }



    public double getValue() {
        return value;
    }

    public SubTreeNode getLeft() {
        return left;
    }

    public SubTreeNode getRight() {
        return right;
    }

    public SubTreeNode getParent() {
        return parent;
    }


    public void setValue(double value) {
        this.value = value;
    }

    public void setLeft(SubTreeNode left) {
        this.left = left;
    }

    public void setRight(SubTreeNode right) {
        this.right = right;
    }

    public void setParent(SubTreeNode parent) {
        this.parent = parent;
    }
}
