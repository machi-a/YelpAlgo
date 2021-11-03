package com.example.demo.avltree;

public class FloatNode {

    float element;
    int h;  //for height
    FloatNode leftChild;
    FloatNode rightChild;

    //default constructor to create null node
    public FloatNode()
    {
        leftChild = null;
        rightChild = null;
        element = 0;
        h = 0;
    }
    // parameterized constructor
    public FloatNode(float element)
    {
        leftChild = null;
        rightChild = null;
        this.element = element;
        h = 0;
    }

}
