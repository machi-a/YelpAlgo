package com.example.demo.treemap;

import com.example.demo.business.Business;

public class Treenodex {
    private Business business;
    private Treenodex left;
    private Treenodex right;

    public Treenodex(Business business, Treenodex left, Treenodex right) {
        this.business = business;
        this.left = left;
        this.right = right;
    }

    public Business getBusiness() {
        return business;
    }

    public Treenodex getLeft() {
        return left;
    }

    public Treenodex getRight() {
        return right;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public void setRight(Treenodex right) {
        this.right = right;
    }

    public void setLeft(Treenodex left) {
        this.left = left;
    }
}
