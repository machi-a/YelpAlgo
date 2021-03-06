package com.example.demo.avltree;

import com.example.demo.business.Business;

import java.util.ArrayList;
import java.io.Serializable;

public class StringNode implements Serializable {
    private static final long serialVersionUID = 1L;
    String element;
    ArrayList<Business> bizList;
    int h;  //for height
    StringNode leftChild;
    StringNode rightChild;

    //default constructor to create null node
    public StringNode()
    {
        leftChild = null;
        rightChild = null;
        element = null;
        ArrayList<Business> bizList = null;
        h = 0;
    }
    // parameterized constructor
    public StringNode(String element, ArrayList<Business> bizList)
    {
        leftChild = null;
        rightChild = null;
        this.element = element;
        this.bizList = bizList;
        h = 0;
    }



}
