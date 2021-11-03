package com.example.demo.NestedTree;


import com.example.demo.business.Business;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HybridTree {

    private static ArrayList<Business> toReturn= new ArrayList<>();



    public static HashMap< String , SubTreeNode>  LoadHybridStructure( ArrayList<Business> returned ){

//		ArrayList<Business> returned= readFile("/Users/charzzzzy/Downloads/yelp_dataset/yelp_academic_dataset_business.json");
        HashMap<String,ArrayList<Business>> stateGrouped= groupStates(returned);

        HashMap< String , SubTreeNode> toReturn= formHybridStructure(stateGrouped);

        return toReturn;


    }

    public static  HashMap< String , SubTreeNode> formHybridStructure( HashMap<String,ArrayList<Business>> map){
        HashMap< String , SubTreeNode> toReturn = new HashMap<>();
        for(String state: map.keySet()){

            toReturn.put(state, createSubTree(map.get(state)));
        }
        return toReturn;
    }



    /*


    Group Businesses into Cities

     */
    //
    public static HashMap<String, ArrayList<Business> > groupStates(ArrayList<Business> businessList){

        HashMap<String, ArrayList<Business> >map = new HashMap<String, ArrayList<Business> >();
        for(Business b: businessList){
            if(map.containsKey(b.getState())){

                ArrayList<Business> current=map.get(b.getState());
                current.add(b);
                map.put(b.getState(), current);
            }else{
                ArrayList<Business> toAdd= new ArrayList<Business> ();
                toAdd.add(b);
                map.put(b.getState(), toAdd);
            }
        }

        return map;

    }
    // within groups of businesses, organise base on postal code (form subtrees)

    public static SubTreeNode createSubTree(ArrayList<Business> business) {
        SubTreeNode root = null;
        for (Business b : business) {
//            if(b.getPostalCode()!=null){
            SubTreeNode store = new SubTreeNode(b, null, null);
            root = insertSubTree(root, store);
//            }

        }
//        printSubTree(root);
        return root;
    }



    private static MainTreeNode searchByState(MainTreeNode root, String state) {
        // Base Cases: root is null or key is present at root
        if (root == null || root.getValue().equals(state)){
            return root;
        }


        // Key is greater than root's key
        if (root.getValue().compareTo(state) < 0)
            return searchByState(root.getRight(), state);

        return searchByState(root.getLeft(), state);
    }


    private static void searchByCity(SubTreeNode root, String city){
        if (root==null)
            return;

        if(root.getBusiness().getCity().equals(city)){

            toReturn.add(root.getBusiness());
        }

        // Key is greater than root's key
        if (root.getBusiness().getCity().compareTo(city)<0)
            searchByCity(root.getRight(), city);

        // Key is smaller than root's key
        searchByCity(root.getLeft(),city);

    }

    public static ArrayList<Business> searchByCityAndState(HashMap<String, SubTreeNode> map, String state, String city) throws BusinessNotFoundException{

        if(!map.containsKey(state)){
          throw new BusinessNotFoundException();
        }
        searchByCity(map.get(state),city);
        return toReturn;
    }





    private static void printSubTree(SubTreeNode root){
        //Check whether tree is empty

        if(root == null){
            System.out.println("Tree is empty");
            return;
        }
        else {

            if(root.getLeft()!= null){
                printSubTree(root.getLeft());
            }

            System.out.print(root.getBusiness().getName() + " ");
            System.out.println(root.getBusiness().getCity() + " ");
//            System.out.println(root.getBusiness().getState());


            if(root.getRight()!= null){
                printSubTree(root.getRight());
            }


        }
    }


    private static SubTreeNode insertSubTree(SubTreeNode root, SubTreeNode toInsert) {
        if (root == null) {
            root = toInsert;
            return root;
        }


        if (toInsert.getBusiness().getCity().compareTo(root.getBusiness().getCity())<=0) {

//		if(root.getBusiness().getName().compareTo(store.getBusiness().getName())>0){
            root.setLeft(insertSubTree(root.getLeft(), toInsert));
            if (getHeight(root.getLeft()) - getHeight(root.getRight()) == 2)
                if (toInsert.getBusiness().getCity().compareTo(root.getLeft().getBusiness().getCity()) < 0)
                    root = rotateMainTreeWithLeftChild(root);
                else
                    root = doubleWithLeftChild(root);
        }
//		else if(root.getBusiness().getName().compareTo(store.getBusiness().getName())<0){
        else if (toInsert.getBusiness().getCity().compareTo(root.getBusiness().getCity())>0){

            root.setRight(insertSubTree(root.getRight(), toInsert));
            if (getHeight(root.getRight()) - getHeight(root.getLeft()) == 2)
                if (toInsert.getBusiness().getCity().compareTo(root.getRight().getBusiness().getCity()) > 0)
                    root = rotateMainTreeWithRightChild(root);
                else
                    root = doubleWithRightChild(root);
        }
        return root;
    }



    // create insertElement() to insert element into the AVL Tree

    //create getHeight() method to get the height of the AVL Tree


    //create maxNode() method to get the maximum height from left and right node
    private static int getMaxHeight(int leftNodeHeight, int rightNodeHeight) {
        return leftNodeHeight > rightNodeHeight ? leftNodeHeight : rightNodeHeight;
    }



    //create getHeight() method to get the height of the AVL Tree
    private static int getHeight(SubTreeNode  node) {
        return node == null ? -1 : node.getHeight();
    }



    // creating rotateWithLeftChild() method to perform rotation of binary tree node with left child
    private static SubTreeNode rotateMainTreeWithLeftChild(SubTreeNode node2) {
        SubTreeNode node1 = node2.getLeft();
        node2.setLeft(node1.getRight());
        node1.setRight(node2);


        node2.setHeight(getMaxHeight(getHeight(node2.getLeft()), getHeight(node2.getRight())) + 1);
        node1.setHeight(getMaxHeight(getHeight(node1.getLeft()), node2.getHeight()) + 1);
        return node1;
    }

    // creating rotateWithRightChild() method to perform rotation of binary tree node with right child
    private static SubTreeNode rotateMainTreeWithRightChild(SubTreeNode node1) {
        SubTreeNode node2 = node1.getRight();
        node1.setRight(node2.getLeft());
        node2.setLeft(node1);
        node1.setHeight(getMaxHeight(getHeight(node1.getLeft()), getHeight(node1.getRight())) + 1);
        node2.setHeight(getMaxHeight(getHeight(node2.getRight()), node1.getHeight()) + 1);
        return node2;
    }

    //create doubleWithLeftChild() method to perform double rotation of binary tree node. This method first rotate the left child with its right child, and after that, node3 with the new left child
    private static SubTreeNode doubleWithLeftChild(SubTreeNode node3) {
        node3.setLeft(rotateMainTreeWithRightChild(node3.getLeft()));
        return rotateMainTreeWithLeftChild(node3);
    }

    //create doubleWithRightChild() method to perform double rotation of binary tree node. This method first rotate the right child with its left child and after that node1 with the new right child
    private static SubTreeNode  doubleWithRightChild(SubTreeNode node1) {
        node1.setRight(rotateMainTreeWithLeftChild(node1.getRight()));
        return rotateMainTreeWithRightChild(node1);
    }



    private int getTotalNumberOfNodes(SubTreeNode head) {
        if (head == null)
            return 0;
        else {
            int length = 1;
            length = length + getTotalNumberOfNodes(head.getLeft());
            length = length + getTotalNumberOfNodes(head.getRight());
            return length;
        }
    }



}
