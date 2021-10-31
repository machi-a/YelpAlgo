package com.example.demo.treemap;

import com.example.demo.business.Business;
import com.example.demo.reader.ReadToArray;

import java.util.*;

public class Treemapx {

    private static Treenodex root;
    ArrayList<Business> a = new ArrayList<>();

    public static void main(String[] args) {
        Treemapx tm = new Treemapx();
        ReadToArray fileReader = new ReadToArray();
        ArrayList<Business> businessList = fileReader.readFile("/Users/charischin/Documents/Y2S1/CS201/project/classes/yelp_academic_dataset_business.json");
        for (int i = 0; i < 10; i++) { // i to be replaced with businessList.size()
            tm.insert(businessList.get(i));
            // System.out.println(businessList.get(i));
        }
        // insert(business);
        printTree(root);
        tm.searchRating(4); // test using stars = 4
    }

    private static void printTree(Treenodex root){
        if (root == null) {
            System.out.println("Null");
            return;
        } else {
            if (root.getLeft() != null) {
                printTree(root.getLeft());
            }
            System.out.println(root.getBusiness().getBusinessId() + " - " + root.getBusiness().getName() + " | Rating: " + root.getBusiness().getStars() + ", Reviews: " + root.getBusiness().getReviewCount());
            if (root.getRight() != null) {
                printTree(root.getRight());
            }
        }
    }

    private void insert(Business business) {
        root = insertRecursive(root, business); 
    }

    private Treenodex insertRecursive(Treenodex root, Business business) { 
        if (root == null) { 
            root = new Treenodex(business, null, null); 
            return root; 
        } 
        if (business.getBusinessId().compareTo(root.getBusiness().getBusinessId()) < 0)
            root.setLeft(insertRecursive(root.getLeft(), business));
        else if (business.getBusinessId().compareTo(root.getBusiness().getBusinessId()) > 0)
            root.setRight(insertRecursive(root.getRight(), business));
        return root; 
    }

    private void searchRating(float rating) {
        ArrayList<Business> r = searchRatingRecursive(root, rating);
        if (root != null) {
            if (root.getBusiness().getStars() >= rating) {
                r.add(root.getBusiness());
            }
        }
        System.out.println("Businesses: ");
        for(int i = 0; i < r.size(); i++) {
            System.out.println(r.get(i).getName());
        }
        System.out.println("Count: "+ r.size());
    }

    private ArrayList<Business> searchRatingRecursive(Treenodex root, float rating) {
        // ArrayList<Business> a = new ArrayList<>();
        // if (Double.parseDouble(root.getBusiness().getStars()) >= rating) {
        //     a.add(root.getBusiness());
        //     System.out.println("yay");
        // }
        if (root.getLeft() != null) {
            searchRatingRecursive(root.getLeft(), rating);
            if (root.getLeft().getBusiness().getStars() >= rating) {
                a.add(root.getLeft().getBusiness());
                // System.out.println("hi");
                // System.out.println(a);
            }
        }
        // System.out.println(root.getBusiness().getBusinessId());
        if (root.getRight() != null) {
            searchRatingRecursive(root.getRight(), rating);
            if (root.getRight().getBusiness().getStars() >= rating) {
                a.add(root.getRight().getBusiness());
                // System.out.println("bye");
            }
        }
        return a;
    }

}
