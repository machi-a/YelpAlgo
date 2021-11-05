
package com.example.demo;

import com.example.demo.avltree.AVLTree;
import com.example.demo.nestedTree.*;
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
//    SpringApplication.run(Cs201Application.class, args);

//		String filepath = "CS201/src/main/resources/yelp_academic_dataset_business.json";
		String filepath = "/Users/charzzzzy/Downloads/yelp_dataset/yelp_academic_dataset_business.json";

//-------------------------Storage Test Case--------------------------------------------------------------------------

		System.out.println("\n---------Storage Test" + "----------");

		long startTime = System.nanoTime();
		Treemapx treemapx = new Treemapx(filepath);
		long endTime = System.nanoTime();
		long duration = ((endTime - startTime)/1000000);  //divide by 1000000 to get milliseconds.
		System.out.println("The Treemap took " + duration + "ms to complete storing.");

		startTime = System.nanoTime();
		CityAndState.LoadNestedTree(filepath);
		// TODO: Charlene add NestedTree creation code here
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);  //divide by 1000000 to get milliseconds.
		System.out.println("The NestedTree took " + duration + "ms to complete storing.");

		startTime = System.nanoTime();
		HybridTree.LoadHybridStructure(filepath);
		// TODO: Charlene add NestedTree creation code here
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);  //divide by 1000000 to get milliseconds.
		System.out.println("The Hybrid Structure took " + duration + "ms to complete storing.");


		startTime = System.nanoTime();
		MultiTreemap2 multiTreemap2 = new MultiTreemap2(filepath); // I think for li's and mine, when we run this we are just creating
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);  //divide by 1000000 to get milliseconds.
		System.out.println("The MultipleTreeMap2 took " + duration + "ms to complete storing.");


		startTime = System.nanoTime();
		AVLTree avlTree = new AVLTree(filepath);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);  //divide by 1000000 to get milliseconds.
		System.out.println("The AVLTree took " + duration + "ms to complete storing.");


		startTime = System.nanoTime();
		Hashmap hashmap = new Hashmap(filepath);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);  //divide by 1000000 to get milliseconds.
		System.out.println("The HashMap took " + duration + "ms to complete storing.");

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
		ArrayList<Business> a1 = Treemapx.searchAll(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);  //divide by 1000000 to get milliseconds.
		System.out.println("Treemap took " + duration + "ms to filter for test case " + testCase + ".");




		startTime = System.nanoTime();
		ArrayList<Business> a2 = multiTreemap2.filterAll(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);  //divide by 1000000 to get milliseconds.
		System.out.println("MultipleTreeMap took " + duration + "ms to filter for test case " + testCase + ".");


		startTime = System.nanoTime();
		ArrayList<Business> a3 = avlTree.filterAll(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);  //divide by 1000000 to get milliseconds.
		System.out.println("AVLTree took " + duration + "ms to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		ArrayList<Business> a4 = hashmap.filterAllKeySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);
		System.out.println("HashMap with key set took " + duration + "ms to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		ArrayList<Business> a5 = hashmap.filterAllEntrySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);
		System.out.println("HashMap with entry set took " + duration + "ms to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		ArrayList<Business> a6 = hashmap.filterAllEntryIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);
		System.out.println("HashMap with entry Iteration took " + duration + "ms to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		ArrayList<Business> a7 = hashmap.filterAllKeyIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);
		System.out.println("HashMap with key Iteration took " + duration + "ms to filter for test case " + testCase + ".");
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
		ArrayList<Business> b1 = Treemapx.searchAll(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);  //divide by 1000000 to get milliseconds.
		System.out.println("Treemap took " + duration + "ms to filter for test case " + testCase + ".");


		startTime = System.nanoTime();
		ArrayList<Business> b2 = multiTreemap2.filterAll(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);  //divide by 1000000 to get milliseconds.
		System.out.println("MultipleTreeMap took " + duration + "ms to filter for test case " + testCase + ".");


		startTime = System.nanoTime();
		ArrayList<Business> b3 = avlTree.filterAll(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);  //divide by 1000000 to get milliseconds.
		System.out.println("AVLTree took " + duration + "ms to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		ArrayList<Business> b4 = hashmap.filterAllKeySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);
		System.out.println("HashMap with key set took " + duration + "ms to filter for test case " + testCase + ".");



		startTime = System.nanoTime();
		ArrayList<Business> b5 = hashmap.filterAllEntrySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);
		System.out.println("HashMap with entry set took " + duration + "ms to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		ArrayList<Business> b6 = hashmap.filterAllEntryIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);
		System.out.println("HashMap with entry Iteration took " + duration + "ms to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		ArrayList<Business> b7 = hashmap.filterAllKeyIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);
		System.out.println("HashMap with key Iteration took " + duration + "ms to filter for test case " + testCase + ".");



//-------------------------Filter Test Case 3--------------------------------------------------------------------------

		/*
		Test case 3:
		stars - 0 and above
		no. of reviews: 100 and above
		state - OR
		city - null
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
		ArrayList<Business> c1 = Treemapx.searchAll(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);  //divide by 1000000 to get milliseconds.
		System.out.println("Treemap took " + duration + "ms to filter for test case " + testCase + ".");


		startTime = System.nanoTime();
		ArrayList<Business> c2 = multiTreemap2.filterAll(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);  //divide by 1000000 to get milliseconds.
		System.out.println("MultipleTreeMap took " + duration + "ms to filter for test case " + testCase + ".");


		startTime = System.nanoTime();
		ArrayList<Business> c3 = avlTree.filterAll(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);  //divide by 1000000 to get milliseconds.
		System.out.println("AVLTree took " + duration + "ms to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		ArrayList<Business> c4 = hashmap.filterAllKeySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);
		System.out.println("HashMap with key set took " + duration + "ms to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		ArrayList<Business> c5 = hashmap.filterAllEntrySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);
		System.out.println("HashMap with entry set took " + duration + "ms to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		ArrayList<Business> c6 = hashmap.filterAllEntryIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);
		System.out.println("HashMap with entry Iteration took " + duration + "ms to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		ArrayList<Business> c7 = hashmap.filterAllKeyIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);
		System.out.println("HashMap with key Iteration took " + duration + "ms to filter for test case " + testCase + ".");




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
		state = "CO";
		city = "Boulder";

		System.out.println("\n---------Test Case " + testCase + "---------");
		System.out.println("Features: 1");
		System.out.println("minStars: " + minStars);
		System.out.println("minReviews:  " + minReviews);
		System.out.println("state: " + state);
		System.out.println("city: " + city + "\n");

		startTime = System.nanoTime();
		ArrayList<Business> d1 = Treemapx.searchAll(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);  //divide by 1000000 to get milliseconds.
		System.out.println("Treemap took " + duration + "ms to filter for test case " + testCase + ".");


		startTime = System.nanoTime();
		ArrayList<Business> d2 = multiTreemap2.filterAll(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);  //divide by 1000000 to get milliseconds.
		System.out.println("MultipleTreeMap took " + duration + "ms to filter for test case " + testCase + ".");


		startTime = System.nanoTime();
		ArrayList<Business> d3 = avlTree.filterAll(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);  //divide by 1000000 to get milliseconds.
		System.out.println("AVLTree took " + duration + "ms to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		ArrayList<Business> d4 = hashmap.filterAllKeySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);
		System.out.println("HashMap with key set took " + duration + "ms to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		ArrayList<Business> d5 = hashmap.filterAllEntrySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);
		System.out.println("HashMap with entry set took " + duration + "ms to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		ArrayList<Business> d6 = hashmap.filterAllEntryIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);
		System.out.println("HashMap with entry Iteration took " + duration + "ms to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		ArrayList<Business> d7 = hashmap.filterAllKeyIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);
		System.out.println("HashMap with key Iteration took " + duration + "ms to filter for test case " + testCase + ".");

		//-------------------------Filter Test Case 5--------------------------------------------------------------------------

	/*
	Test case 4:
	stars - 4.0 and above
	no. of reviews: 100 and above
	state - OR
	city - Portland
	*/
		testCase++;
		minStars = 0.0f;
		minReviews = 0;
		state = "WA";
		city = "Vancouver";

		System.out.println("\n---------Test Case " + testCase + "---------");
		System.out.println("Features: 1");
		System.out.println("minStars: " + minStars);
		System.out.println("minReviews:  " + minReviews);
		System.out.println("state: " + state);
		System.out.println("city: " + city + "\n");


		startTime = System.nanoTime();
		// TODO: Charis add TreeMap filter code here
//		System.out.println(CityAndState.searchByCityAndState(CityAndState.root, state, city));
		CityAndState.searchByCityAndState(CityAndState.root, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);   //divide by 1000000 to get milliseconds.
		System.out.println("Nested Tree took " + duration + "ms to filter for test case " + testCase + ".");




		startTime = System.nanoTime();
//		System.out.println(HybridTree.searchByCityAndState(HybridTree.hybridStructure,state,city));
		HybridTree.searchByCityAndState(HybridTree.hybridStructure,state,city);
		// TODO: Charis add TreeMap filter code here
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);   //divide by 1000000 to get milliseconds.
		System.out.println("Hybrid structure took " + duration + "ms to filter for test case " + testCase + ".");



		startTime = System.nanoTime();
		// TODO: Jasmine add MultipleTreeMap filter code here
		multiTreemap2.filterAll(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);   //divide by 1000000 to get milliseconds.
		System.out.println("MultipleTreeMap took " + duration + "ms to filter for test case " + testCase + ".");


		startTime = System.nanoTime();
		avlTree.filterAll(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);   //divide by 1000000 to get milliseconds.
		System.out.println("AVLTree took " + duration + "ms to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		hashmap.filterAllKeySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);
		System.out.println("HashMap with key set took " + duration + "ms to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		hashmap.filterAllEntrySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);
		System.out.println("HashMap with entry set took " + duration + "ms to filter for test case " + testCase + ".");


		hashmap.filterAllEntryIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);
		System.out.println("HashMap with entry Iteration took " + duration + "ms to filter for test case " + testCase + ".");


		hashmap.filterAllKeyIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000000);
		System.out.println("HashMap with key Iteration took " + duration + "ms to filter for test case " + testCase + ".");




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