package com.example.demo.avltree;
import com.example.demo.business.Business;

class BusinessNode
{
    Business element;
    int h;  //for height
    BusinessNode leftChild;
    BusinessNode rightChild;

    //default constructor to create null node
    public BusinessNode()
    {
        leftChild = null;
        rightChild = null;
        element = null;
        h = 0;
    }
    // parameterized constructor
    public BusinessNode(Business element)
    {
        leftChild = null;
        rightChild = null;
        this.element = element;
        h = 0;
    }
}