package com.example.demo.avltree;

import com.example.demo.business.Business;
import com.example.demo.reader.ReadToArray;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.io.Serializable;

class AVLStorage implements Serializable  {
    private static final long serialVersionUID = 1L;
    public MainAVLTree createMainAVLTree(ArrayList<Business> listOfBiz){

        MainAVLTree returnAVLTree = new MainAVLTree();
        Iterator<Business> bizIterator = listOfBiz.iterator();

        for (Business temp : listOfBiz){
            returnAVLTree.insertElement(temp);
        }
        return returnAVLTree;
    }

    public IntAVLTree createReviewCountAVLTree(ArrayList<Business> listOfBiz){

        IntAVLTree returnAVLTree = new IntAVLTree();

        for (Business temp : listOfBiz) {
            if (!returnAVLTree.searchElement(temp.getReviewCount())) {
                ArrayList<Business> tempList = new ArrayList<Business>();
                returnAVLTree.insertElement(temp.getReviewCount(), tempList);
            }
            returnAVLTree.getElement((temp.getReviewCount())).bizList.add(temp);
        }
        return returnAVLTree;
    }

    public FloatAVLTree createStarAVLTree(ArrayList<Business> listOfBiz){

        FloatAVLTree returnAVLTree = new FloatAVLTree();

        for (Business temp : listOfBiz) {
//            System.out.println(temp.getStars());
//            System.out.println(returnAVLTree.searchElement(temp.getStars()));
            if (!returnAVLTree.searchElement(temp.getStars())) {
                ArrayList<Business> tempList = new ArrayList<Business>();
                returnAVLTree.insertElement(temp.getStars(), tempList);
            }
//            System.out.print("Current Object Stars: ");
//            System.out.println(temp.getStars());
//            System.out.print("Node Element: ");
//            System.out.println(returnAVLTree.getElement((temp.getStars())).element);
//            System.out.print("Node List Before: ");
//            System.out.println(returnAVLTree.getElement((temp.getStars())).bizList);
            FloatNode temp2 = returnAVLTree.getElement((temp.getStars()));
            temp2.bizList.add(temp);
//            System.out.print("Node List After: ");
//            System.out.println(returnAVLTree.getElement((temp.getStars())).bizList);
//            System.out.println(temp2.element);
//            System.out.println(temp2.bizList);
        }
        return returnAVLTree;
    }

    public StringAVLTree createStateAVLTree(ArrayList<Business> listOfBiz){

        StringAVLTree returnAVLTree = new StringAVLTree();

        for (Business temp : listOfBiz) {
            if (!returnAVLTree.searchElement(temp.getState())) {
                ArrayList<Business> tempList = new ArrayList<Business>();
                returnAVLTree.insertElement(temp.getState(), tempList);
            }
//            System.out.print("Current Object State: ");
//            System.out.println(temp.getState());
//            System.out.print("Node Element: ");
//            System.out.println(returnAVLTree.getElement((temp.getState())).element);
//            System.out.print("Node List Before: ");
//            System.out.println(returnAVLTree.getElement((temp.getState())).bizList);
            returnAVLTree.getElement((temp.getState())).bizList.add(temp);
        }
        return returnAVLTree;
    }

    public StringAVLTree createCityAVLTree(ArrayList<Business> listOfBiz){

        StringAVLTree returnAVLTree = new StringAVLTree();

        for (Business temp : listOfBiz) {
            if (!returnAVLTree.searchElement(temp.getCity())) {
                ArrayList<Business> tempList = new ArrayList<Business>();
                returnAVLTree.insertElement(temp.getCity(), tempList);
            }
            returnAVLTree.getElement((temp.getCity())).bizList.add(temp);
        }
        return returnAVLTree;
    }

}