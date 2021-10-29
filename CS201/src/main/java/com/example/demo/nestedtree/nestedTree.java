package com.example.demo.nestedtree;

import com.example.demo.business.Business;
import com.example.demo.TreeNode;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class nestedTree {


//    public static ArrayList<Business> readFile(String filepath){
//        ArrayList<Business> toReturn= new ArrayList<>();
//        try {
//
//
//            FileReader reader = new FileReader(filepath);
//
//            System.out.println("FILE");
//            BufferedReader bufferedReader = new BufferedReader(reader);
//
//            String currentLine;
//            while ((currentLine = bufferedReader.readLine()) != null) {
//
//                Gson gson = new Gson();
//
//                Business business = gson.fromJson(currentLine.toString(), Business.class);
//                toReturn.add(business);
//
//            }
//            // System.out.println("SUCCESS");
//
//
//        } catch (IOException e) {
//            System.out.println("Here");
//        }
//        return toReturn;
//
//    }
//
//    // store tree based on ratings;
//    public static HashMap<Double, ArrayList<Business> > groupRatings(ArrayList<Business> businessList){
//
//        HashMap<Double, ArrayList<Business> >map = new HashMap<>();
//        for(Business b: businessList){
//            if(map.containsKey(Double.parseDouble(b.getStars()))){
//
//                ArrayList<Business> current=map.get(Double.parseDouble(b.getStars()));
//                current.add(b);
//                map.put(Double.parseDouble(b.getStars()), current);
//            }else{
//                ArrayList<Business> toAdd= new ArrayList<>();
//                toAdd.add(b);
//                map.put(Double.parseDouble(b.getStars()), toAdd);
//            }
//        }
//        return map;
//
//    }
//
//    public static TreeNode createSubTree(ArrayList<Business> business) {
//
//        for (Business b : business) {
//            TreeNode store = new TreeNode(Double.parseDouble(b.getPostalCode()), null, null, null);
//            formTree( root,store);
//        }
//        return null;
//    }
//
//
//    public static void InsertIntoTree(HashMap<Double, ArrayList<Business> > map){
//        for(double d: map.keySet()){
//            TreeNode ref=createSubTree(map.get(d));
//            TreeNode toInsert= new TreeNode(d,ref,null,null);
//            formTree(root,toInsert);
//        }
//
//
//    }
//
//    ///	insert treenode into tree
//    public static TreeNode formTree(TreeNode root, TreeNode toInsert)
//    {
//
//        if (root == null)
//        {
//            root=toInsert;
//            return root;
//        }
//        if (toInsert.getValue() < root.getValue())
//            root.setLeft(formTree(root.getLeft(), toInsert));
//        else if (toInsert.getValue() > root.getValue())
//            root.setRight(formTree(root.getRight(), toInsert));
//
//        /* return the (unchanged) node pointer */
//        return root;
//    }

}
