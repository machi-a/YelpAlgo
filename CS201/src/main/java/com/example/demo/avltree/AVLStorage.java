package com.example.demo.avltree;

import com.example.demo.business.Business;
import com.example.demo.reader.ReadToArray;

import java.util.ArrayList;
import java.util.Iterator;

class AVLStorage {

    ReadToArray read;

    public MainAVLTree createMainAVLTree(String filepath){

        MainAVLTree returnAVLTree = new MainAVLTree();
        ReadToArray fileReader = new ReadToArray();
        ArrayList<Business> listOfBiz = read.readFile(filepath);
        Iterator<Business> bizIterator = listOfBiz.iterator();

        while(bizIterator.hasNext()) {
            returnAVLTree.insertElement((Business) bizIterator.next());
            System.out.println(bizIterator.next());
        }
        return returnAVLTree;
    }







}