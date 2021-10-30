package com.example.demo.NestedTree;


import com.example.demo.business.Business;

public class SubTreeNode {

    private Business business;
    private SubTreeNode left;
    private SubTreeNode right;

    public SubTreeNode(Business business, SubTreeNode left, SubTreeNode right) {
        this.business = business;
        this.left = left;
        this.right = right;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public Business getBusiness() {
        return business;
    }


    public SubTreeNode getLeft() {
        return left;
    }

    public SubTreeNode getRight() {
        return right;
    }


    public void setLeft(SubTreeNode left) {
        this.left = left;
    }

    public void setRight(SubTreeNode right) {
        this.right = right;
    }

}
