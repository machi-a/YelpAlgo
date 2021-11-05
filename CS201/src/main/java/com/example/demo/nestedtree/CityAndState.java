package com.example.demo.nestedtree;

import com.example.demo.business.Business;
import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


@SpringBootApplication
public class CityAndState  {


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
            e.printStackTrace();
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

    private static SubTreeNode insertSubTree(SubTreeNode root, SubTreeNode store) {
        if (root == null) {
            root = store;
            return root;
        }


        if (store.getBusiness().getCity().compareTo(root.getBusiness().getCity())<=0) {

//		if(root.getBusiness().getName().compareTo(store.getBusiness().getName())>0){
            root.setLeft(insertSubTree(root.getLeft(), store));
        }
//		else if(root.getBusiness().getName().compareTo(store.getBusiness().getName())<0){
        else if (store.getBusiness().getCity().compareTo(root.getBusiness().getCity())>0){

            root.setRight(insertSubTree(root.getRight(), store));
        }
        return root;
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
        if (toInsert.getValue().compareTo(root.getValue())<=0)
            root.setLeft(insertMainTree(root.getLeft(), toInsert));
        else if (toInsert.getValue().compareTo(root.getValue())>0)
            root.setRight(insertMainTree(root.getRight(), toInsert));
        /* return the (unchanged) node pointer */
        return root;
    }
}


