package com.example.demo.avltree;

import com.example.demo.business.Business;

import java.util.ArrayList;
import java.io.Serializable;

public class FloatNode  implements Serializable {
    private static final long serialVersionUID = 1L;
    float element;
    ArrayList<Business> bizList;
    int h;  //for height
    FloatNode leftChild;
    FloatNode rightChild;

    //default constructor to create null node
    public FloatNode()
    {
        leftChild = null;
        rightChild = null;
        element = 0;
        bizList = null;
        h = 0;
    }
    // parameterized constructor
    public FloatNode(float element, ArrayList<Business> bizList)
    {
        leftChild = null;
        rightChild = null;
        this.element = element;
        this.bizList = bizList;
        h = 0;
    }

}
