package com.example.demo.treemap;

import com.example.demo.business.Business;
import com.example.demo.reader.ReadToArray;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.*;

public class Treemapx implements Serializable {

    private static Treenodex root;
    private static final long serialVersionUID = 1L;
    public Treenodex getRoot() {
        return root;
    }
    ArrayList<Business> ratingal = new ArrayList<>();
    ArrayList<Business> reviewal = new ArrayList<>();
    ArrayList<Business> stateal = new ArrayList<>();
    ArrayList<Business> cityal = new ArrayList<>();

    public static void main(String[] args) {
        Treemapx map = new Treemapx();
        map.fill();
        // tests (all)
        // ArrayList<Business> l = map.searchAll(4.0f, 50, null, null);
        // ArrayList<Business> l = map.searchAll(null, 0, "GA", null);
        // ArrayList<Business> l = map.searchAll(5.0f, 0, "TX", null);
        // printResult(l);

        // tests (individual)
        // printResult(tm.searchRating(4)); // test using stars = 4
        // printResult(tm.searchReviewCount(80)); // test using count = 80
        // printResult(tm.searchState("OR")); // test using state = OR
        // printResult(tm.searchCity("Austin")); // test using city = austin

        // tests (tests)
		long startTime = System.nanoTime();
        ArrayList<Business> l = map.searchAll(4.0f, 100, "OR", "Portland");
		long endTime = System.nanoTime();
        long Test1duration_Treemap = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("This method took " + Test1duration_Treemap + "ns to run.");

        startTime = System.nanoTime();
        l = map.searchAll(4.0f, 20, "CO", null);
		endTime = System.nanoTime();
        Test1duration_Treemap = (endTime - startTime);
		System.out.println("This method took " + Test1duration_Treemap + "ns to run.");

        startTime = System.nanoTime();
        l = map.searchAll(null, 120, null, null);
		endTime = System.nanoTime();
        Test1duration_Treemap = (endTime - startTime);
		System.out.println("This method took " + Test1duration_Treemap + "ns to run.");

        try {
			//Saving of object in a file
			String path = "/Users/charischin/Documents/Y2S1/CS201/project/file2";
			FileOutputStream file = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(file);
			
			// Method for serialization of object
			out.writeObject(map); // object to serialise
			out.close();
			file.close();
			long longer = Files.size(Paths.get(path));
			
			System.out.println("Object has been serialized");
			System.out.println("Size of file: " + longer + "bytes");
		} catch (IOException e) {
			System.out.println("Whoops");
            e.printStackTrace();
		}
    }

    public Treemapx fill() {
        Treemapx tm = new Treemapx();
        ReadToArray fileReader = new ReadToArray();
        ArrayList<Business> businessList = fileReader.readFile("CS201/src/main/resources/yelp_academic_dataset_business.json");
        // ArrayList<Business> businessList = fileReader.readFile("/Users/charischin/Documents/Y2S1/CS201/project/classes/yelp_academic_dataset_business.json");
        for (int i = 0; i < businessList.size(); i++) {
            tm.insert(businessList.get(i));
        }
        return tm;
    }

    public void fillList(Treenodex node, ArrayList<Business> list){
        if (node == null){
            return;
        }
        fillList(node.getLeft(), list);
        list.add(node.getBusiness());
        fillList(node.getRight(), list);
    }

    public static void printAll(Treenodex root){
        if (root == null) {
            System.out.println("Null");
            return;
        } else {
            if (root.getLeft() != null) {
                printAll(root.getLeft());
            }
            System.out.println(root.getBusiness().getBusinessId() + " " + root.getBusiness().getName() 
                            + " | Rating: " + root.getBusiness().getStars() 
                            + ", Reviews: " + root.getBusiness().getReviewCount() 
                            + ", State: " + root.getBusiness().getState()
                            + ", City: " + root.getBusiness().getCity());
            if (root.getRight() != null) {
                printAll(root.getRight());
            }
        }
    }

    public static void printResult(ArrayList<Business> al){
        System.out.println("\nLIST OF BUSINESSES");
        for(int i = 0; i < al.size(); i++) {
            System.out.println(al.get(i).getName());
        }
        System.out.println("--------\nCount: "+ al.size());
    }

    public void insert(Business business) {
        root = insertRecursive(root, business); 
    }

