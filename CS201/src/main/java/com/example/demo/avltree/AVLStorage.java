package com.example.demo.avltree;

import com.example.demo.business.Business;
import com.example.demo.reader.ReadToArray;
import com.example.demo.avltree.IntAVLTree;

import java.util.ArrayList;
import java.util.Iterator;

class AVLStorage {

    ReadToArray read;

    public MainAVLTree createMainAVLTree(String filepath){

        MainAVLTree returnAVLTree = new MainAVLTree();
        ArrayList<Business> listOfBiz = read.readFile(filepath);
        Iterator<Business> bizIterator = listOfBiz.iterator();

        while(bizIterator.hasNext()) {
            returnAVLTree.insertElement(bizIterator.next());
            System.out.println(bizIterator.next());
        }
        return returnAVLTree;
    }

    public IntAVLTree createReviewCountAVLTree(String filepath){

        IntAVLTree returnAVLTree = new IntAVLTree();
        ArrayList<Business> listOfBiz = read.readFile(filepath);
        Iterator<Business> bizIterator = listOfBiz.iterator();

        while(bizIterator.hasNext()) {
            returnAVLTree.insertElement(bizIterator.next().getReviewCount());
        }
        return returnAVLTree;
    }

    public FloatAVLTree createStarAVLTree(String filepath){

        FloatAVLTree returnAVLTree = new FloatAVLTree();
        ArrayList<Business> listOfBiz = read.readFile(filepath);
        Iterator<Business> bizIterator = listOfBiz.iterator();

        while(bizIterator.hasNext()) {
            returnAVLTree.insertElement(bizIterator.next().getStars());
        }
        return returnAVLTree;
    }

    public StringAVLTree createStateAVLTree(String filepath){

        StringAVLTree returnAVLTree = new StringAVLTree();
        ArrayList<Business> listOfBiz = read.readFile(filepath);
        Iterator<Business> bizIterator = listOfBiz.iterator();

        while(bizIterator.hasNext()) {
            returnAVLTree.insertElement(bizIterator.next().getState());
        }
        return returnAVLTree;
    }







}