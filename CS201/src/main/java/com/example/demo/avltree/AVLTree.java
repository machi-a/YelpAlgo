package com.example.demo.avltree;

import com.example.demo.business.Business;
import com.example.demo.reader.ReadToArray;
import com.sun.tools.javac.Main;

import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;

public class AVLTree implements Serializable {
    private static final long serialVersionUID = 1L;

    MainAVLTree mainTree = null;
    FloatAVLTree starsTree = null;
    IntAVLTree reviewsTree = null;
    public StringAVLTree stateTree = null;
    StringAVLTree cityTree = null;

    AVLStorage store = new AVLStorage();
    ReadToArray read = new ReadToArray();

    public AVLTree(String filepath){
        ArrayList<Business> listOfBiz = read.readFile(filepath);
        mainTree = store.createMainAVLTree(listOfBiz);
        starsTree = store.createStarAVLTree(listOfBiz);
        reviewsTree = store.createReviewCountAVLTree(listOfBiz);
        stateTree = store.createStateAVLTree(listOfBiz);
        cityTree = store.createCityAVLTree(listOfBiz);
    }

    public ArrayList<Business> getMinStars(float minStar){
        ArrayList<Business> returnBizList = new ArrayList<Business>();
        starsTree.getMinFloat(minStar, returnBizList);
        return returnBizList;
    }

    public ArrayList<Business> getMinReview(int minReview){
        ArrayList<Business> returnBizList = new ArrayList<Business>();
        reviewsTree.getMinReview(minReview, returnBizList);
        return returnBizList;
    }

    public ArrayList<Business> getStateList(String state){

        ArrayList<Business> returnBizList = new ArrayList<Business>();
        if (state == null){
            stateTree.returnOrderedList(returnBizList);
            return returnBizList;
        }
        stateTree.getStateList(state, returnBizList);
        return returnBizList;
    }

    public ArrayList<Business> getCityList(String city){
        ArrayList<Business> returnBizList = new ArrayList<Business>();
        if (city == null){
            cityTree.returnOrderedList(returnBizList);
            return returnBizList;
        }
        cityTree.getCityList(city, returnBizList);
        return returnBizList;
    }

    public ArrayList<Business> filterAll(Float minStar, int minReview, String state, String city){
        ArrayList<Business> starList = getMinStars(minStar);
        ArrayList<Business> reviewList = getMinReview(minReview);
        ArrayList<Business> stateList = getStateList(state);
        ArrayList<Business> cityList = getCityList(city);
        cityList.retainAll(reviewList);
        cityList.retainAll(starList);
        cityList.retainAll(stateList);
    return cityList;
    }


    public void printAllNodes(){
//        mainTree.inorderTraversal();
//        System.out.println();
        stateTree.inorderTraversal();
//        System.out.println();
//        cityTree.inorderTraversal();
//        System.out.println();
//        starsTree.inorderTraversal();
//        System.out.println();
//        reviewsTree.inorderTraversal();

    }




}
