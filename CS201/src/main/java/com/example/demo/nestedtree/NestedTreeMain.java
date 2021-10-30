package com.example.demo.NestedTree;

import com.example.demo.business.Business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class NestedTreeMain {
    private static MainTreeNode root;
    public static void main(String[] args) {
        LoadNestedTree();
        Scanner sc= new Scanner(System.in);
        System.out.println("Choose an option:");
        String option =sc.nextLine();


        switch(option){
            case "1":
                System.out.println("Enter the rating of restaurant:");
                String input = sc.nextLine();
                MainTreeNode ratingGroup = searchByRating(root, Double.parseDouble(input));
                printSubTree(ratingGroup.getRoot());
                break;
            case "2":
                System.out.println("Enter the rating of restaurant:");
//                String input = sc.nextLine();
//                MainTreeNode ratingGroup = searchByRating(root, Double.parseDouble(input));
//                printSubTree(ratingGroup.getRoot());
                System.out.println("Enter the name of restaurant:");
                break;
        }


    }
    public static void LoadNestedTree(){

        ArrayList<Business> returned = readFile("/Users/charzzzzy/YelpAlgo/CS201/target/classes/yelp_academic_dataset_business.json");

//		ArrayList<Business> returned= readFile("/Users/charzzzzy/Downloads/yelp_dataset/yelp_academic_dataset_business.json");
        HashMap<Double, ArrayList<Business> > grouped=groupRatings(returned);


//		for(Double d: grouped.keySet()){
//			System.out.println("_____________________");
//			System.out.println("Ratings "+ d +"  :");
//			createSubTree(grouped.get(d));
//		}

        linkSubTree(groupRatings(returned));
        printMainTree(root);


    }
    public static ArrayList<Business> readFile(String filepath){
        ArrayList<Business> businessList = new ArrayList<Business> ();
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





    //    Group  businesses by stars
    public static HashMap<Double, ArrayList<Business> > groupRatings(ArrayList<Business> businessList){

        HashMap<Double, ArrayList<Business> >map = new HashMap<Double, ArrayList<Business> >();
        for(Business b: businessList){
            if(map.containsKey(Double.parseDouble(b.getStars()))){

                ArrayList<Business> current=map.get(Double.parseDouble(b.getStars()));
                current.add(b);
                map.put(Double.parseDouble(b.getStars()), current);
            }else{
                ArrayList<Business> toAdd= new ArrayList<Business> ();
                toAdd.add(b);
                map.put(Double.parseDouble(b.getStars()), toAdd);
            }
        }
        return map;

    }
    // within groups of businesses, organise base on postal code (form subtrees)
    public static SubTreeNode createSubTree(ArrayList<Business> business) {
        SubTreeNode root = null;
        for (Business b : business) {
            if(b.getPostalCode()!=null){
                SubTreeNode store = new SubTreeNode(b, null, null);
                root = insertSubTree(root, store);
            }

        }
        printSubTree(root);
        return root;
    }

    private static MainTreeNode searchByRating(MainTreeNode root, Double target)
    {
        // Base Cases: root is null or key is present at root
        if (root==null || root.getValue()==target)
            return root;

        // Key is greater than root's key
        if (root.getValue() < target)
            return searchByRating(root.getRight(), target);

        // Key is smaller than root's key
        return searchByRating(root.getLeft(),target);
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

            System.out.println(root.getBusiness().getName() + " ");


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
            System.out.println("Ratings " + root.getValue() +" :");
            printSubTree(root.getRoot());


            if(root.getRight()!= null){
                printMainTree(root.getRight());
            }


        }
    }

    private static SubTreeNode insertSubTree(SubTreeNode root, SubTreeNode store) {
        if (root == null) {
            root = store;
            return root;
        }
//		if (Integer.parseInt(store.getBusiness().getPostalCode()) <Integer.parseInt(root.getBusiness().getPostalCode())) {
        if(root.getBusiness().getName().compareTo(store.getBusiness().getName())>0){
            root.setLeft(insertSubTree(root.getLeft(), store));
        }
        else if(root.getBusiness().getName().compareTo(store.getBusiness().getName())<0){
//		else if ((Integer.parseInt(store.getBusiness().getPostalCode())> Integer.parseInt(root.getBusiness().getPostalCode()))){

            root.setRight(insertSubTree(root.getRight(), store));
        }
        return root;
    }


    // form Main TreeNode - consist of ratings, subtree, left, right, parent
    public static void linkSubTree(HashMap<Double, ArrayList<Business> > map){
        for(double ratings: map.keySet()){
            MainTreeNode toInsert= new MainTreeNode(ratings, createSubTree(map.get(ratings)) ,null,null );
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
        if (toInsert.getValue() < root.getValue())
            root.setLeft(insertMainTree(root.getLeft(), toInsert));
        else if (toInsert.getValue() > root.getValue())
            root.setRight(insertMainTree(root.getRight(), toInsert));
        /* return the (unchanged) node pointer */
        return root;
    }
}
