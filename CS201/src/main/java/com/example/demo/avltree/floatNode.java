package com.example.demo.avltree;

public class floatNode {

    float element;
    int h;  //for height
    BusinessNode leftChild;
    BusinessNode rightChild;

    //default constructor to create null node
    public floatNode()
    {
        leftChild = null;
        rightChild = null;
        element = 0;
        h = 0;
    }
    // parameterized constructor
    public floatNode(int element)
    {
        leftChild = null;
        rightChild = null;
        this.element = element;
        h = 0;
    }

}
