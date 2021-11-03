package com.example.demo.avltree;

import com.example.demo.business.Business;

public class IntNode {

    int element;
    int h;  //for height
    IntNode leftChild;
    IntNode rightChild;

    //default constructor to create null node
    public IntNode()
    {
        leftChild = null;
        rightChild = null;
        element = 0;
        h = 0;
    }
    // parameterized constructor
    public IntNode(int element)
    {
        leftChild = null;
        rightChild = null;
        this.element = element;
        h = 0;
    }
}
