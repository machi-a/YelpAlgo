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
//		SpringApplication.run(Cs201Application.class, args);

		String filepath = "CS201/src/main/resources/yelp_academic_dataset_business.json";

//-------------------------Storage Test Case--------------------------------------------------------------------------

		System.out.println("\n---------Storage Test" + "---------");

		long startTime = System.nanoTime();
		// TODO: Charis add TreeMap creation code here
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("The Treemap took " + duration + "ns to complete storing.");

		startTime = System.nanoTime();
		// TODO: Charlene add NestedTree creation code here
		endTime = System.nanoTime();
		duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("The NestedTree took " + duration + "ns to complete storing.");

		startTime = System.nanoTime();
		// TODO: Jasmine add MultipleTreeMap creation code here
		endTime = System.nanoTime();
		duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("The MultipleTreeMap took " + duration + "ns to complete storing.");

		startTime = System.nanoTime();
		AVLTree avlTree = new AVLTree(filepath);
		endTime = System.nanoTime();
		duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("The AVLTree took " + duration + "ns to complete storing.");

		startTime = System.nanoTime();
		Hashmap hashmap = new Hashmap(filepath);
		endTime = System.nanoTime();
		duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("The HashMap took " + duration + "ns to complete storing.");
//-------------------------Filter Test Case 1--------------------------------------------------------------------------

		/*
		Test case 1:
		stars - 4.0 and above
		no. of reviews: 100 and above
		state - OR
		city - Portland
		*/
		int testCase = 1;
		Float minStars = 4.0f;
		int minReviews = 100;
		String state = "OR";
		String city = "Portland";

		System.out.println("\n---------Test Case " + testCase + "---------");
		System.out.println("Features: 4");
		System.out.println("minStars: " + minStars);
		System.out.println("minReviews:  " + minReviews);
		System.out.println("state: " + state);
		System.out.println("city: " + city + "\n");


		startTime = System.nanoTime();
		// TODO: Charis add TreeMap filter code here
		endTime = System.nanoTime();
		duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("Treemap took " + duration + "ns to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		// TODO: Charlene add NestedTree filter code here
		endTime = System.nanoTime();
		duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("NestedTree took " + duration + "ns to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		// TODO: Jasmine add MultipleTreeMap filter code here
		endTime = System.nanoTime();
		duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("MultipleTreeMap took " + duration + "ns to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		avlTree.filterAll(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("AVLTree took " + duration + "ns to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		hashmap.filterAllKeySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("HashMap with key set took " + duration + "ns to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		hashmap.filterAllEntrySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("HashMap with entry set took " + duration + "ns to filter for test case " + testCase + ".");


		hashmap.filterAllEntryIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("HashMap with entry Iteration took " + duration + "ns to filter for test case " + testCase + ".");


		hashmap.filterAllKeyIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("HashMap with key Iteration took " + duration + "ns to filter for test case " + testCase + ".");
//-------------------------Filter Test Case 2--------------------------------------------------------------------------

		/*
		Test case 2:
		stars - 4.0 and above
		no. of reviews: 100 and above
		state - OR
		city - Portland
		*/
		testCase++;
		minStars = 4.0f;
		minReviews = 100;
		state = "OR";
		city = null;

		System.out.println("\n---------Test Case " + testCase + "---------");
		System.out.println("Features: 3");
		System.out.println("minStars: " + minStars);
		System.out.println("minReviews:  " + minReviews);
		System.out.println("state: " + state);
		System.out.println("city: " + city + "\n");


		startTime = System.nanoTime();
		// TODO: Charis add TreeMap filter code here
		endTime = System.nanoTime();
		duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("Treemap took " + duration + "ns to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		// TODO: Charlene add NestedTree filter code here
		endTime = System.nanoTime();
		duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("NestedTree took " + duration + "ns to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		// TODO: Jasmine add MultipleTreeMap filter code here
		endTime = System.nanoTime();
		duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("MultipleTreeMap took " + duration + "ns to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		avlTree.filterAll(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("AVLTree took " + duration + "ns to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		hashmap.filterAllKeySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("HashMap with key set took " + duration + "ns to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		hashmap.filterAllEntrySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("HashMap with entry set took " + duration + "ns to filter for test case " + testCase + ".");


		hashmap.filterAllEntryIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("HashMap with entry Iteration took " + duration + "ns to filter for test case " + testCase + ".");


		hashmap.filterAllKeyIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("HashMap with key Iteration took " + duration + "ns to filter for test case " + testCase + ".");

		// System.out.println("Calculated Size of file: " + actualMemUsed.byteValue());


//-------------------------Filter Test Case 3--------------------------------------------------------------------------

		/*
		Test case 3:
		stars - 4.0 and above
		no. of reviews: 100 and above
		state - OR
		city - Portland
		*/
		testCase++;
		minStars = 0f;
		minReviews = 100;
		state = "OR";
		city = null;

		System.out.println("\n---------Test Case " + testCase + "---------");
		System.out.println("Features: 2");
		System.out.println("minStars: " + minStars);
		System.out.println("minReviews:  " + minReviews);
		System.out.println("state: " + state);
		System.out.println("city: " + city + "\n");


		startTime = System.nanoTime();
		// TODO: Charis add TreeMap filter code here
		endTime = System.nanoTime();
		duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("Treemap took " + duration + "ns to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		// TODO: Charlene add NestedTree filter code here
		endTime = System.nanoTime();
		duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("NestedTree took " + duration + "ns to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		// TODO: Jasmine add MultipleTreeMap filter code here
		endTime = System.nanoTime();
		duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("MultipleTreeMap took " + duration + "ns to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		avlTree.filterAll(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("AVLTree took " + duration + "ns to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		hashmap.filterAllKeySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("HashMap with key set took " + duration + "ns to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		hashmap.filterAllEntrySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("HashMap with entry set took " + duration + "ns to filter for test case " + testCase + ".");


		hashmap.filterAllEntryIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("HashMap with entry Iteration took " + duration + "ns to filter for test case " + testCase + ".");


		hashmap.filterAllKeyIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("HashMap with key Iteration took " + duration + "ns to filter for test case " + testCase + ".");
//-------------------------Filter Test Case 4--------------------------------------------------------------------------

		/*
		Test case 4:
		stars - 4.0 and above
		no. of reviews: 100 and above
		state - OR
		city - Portland
		*/
		testCase++;
		minStars = 1.5f;
		minReviews = 0;
		state = null;
		city = null;

		System.out.println("\n---------Test Case " + testCase + "---------");
		System.out.println("Features: 1");
		System.out.println("minStars: " + minStars);
		System.out.println("minReviews:  " + minReviews);
		System.out.println("state: " + state);
		System.out.println("city: " + city + "\n");


		startTime = System.nanoTime();
		// TODO: Charis add TreeMap filter code here
		endTime = System.nanoTime();
		duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("Treemap took " + duration + "ns to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		// TODO: Charlene add NestedTree filter code here
		endTime = System.nanoTime();
		duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("NestedTree took " + duration + "ns to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		// TODO: Jasmine add MultipleTreeMap filter code here
		endTime = System.nanoTime();
		duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("MultipleTreeMap took " + duration + "ns to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		avlTree.filterAll(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("AVLTree took " + duration + "ns to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		hashmap.filterAllKeySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("HashMap with key set took " + duration + "ns to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		hashmap.filterAllEntrySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("HashMap with entry set took " + duration + "ns to filter for test case " + testCase + ".");


		hashmap.filterAllEntryIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("HashMap with entry Iteration took " + duration + "ns to filter for test case " + testCase + ".");


		hashmap.filterAllKeyIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("HashMap with key Iteration took " + duration + "ns to filter for test case " + testCase + ".");




		/*
		Test case 1:
		stars - 4.0 and above
		no. of reviews: 100 and above
		state - OR
		city - Portland
		*/
//		System.out.println("Test 1: ");
//		Float requiredStars = 4.0f;
//		int requiredReviewCount = 100;
//		String state = "OR";
//		String city = "Portland";

		//multitree (Jasmine)
//		long startTime = System.nanoTime();
//		Filter filter = new Filter();
//		List<Business> fitsCriteriaList = filter.fitsAllList(minStars, minReviews, state, city);
//		long endTime = System.nanoTime();
//		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
//
//		System.out.println(fitsCriteriaList);
//		System.out.println("This method took " + duration + "ns to run.");
//
//		System.out.println("Test 3: HashMap Version - key set");
//		fitsCriteriaList = hashmap.filterAllKeySet(minStars, minReviews, state, city);
//		endTime = System.nanoTime();
//		System.out.println("Duration taken "+ (endTime-startTime));
//
//		System.out.println("Test 3: HashMap Version - entry set");
//		fitsCriteriaList = hashmap.filterAllEntrySet(minStars, minReviews, state, city);
//		endTime = System.nanoTime();
//		System.out.println("Duration taken "+ (endTime-startTime));
//
//		System.out.println("Test 3: HashMap Version - entry iter");
//		fitsCriteriaList = hashmap.filterAllEntryIter(minStars, minReviews, state, city);
//		endTime = System.nanoTime();
//		System.out.println("Duration taken "+ (endTime-startTime));
//
//		System.out.println("Test 3: HashMap Version - key iter");
//		fitsCriteriaList = hashmap.filterAllKeyIter(minStars, minReviews, state, city);
//		endTime = System.nanoTime();
//		System.out.println("Duration taken "+ (endTime-startTime));

		// // treemap (Charis)
		// startTime = System.nanoTime();
		// // call ur method
		// endTime = System.nanoTime();
		// long Test1duration_Treemap = (endTime - startTime);  //divide by 1000000 to get milliseconds.

		// System.out.println(); // list 
		// System.out.println("This method took " + Test1duration_Treemap + "ns to run.");

		// try {   
		// 	//Saving of object in a file
		// 	String path = "/Users/jasminequek/Desktop/CS201 Data/project/Test1_Treemap";
		// 	FileOutputStream file = new FileOutputStream(path);
		// 	ObjectOutputStream out = new ObjectOutputStream(file);
			
		// 	// Method for serialization of object
		// 	out.writeObject(filter); // object to serialise
			
		// 	out.close();
		// 	file.close();

		// 	long Test1Size_Treemap = Files.size(Paths.get(path));
			
		// 	System.out.println("Test1_Treemap: Object has been serialized");
		// 	System.out.println("Size of file: " + Test1Size_Treemap + "bytes");
		// } catch(IOException ex) {
		// 	System.out.println("Test1_Treemap: IOException is caught");
		// }



		// // AVL (Jye Yi)
		// startTime = System.nanoTime();
		// // call ur method
		// endTime = System.nanoTime();
		// long Test1duration_AVLTree = (endTime - startTime);  //divide by 1000000 to get milliseconds.

		// System.out.println(); // list 
		// System.out.println("This method took " + Test1duration_AVLTree + "ns to run.");

		// try {   
		// 	//Saving of object in a file
		// 	String path = "/Users/jasminequek/Desktop/CS201 Data/project/Test1_AVLTree";
		// 	FileOutputStream file = new FileOutputStream(path);
		// 	ObjectOutputStream out = new ObjectOutputStream(file);
			
		// 	// Method for serialization of object
		// 	out.writeObject(filter); // object to serialise
			
		// 	out.close();
		// 	file.close();

		// 	long Test1Size_AVLTree = Files.size(Paths.get(path));
			
		// 	System.out.println("Test1_AVLTree: Object has been serialized");
		// 	System.out.println("Size of file: " + Test1Size_AVLTree + "bytes");
		// } catch(IOException ex) {
		// 	System.out.println("Test1_AVLTree: IOException is caught");
		// }

// --------------------TEST CASE 2----------------------------
		/*
		Test case 2:
		stars - 4.5 and above
		no. of reviews: 20 and above
		state - CO
		city - null
		*/
//		System.out.println("Test 2: ");
//		requiredStars = 4.0f;
//		requiredReviewCount = 20;
//		state = "CO";
//		city = null;
//
//		// multitree (Jasmine)
//		startTime = System.nanoTime();
//		fitsCriteriaList = filter.fitsAllList(minStars, minReviews, state, city);
//		endTime = System.nanoTime();
//		long duration = (endTime - startTime)/1000000;
//
//		System.out.println(fitsCriteriaList);
//		System.out.println("This method took " + duration + "ms to run.");

		/*
		Test case 3:
		stars - null
		no. of reviews: 120 and above
		state - null
		city - null
		*/

//		startTime = System.nanoTime();
//		System.out.println("Test 3: ");
//		requiredStars = null;
//		requiredReviewCount = 120;
//		state = null;
//		city = null;

		// multitree (Jasmine)
//		startTime = System.nanoTime();
//		fitsCriteriaList = filter.fitsAllList(minStars, minReviews, state, city);
//		endTime = System.nanoTime();
//		duration = (endTime - startTime)/1000000;
//
//		System.out.println(fitsCriteriaList);
//		System.out.println("This method took " + duration + "ms to run.");


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

