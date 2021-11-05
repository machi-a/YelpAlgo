package com.example.demo.avltree;

import com.example.demo.business.Business;

import java.util.ArrayList;
import java.io.Serializable;

public class IntNode  implements Serializable {
    private static final long serialVersionUID = 1L;
    int element;
    ArrayList<Business> bizList;
    int h;  //for height
    IntNode leftChild;
    IntNode rightChild;

    //default constructor to create null node
    public IntNode()
    {
        leftChild = null;
        rightChild = null;
        element = 0;
        bizList = null;
        h = 0;
    }
    // parameterized constructor
    public IntNode(int element, ArrayList<Business> bizList)
    {
        leftChild = null;
        rightChild = null;
        this.element = element;
        this.bizList = bizList;
        h = 0;
    }
}
