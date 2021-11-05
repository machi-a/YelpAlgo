package com.example.demo;

import com.example.demo.avltree.*;
import com.example.demo.NestedTree.*;
import com.example.demo.business.Business;
import com.example.demo.reader.ReadToArray;
import com.example.demo.multitrees.Filter;
import com.example.demo.hashmap.Hashmap;
import com.example.demo.treemap.Treemapx;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@SpringBootApplication
public class Cs201Application {
	static long startTime = 0;
	static long endTime = 0;
	public static void main(String[] args) {
		String filepath = "CS201/src/main/resources/yelp_academic_dataset_business.json";
		Hashmap hashmap= new Hashmap();


		System.out.println("Test 1: Requirements, min 4 stars, min 100 reviews, in OR, Portland");
		Float requiredStars = 4.0f;
		int requiredReviewCount = 100;
		String state = "OR";
		String city = "Portland";

		//multitree (Jasmine)
		long startTime = System.nanoTime();
		Filter filter = new Filter();
		List<Business> fitsCriteriaList = filter.fitsAllList(requiredStars, requiredReviewCount, state, city);
		long endTime = System.nanoTime();
		long Test1duration_MultiTree = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("This method took " + Test1duration_MultiTree + "ns to run.");

		// // treemap (Charis)
		// startTime = System.nanoTime();
		// // call ur method
		// endTime = System.nanoTime();
		// long Test1duration_Treemap = (endTime - startTime);  //divide by 1000000 to get milliseconds.

		// System.out.println(); // list 
		// System.out.println("This method took " + Test1duration_Treemap + "ns to run.");

		// // AVL (Jye Yi)
		// startTime = System.nanoTime();
		// // call ur method
		// endTime = System.nanoTime();
		// long Test1duration_AVLTree = (endTime - startTime);  //divide by 1000000 to get milliseconds.

		// System.out.println(); // list 
		// System.out.println("This method took " + Test1duration_AVLTree + "ns to run.");

		//Hashmap (Li)
		System.out.println("Test 1: HashMap Version - key set");
		fitsCriteriaList = hashmap.filterAllKeySet(requiredStars, requiredReviewCount, state, city);
		endTime = System.nanoTime();
		System.out.println("This method took "+ (endTime-startTime) + "ns to run.");

		System.out.println("Test 1: HashMap Version - entry set");
		fitsCriteriaList = hashmap.filterAllEntrySet(requiredStars, requiredReviewCount, state, city);
		endTime = System.nanoTime();
		System.out.println("This method took "+ (endTime-startTime) + "ns to run.");

		System.out.println("Test 1: HashMap Version - entry iter");
		fitsCriteriaList = hashmap.filterAllEntryIter(requiredStars, requiredReviewCount, state, city);
		endTime = System.nanoTime();
		System.out.println("This method took "+ (endTime-startTime) + "ns to run.");

		System.out.println("Test 1: HashMap Version - key iter");
		fitsCriteriaList = hashmap.filterAllKeyIter(requiredStars, requiredReviewCount, state, city);
		endTime = System.nanoTime();
		System.out.println("This method took "+ (endTime-startTime) + "ns to run.");



// --------------------TEST CASE 2----------------------------
		System.out.println("-----------------------------------------------------------------");
		System.out.println("Test 2: Requirements min 4 stars, min 20 reviews, from state CO  ");
		requiredStars = 4.0f;
		requiredReviewCount = 20;
		state = "CO";
		city = null;

		// multitree (Jasmine)
		startTime = System.nanoTime();
		fitsCriteriaList = filter.fitsAllList(requiredStars, requiredReviewCount, state, city);
		endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println("This method took " + duration + "ns to run.");

		//Hashmap (Li)
		System.out.println("Test 2: HashMap Version - key set");
		fitsCriteriaList = hashmap.filterAllKeySet(requiredStars, requiredReviewCount, state, city);
		endTime = System.nanoTime();
		System.out.println("This method took "+ (endTime-startTime) + "ns to run.");

		System.out.println("Test 2: HashMap Version - entry set");
		fitsCriteriaList = hashmap.filterAllEntrySet(requiredStars, requiredReviewCount, state, city);
		endTime = System.nanoTime();
		System.out.println("This method took "+ (endTime-startTime) + "ns to run.");

		System.out.println("Test 2: HashMap Version - entry iter");
		fitsCriteriaList = hashmap.filterAllEntryIter(requiredStars, requiredReviewCount, state, city);
		endTime = System.nanoTime();
		System.out.println("This method took "+ (endTime-startTime) + "ns to run.");

		System.out.println("Test 2: HashMap Version - key iter");
		fitsCriteriaList = hashmap.filterAllKeyIter(requiredStars, requiredReviewCount, state, city);
		endTime = System.nanoTime();
		System.out.println("This method took "+ (endTime-startTime) + "ns to run.");


// --------------------TEST CASE 3----------------------------

		System.out.println("-----------------------------------------------------------------");
		System.out.println("Test 3: Requirements : 120 reviews");
		requiredStars = null;
		requiredReviewCount = 120;
		state = null;
		city = null;

		// multitree (Jasmine)
		startTime = System.nanoTime();
		fitsCriteriaList = filter.fitsAllList(requiredStars, requiredReviewCount, state, city);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("This method took " + duration + "ns to run.");

		//Hashmap (Li)
		System.out.println("Test 3: HashMap Version - key set");
		fitsCriteriaList = hashmap.filterAllKeySet(requiredStars, requiredReviewCount, state, city);
		endTime = System.nanoTime();
		System.out.println("This method took "+ (endTime-startTime) + "ns to run.");

		System.out.println("Test 3: HashMap Version - entry set");
		fitsCriteriaList = hashmap.filterAllEntrySet(requiredStars, requiredReviewCount, state, city);
		endTime = System.nanoTime();
		System.out.println("This method took "+ (endTime-startTime) + "ns to run.");

		System.out.println("Test 3: HashMap Version - entry iter");
		fitsCriteriaList = hashmap.filterAllEntryIter(requiredStars, requiredReviewCount, state, city);
		endTime = System.nanoTime();
		System.out.println("This method took "+ (endTime-startTime) + "ns to run.");

		System.out.println("Test 3: HashMap Version - key iter");
		fitsCriteriaList = hashmap.filterAllKeyIter(requiredStars, requiredReviewCount, state, city);
		endTime = System.nanoTime();
		System.out.println("This method took "+ (endTime-startTime) + "ns to run.");


// --------------------Nested Trees, AVL Trees, Hybrid----------------------------
		/*
		Comparing between Nested Tree, Nested Tree with AVL and Hybrid structure
		Hybrid structure is a hashmap with an AVL tree
		*/
//		System.out.println("Test 3: ");
//
//		state = "WA";
//		city = "Vancouver";
//
//		System.out.println("Option #1 Using nested tree: ");
//		// multitree (Jasmine)
//		long startTimeCreate = System.nanoTime();
//		CityAndState.LoadNestedTree(businessList);
//		long endTimeCreate = System.nanoTime();
//		startTime = System.nanoTime();
//		CityAndState.searchByCityAndState(CityAndState.root,state,city);
//		endTime = System.nanoTime();
//		duration = (endTime - startTime)/1000000;
//
//		System.out.println("This method took " + duration + "ms to run.");
//
//
//		System.out.println("Option #2 Using nested AVL tree: ");
//		// multitree (Jasmine)
//		startTimeCreate = System.nanoTime();
//		CityAndStateAVL.LoadNestedTree(businessList);
//		endTimeCreate = System.nanoTime();
//		startTime = System.nanoTime();
//		CityAndStateAVL.searchByCityAndState(CityAndStateAVL.root,state,city);
//		endTime = System.nanoTime();
//		duration = (endTime - startTime)/1000000;
//
//		System.out.println("This method took " + duration + "ms to run.");
//
//		System.out.println("Option #2 Using Hybrid Structure: ");
//		// multitree (Jasmine)
//		startTimeCreate = System.nanoTime();
//		HashMap< String , SubTreeNode> hybridStructure=HybridTree.LoadHybridStructure(businessList);
//		endTimeCreate = System.nanoTime();
//		startTime = System.nanoTime();
//		HybridTree.searchByCityAndState(hybridStructure,state,city);
//		endTime = System.nanoTime();
//		duration = (endTime - startTime)/1000000;
//
//		System.out.println("This method took " + duration + "ms to run.");


		AVLTree avlTree = new AVLTree(filepath);
		System.out.println(avlTree.filterAll(1.50F, 0, "MA", null));
	}

	public static void fileSizeCalculator(Object obj){
		try {
			//Saving of object in a file
			String path = "CS201/src/main/resources/Test1";
			FileOutputStream file = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(file);

			// Method for serialization of object
			out.writeObject(obj); // object to serialise

			out.close();
			file.close();

			long Test1Size_Hashmap = Files.size(Paths.get(path));

			System.out.println("Test1_Hashmap: Object has been serialized");
			System.out.println("Size of file: " + Test1Size_Hashmap + "bytes");
		} catch(IOException ex) {
			System.out.println("Test1_Hashmap: IOException is caught");
		}
	}




}