    public Treenodex insertRecursive(Treenodex root, Business business) { 
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

    public ArrayList<Business> searchRating(float rating) {
        // ArrayList<Business> rg = searchRatingRecursive(root, rating);
        // if (root != null) {
        //     if (root.getBusiness().getStars() >= rating) {
        //         rg.add(root.getBusiness());
        //     }
        // }
        // System.out.println("\nBUSINESS RATING");
        // for(int i = 0; i < rg.size(); i++) {
        //     System.out.println(rg.get(i).getName());
        // }
        // System.out.println("--------\nCount: "+ rg.size());
        ratingal = searchRatingRecursive(root, rating);
        if (root != null) {
            if (root.getBusiness().getStars() >= rating) {
                ratingal.add(root.getBusiness());
            }
        }
        return ratingal;
    }

    public ArrayList<Business> searchRatingRecursive(Treenodex root, float rating) {
        // ArrayList<Business> a = new ArrayList<>();
        // if (Double.parseDouble(root.getBusiness().getStars()) >= rating) {
        //     a.add(root.getBusiness());
        //     System.out.println("yay");
        // }
        if (root.getLeft() != null) {
            searchRatingRecursive(root.getLeft(), rating);
            if (root.getLeft().getBusiness().getStars() >= rating) {
                ratingal.add(root.getLeft().getBusiness());
                // System.out.println("hi");
                // System.out.println(a);
            }
        }
        // System.out.println(root.getBusiness().getBusinessId());
        if (root.getRight() != null) {
            searchRatingRecursive(root.getRight(), rating);
            if (root.getRight().getBusiness().getStars() >= rating) {
                ratingal.add(root.getRight().getBusiness());
                // System.out.println("bye");
            }
        }
        return ratingal;
    }

    public ArrayList<Business> searchReviewCount(int count) {
        reviewal = searchReviewCountRecursive(root, count);
        if (root != null) {
            if (root.getBusiness().getReviewCount() >= count) {
                reviewal.add(root.getBusiness());
            }
        }
        return reviewal;
    }

    public ArrayList<Business> searchReviewCountRecursive(Treenodex root, int count) {
        if (root.getLeft() != null) {
            searchReviewCountRecursive(root.getLeft(), count);
            if (root.getLeft().getBusiness().getReviewCount() >= count) {
                reviewal.add(root.getLeft().getBusiness());
            }
        }
        if (root.getRight() != null) {
            searchReviewCountRecursive(root.getRight(), count);
            if (root.getRight().getBusiness().getReviewCount() >= count) {
                reviewal.add(root.getRight().getBusiness());
            }
        }
        return reviewal;
    }

    public ArrayList<Business> searchState(String state) {
        stateal = searchStateRecursive(root, state);
        if (root != null) {
            if (root.getBusiness().getState().equals(state)) {
                stateal.add(root.getBusiness());
            }
        }
        return stateal;
    }

    public ArrayList<Business> searchStateRecursive(Treenodex root, String state) {
        if (root.getLeft() != null) {
            searchStateRecursive(root.getLeft(), state);
            if (root.getLeft().getBusiness().getState().equals(state)) {
                stateal.add(root.getLeft().getBusiness());
            }
        }
        if (root.getRight() != null) {
            searchStateRecursive(root.getRight(), state);
            if (root.getRight().getBusiness().getState().equals(state)) {
                stateal.add(root.getRight().getBusiness());
            }
        }
        return stateal;
    }

    public ArrayList<Business> searchCity(String city) {
        cityal = searchCityRecursive(root, city);
        if (root != null) {
            if (root.getBusiness().getCity().equals(city)) {
                cityal.add(root.getBusiness());
            }
        }
        return cityal;
    }
    
    public ArrayList<Business> searchCityRecursive(Treenodex root, String city) {
        if (root.getLeft() != null) {
            searchCityRecursive(root.getLeft(), city);
            if (root.getLeft().getBusiness().getCity().equals(city)) {
                cityal.add(root.getLeft().getBusiness());
            }
        }
        if (root.getRight() != null) {
            searchCityRecursive(root.getRight(), city);
            if (root.getRight().getBusiness().getCity().equals(city)) {
                cityal.add(root.getRight().getBusiness());
            }
        }
        return cityal;
    }

    public ArrayList<Business> searchAll(Float rating, int count, String state, String city) {
        Treemapx map = new Treemapx();
        map.fill();
        ArrayList<Business> allal = new ArrayList<Business>();
        ArrayList<Business> toreturn = new ArrayList<Business>();
        fillList(map.getRoot(), allal);
        fillList(map.getRoot(), toreturn);
        if (rating != null) {
            ArrayList<Business> temp = map.searchRating(rating);
            for (Business b : allal) {
                if (!temp.contains(b)) {
                    toreturn.remove(b);
                }
            }
        }
        if (count != 0) {
            ArrayList<Business> temp = map.searchReviewCount(count);
            for (Business b : allal) {
                if (!temp.contains(b)) {
                    toreturn.remove(b);
                }
            }
        }
        if (state != null) {
            ArrayList<Business> temp = map.searchState(state);
            for (Business b : allal) {
                if (!temp.contains(b)) {
                    toreturn.remove(b);
                }
            }
        }
        if (city != null) {
            ArrayList<Business> temp = map.searchCity(city);
            for (Business b : allal) {
                if (!temp.contains(b)) {
                    toreturn.remove(b);
                }
            }
        }
        return toreturn;
    }
}
