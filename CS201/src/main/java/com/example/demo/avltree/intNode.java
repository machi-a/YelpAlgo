package com.example.demo.avltree;

import com.example.demo.business.Business;

public class intNode {

    int element;
    int h;  //for height
    BusinessNode leftChild;
    BusinessNode rightChild;

    //default constructor to create null node
    public intNode()
    {
        leftChild = null;
        rightChild = null;
        element = 0;
        h = 0;
    }
    // parameterized constructor
    public intNode(int element)
    {
        leftChild = null;
        rightChild = null;
        this.element = element;
        h = 0;
    }
}
