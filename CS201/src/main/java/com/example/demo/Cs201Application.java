package com.example.demo;

import com.example.demo.avltree.AVLTree;
import com.example.demo.nestedtree.*;
import com.example.demo.multitrees.MultiTreemap;
import com.example.demo.multitrees.MultiTreemap2;
import com.example.demo.hashmap.Hashmap;
import com.example.demo.treemap.Treemapx;
import java.util.*;
import com.example.demo.business.*;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


@SpringBootApplication
public class Cs201Application {
	static long startTime = 0;
	static long endTime = 0;
	public static void main(String[] args) {
//		SpringApplication.run(Cs201Application.class, args);

		String filepath = "CS201/src/main/resources/yelp_academic_dataset_business.json";
		String filepathBIG = "/Users/jasminequek/Desktop/CS201 Data/project/code/actual/yelp_academic_dataset_business.json";

//-------------------------Storage Test Case--------------------------------------------------------------------------

		System.out.println("\n---------Storage Test" + "---------");

		long startTime = System.nanoTime();
		// TODO: Charis add TreeMap creation code here
		Treemapx treemapx= new Treemapx(filepath);
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
		MultiTreemap2 multiTreemap2 = new MultiTreemap2(filepath); // I think for li's and mine, when we run this we are just creating 
		endTime = System.nanoTime();
		duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("The MultipleTreeMap2 took " + duration + "ns to complete storing.");

		fileSizeCalculator(multiTreemap2);

		// startTime = System.nanoTime();
		// // TODO: Jasmine add MultipleTreeMap creation code here
		// MultiTreemap multiTreemap = new MultiTreemap(filepath); // I think for li's and mine, when we run this we are just creating 
		// endTime = System.nanoTime();
		// duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		// System.out.println("The MultipleTreeMap took " + duration + "ns to complete storing.");

		startTime = System.nanoTime();
		AVLTree avlTree = new AVLTree(filepath);
		endTime = System.nanoTime();
		duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("The AVLTree took " + duration + "ns to complete storing.");

		fileSizeCalculator(avlTree);

		startTime = System.nanoTime();
		Hashmap hashmap = new Hashmap(filepath);
		endTime = System.nanoTime();
		duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("The HashMap took " + duration + "ns to complete storing.");
		
		fileSizeCalculator(hashmap);
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
        ArrayList<Business> l = Treemapx.searchAll(4.0f, 100, "OR", "Portland");
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
		multiTreemap2.filterAll(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("MultipleTreeMap took " + duration + "ns to filter for test case " + testCase + ".");

		System.out.println(multiTreemap2);

		// TESTING OF SPACE COMPLEXITY
		// try {   
		// 	//Saving of object in a file
		// 	String path = "/Users/jasminequek/Desktop/CS201 Data/project/Test1_MultiTreemap";
		// 	FileOutputStream file = new FileOutputStream(path);
		// 	ObjectOutputStream out = new ObjectOutputStream(file);
			
		// 	// Method for serialization of object
		// 	out.writeObject(multiTreemap.filterAll(minStars, minReviews, state, city)); // object to serialise
			
		// 	out.close();
		// 	file.close();

		// 	long Test1Size_MultiTreemap = Files.size(Paths.get(path));
			
		// 	System.out.println("Test1_Treemap: Object has been serialized");
		// 	System.out.println("Size of file: " + Test1Size_MultiTreemap + "bytes");
		// } catch(IOException ex) {
		// 	System.out.println("Test1_Treemap: IOException is caught");
		// }

		// ----------------------------------------------------------------

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
        l = Treemapx.searchAll(4.0f, 100, "OR", null);

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
		multiTreemap2.filterAll(minStars, minReviews, state, city);
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
        l = Treemapx.searchAll(0f, 100, "OR", null);
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
		multiTreemap2.filterAll(minStars, minReviews, state, city);
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
        l = Treemapx.searchAll(1.5f, 0, null, null);
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
		multiTreemap2.filterAll(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("MultipleTreeMap took " + duration + "ns to filter for test case " + testCase + ".");

	// TESTING OF SPACE COMPLEXITY
	try {   
		//Saving of object in a file
		String path = "/Users/jasminequek/Desktop/CS201 Data/project/Test1_MultiTreemap";
		FileOutputStream file = new FileOutputStream(path);
		ObjectOutputStream out = new ObjectOutputStream(file);
		
		// Method for serialization of object
		out.writeObject(multiTreemap2.filterAll(minStars, minReviews, state, city)); // object to serialise
		
		out.close();
		file.close();

		long Test4Size_MultiTreemap = Files.size(Paths.get(path));
		
		System.out.println("Test4_Treemap: Object has been serialized");
		System.out.println("Size of file: " + Test4Size_MultiTreemap + "bytes");
	} catch(IOException ex) {
		System.out.println("Test1_Treemap: IOException is caught");
	}

	// ----------------------------------------------------------------

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