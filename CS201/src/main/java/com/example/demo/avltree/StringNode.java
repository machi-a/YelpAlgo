package com.example.demo.avltree;

public class StringNode {

    String element;
    int h;  //for height
    StringNode leftChild;
    StringNode rightChild;

    //default constructor to create null node
    public StringNode()
    {
        leftChild = null;
        rightChild = null;
        element = null;
        h = 0;
    }
    // parameterized constructor
    public StringNode(String element)
    {
        leftChild = null;
        rightChild = null;
        this.element = element;
        h = 0;
    }



}
