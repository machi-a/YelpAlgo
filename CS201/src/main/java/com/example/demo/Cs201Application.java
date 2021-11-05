package com.example.demo;

import com.example.demo.business.Business;
import com.example.demo.reader.ReadToArray;
import com.example.demo.hashmap.Hashmap;

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
		Hashmap hashmap = new Hashmap(filepath);
		long endTime = System.nanoTime();
		long duration = ((endTime - startTime)/1000);  //divide by 1000000 to get milliseconds.
		System.out.println("The HashMap took " + duration + " μs to complete storing.");

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
		hashmap.filterAllKeySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000); 
		System.out.println("HashMap with key set took " + duration + " μs to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		hashmap.filterAllEntrySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000); 
		System.out.println("HashMap with entry set took " + duration + " μs to filter for test case " + testCase + ".");


		hashmap.filterAllEntryIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000); 
		System.out.println("HashMap with entry Iteration took " + duration + " μs to filter for test case " + testCase + ".");


		hashmap.filterAllKeyIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000); 
		System.out.println("HashMap with key Iteration took " + duration + " μs to filter for test case " + testCase + ".");


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
		hashmap.filterAllKeySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000); 
		System.out.println("HashMap with key set took " + duration + " μs to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		hashmap.filterAllEntrySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000); 
		System.out.println("HashMap with entry set took " + duration + " μs to filter for test case " + testCase + ".");


		hashmap.filterAllEntryIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000); 
		System.out.println("HashMap with entry Iteration took " + duration + " μs to filter for test case " + testCase + ".");


		hashmap.filterAllKeyIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000); 
		System.out.println("HashMap with key Iteration took " + duration + " μs to filter for test case " + testCase + ".");


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
		hashmap.filterAllKeySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000); 
		System.out.println("Chained HashMap with key set took " + duration + " μs to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		hashmap.filterAllEntrySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000); 
		System.out.println("Chained HashMap with entry set took " + duration + " μs to filter for test case " + testCase + ".");


		hashmap.filterAllEntryIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000); 
		System.out.println("Chained HashMap with entry Iteration took " + duration + " μs to filter for test case " + testCase + ".");


		hashmap.filterAllKeyIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000); 
		System.out.println("Chained HashMap with key Iteration took " + duration + " μs to filter for test case " + testCase + ".");
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
		hashmap.filterAllKeySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000); 
		System.out.println("Chained HashMap with key set took " + duration + " μs to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		hashmap.filterAllEntrySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000); 
		System.out.println("Chained HashMap with entry set took " + duration + " μs to filter for test case " + testCase + ".");


		hashmap.filterAllEntryIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000); 
		System.out.println("Chained HashMap with entry Iteration took " + duration + " μs to filter for test case " + testCase + ".");


		hashmap.filterAllKeyIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000); 
		System.out.println("Chained HashMap with key Iteration took " + duration + " μs to filter for test case " + testCase + ".");
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
		hashmap.filterAllKeySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000); 
		System.out.println("Chained HashMap with key set took " + duration + " μs to filter for test case " + testCase + ".");

		startTime = System.nanoTime();
		hashmap.filterAllEntrySet(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000); 
		System.out.println("Chained HashMap with entry set took " + duration + " μs to filter for test case " + testCase + ".");


		hashmap.filterAllEntryIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000); 
		System.out.println("Chained HashMap with entry Iteration took " + duration + " μs to filter for test case " + testCase + ".");


		hashmap.filterAllKeyIter(minStars, minReviews, state, city);
		endTime = System.nanoTime();
		duration = ((endTime - startTime)/1000); 
		System.out.println("Chained HashMap with key Iteration took " + duration + " μs to filter for test case " + testCase + ".");


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

