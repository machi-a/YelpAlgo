package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



import java.io.*;
import java.io.IOException;
import java.math.BigDecimal;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;

import java.util.Iterator;

import org.json.*;
import com.google.gson.Gson;


@SpringBootApplication
public class Cs201Application {
	private static TreeNode root;
	public static void main(String[] args) {
//
//		SpringApplication.run(Cs201Application.class, args);
		ArrayList<Business> businessList=readFile("/Users/charzzzzy/Documents/CS201/src/main/java/com/example/demo/yelp_academic_dataset_business.json");
		HashMap<Double, ArrayList<Business> > map= groupRatings(businessList);
		InsertIntoTree(map);

	}


	public static ArrayList<Business> readFile(String filepath){
		ArrayList<Business> toReturn= new ArrayList<>();
		try {


			FileReader reader = new FileReader(filepath);

			System.out.println("FILE");
			BufferedReader bufferedReader = new BufferedReader(reader);

			String currentLine;
			while ((currentLine = bufferedReader.readLine()) != null) {

				Gson gson = new Gson();

				Business business = gson.fromJson(currentLine.toString(), Business.class);
				toReturn.add(business);

			}
			// System.out.println("SUCCESS");


		} catch (IOException e) {
			System.out.println("Here");
		}
		return toReturn;

	}

	// store tree based on ratings;
	public static HashMap<Double, ArrayList<Business> > groupRatings(ArrayList<Business> businessList){

		HashMap<Double, ArrayList<Business> >map = new HashMap<>();
		for(Business b: businessList){
			if(map.containsKey(Double.parseDouble(b.getStars()))){

				ArrayList<Business> current=map.get(Double.parseDouble(b.getStars()));
				current.add(b);
				map.put(Double.parseDouble(b.getStars()), current);
			}else{
				ArrayList<Business> toAdd= new ArrayList<>();
				toAdd.add(b);
				map.put(Double.parseDouble(b.getStars()), toAdd);
			}
		}
		return map;

	}

	public static TreeNode createSubTree(ArrayList<Business> business) {

		for (Business b : business) {
			TreeNode store = new TreeNode(Double.parseDouble(b.getPostalCode()), null, null, null);
			formTree( root,store);
		}
		return null;
	}

	
	public static void InsertIntoTree(HashMap<Double, ArrayList<Business> > map){
		for(double d: map.keySet()){
			TreeNode ref=createSubTree(map.get(d));
			TreeNode toInsert= new TreeNode(d,ref,null,null);
			formTree(root,toInsert);
		}


	}

///	insert treenode into tree
	public static TreeNode formTree(TreeNode root, TreeNode toInsert)
	{

		if (root == null)
		{
			root=toInsert;
			return root;
		}
		if (toInsert.getValue() < root.getValue())
			root.setLeft(formTree(root.getLeft(), toInsert));
		else if (toInsert.getValue() > root.getValue())
			root.setRight(formTree(root.getRight(), toInsert));

		/* return the (unchanged) node pointer */
		return root;
	}


}
