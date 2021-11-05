package com.example.demo.nestedtree;

import com.example.demo.business.Business;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CityAndStateAVL {

    public static MainTreeNode root;

    private static ArrayList<Business> toReturn= new ArrayList<>();



    public static void LoadNestedTree(ArrayList<Business> returned){


//		ArrayList<Business> returned= readFile("/Users/charzzzzy/Downloads/yelp_dataset/yelp_academic_dataset_business.json");
        HashMap<String, ArrayList<Business> > grouped=groupStates(returned);

        linkSubTree(groupStates(returned));



    }
    public static ArrayList<Business> readFile(String filepath){
        ArrayList<Business> businessList = new ArrayList<Business>();
        try {
            FileReader reader = new FileReader(filepath);

            // System.out.println("FILE");
            BufferedReader bufferedReader = new BufferedReader(reader);

            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                Gson gson = new Gson();

                Business business = gson.fromJson(currentLine.toString(), Business.class);
                businessList.add(business);
            }
        } catch (IOException e) {
            System.out.println("No File Found");
        }
        return businessList;
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

    public static ArrayList<Business> searchByCityAndState(MainTreeNode root, String state, String city) throws BusinessNotFoundException{
        MainTreeNode found= searchByState(root, state);
        System.out.println(found);
        if(found==null){
            throw new BusinessNotFoundException();
        }
        searchByCity(found.getRoot(), city);
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

    private static void printMainTree(MainTreeNode root){
        //Check whether tree is empty

        if(root == null){
            System.out.println("Tree is empty");
            return;
        }
        else {

            if(root.getLeft()!= null){
                printMainTree(root.getLeft());
            }

            System.out.println("_____________________");
            System.out.println("State :" + root.getValue() +" :");
            printSubTree(root.getRoot());


            if(root.getRight()!= null){
                printMainTree(root.getRight());
            }


        }
    }



    // form Main TreeNode - consist of ratings, subtree, left, right, parent
    public static void linkSubTree(HashMap<String, ArrayList<Business> > map){
        for(String state: map.keySet()){
//            printSubTree(createSubTree(map.get(state)));
            MainTreeNode toInsert= new MainTreeNode(state, createSubTree(map.get(state)) ,null,null );
//            printSubTree(toInsert.getRoot());
            root=insertMainTree(root,toInsert);
        }

    }


    ///	insert Treenode into main tree
    public static MainTreeNode insertMainTree(MainTreeNode root, MainTreeNode toInsert)
    {

        if (root == null)
        {
            root=toInsert;
            return root;
        }
        if (toInsert.getValue().compareTo(root.getValue())<=0) {
            root.setLeft(insertMainTree(root.getLeft(), toInsert));
            if (getHeight(root.getLeft()) - getHeight(root.getRight()) == 2){
                if (toInsert.getValue().compareTo(root.getLeft().getValue()) < 0)
                    root = rotateMainTreeWithLeftChild(root);
                else
                    root = doubleWithLeftChild(root);
            }

        }else if (toInsert.getValue().compareTo(root.getValue())>0) {
                root.setRight(insertMainTree(root.getRight(), toInsert));
                if (getHeight(root.getRight()) - getHeight(root.getLeft()) == 2){
                    if (toInsert.getValue().compareTo(root.getRight().getValue()) > 0)
                        root = rotateMainTreeWithRightChild(root);
                    else
                        root = doubleWithRightChild(root);
                }

            }
        /* return the (unchanged) node pointer */

        root.setHeight(getMaxHeight(getHeight(root.getLeft()), getHeight(root.getRight())) + 1);

        return root;
    }


        //create removeAll() method to make AVL Tree empty
        public void removeAll() {
            root= null;
        }

        // create checkEmpty() method to check whether the AVL Tree is empty or not
        public boolean checkEmpty() {
            return root== null;
        }

        // create insertElement() to insert element into the AVL Tree


        //create getHeight() method to get the height of the AVL Tree
        private static int getHeight(MainTreeNode  node) {
            return node == null ? -1 : node.getHeight();
        }

        //create maxNode() method to get the maximum height from left and right node
        private static int getMaxHeight(int leftNodeHeight, int rightNodeHeight) {
            return leftNodeHeight > rightNodeHeight ? leftNodeHeight : rightNodeHeight;
        }




        // creating rotateWithLeftChild() method to perform rotation of binary tree node with left child
        private static MainTreeNode rotateMainTreeWithLeftChild(MainTreeNode node2) {
            MainTreeNode node1 = node2.getLeft();
            node2.setLeft(node1.getRight());
            node1.setRight(node2);


            node2.setHeight(getMaxHeight(getHeight(node2.getLeft()), getHeight(node2.getRight())) + 1);
            node1.setHeight(getMaxHeight(getHeight(node1.getLeft()), node2.getHeight()) + 1);
            return node1;
        }

        // creating rotateWithRightChild() method to perform rotation of binary tree node with right child
        private static MainTreeNode rotateMainTreeWithRightChild(MainTreeNode node1) {
            MainTreeNode node2 = node1.getRight();
            node1.setRight(node2.getLeft());
            node2.setLeft(node1);
            node1.setHeight(getMaxHeight(getHeight(node1.getLeft()), getHeight(node1.getRight())) + 1);
            node2.setHeight(getMaxHeight(getHeight(node2.getRight()), node1.getHeight()) + 1);
            return node2;
        }

        //create doubleWithLeftChild() method to perform double rotation of binary tree node. This method first rotate the left child with its right child, and after that, node3 with the new left child
        private static MainTreeNode doubleWithLeftChild(MainTreeNode node3) {
            node3.setLeft(rotateMainTreeWithRightChild(node3.getLeft()));
            return rotateMainTreeWithLeftChild(node3);
        }

        //create doubleWithRightChild() method to perform double rotation of binary tree node. This method first rotate the right child with its left child and after that node1 with the new right child
        private static MainTreeNode  doubleWithRightChild(MainTreeNode node1) {
            node1.setRight(rotateMainTreeWithLeftChild(node1.getRight()));
            return rotateMainTreeWithRightChild(node1);
        }

        //create getTotalNumberOfNodes() method to get total number of nodes in the AVL Tree
        public int getTotalNumberOfNodes() {
            return getTotalNumberOfNodes(root);
        }

        private int getTotalNumberOfNodes(MainTreeNode head) {
            if (head == null)
                return 0;
            else {
                int length = 1;
                length = length + getTotalNumberOfNodes(head.getLeft());
                length = length + getTotalNumberOfNodes(head.getRight());
                return length;
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
