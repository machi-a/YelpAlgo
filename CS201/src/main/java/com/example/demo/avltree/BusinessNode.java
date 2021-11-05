package com.example.demo.avltree;
import com.example.demo.business.Business;
import java.io.Serializable;

class BusinessNode implements Serializable 
{
    private static final long serialVersionUID = 1L;
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