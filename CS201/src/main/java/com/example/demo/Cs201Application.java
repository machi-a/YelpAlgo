package com.example.demo;

import com.example.demo.business.Business;
import com.example.demo.reader.ReadToArray;
import com.example.demo.multitrees.Filter;
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
		Hashmap hashmap= new Hashmap();

		ReadToArray fileReader = new ReadToArray();
		ArrayList<Business> businessList = fileReader.readFile("CS201/src/main/resources/yelp_academic_dataset_business.json");

		// for testing
		//System.out.println("first line of expected output: ");
		//System.out.println("[businessId: 6iYb2HFDywm3zjuRg0shjw, name: Oskar Blues Taproom, city: Boulder, state: CO, latitude: 40.0175444, longitude: -105.2833481, stars: 4.0, review count: 86]");
		//System.out.println(businessList);

		/*
		Test case 1:
		stars - 4.0 and above
		no. of reviews: 100 and above
		state - OR
		city - Portland
		*/
		System.out.println("Test 1: ");
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

		System.out.println(fitsCriteriaList);
		System.out.println("This method took " + Test1duration_MultiTree + "ns to run.");

		System.out.println("Test 3: HashMap Version - key set");
		fitsCriteriaList = hashmap.filterAllKeySet(requiredStars, requiredReviewCount, state, city);
		endTime = System.nanoTime();
		System.out.println("Duration taken "+ (endTime-startTime));

		System.out.println("Test 3: HashMap Version - entry set");
		fitsCriteriaList = hashmap.filterAllEntrySet(requiredStars, requiredReviewCount, state, city);
		endTime = System.nanoTime();
		System.out.println("Duration taken "+ (endTime-startTime));

		System.out.println("Test 3: HashMap Version - entry iter");
		fitsCriteriaList = hashmap.filterAllEntryIter(requiredStars, requiredReviewCount, state, city);
		endTime = System.nanoTime();
		System.out.println("Duration taken "+ (endTime-startTime));

		System.out.println("Test 3: HashMap Version - key iter");
		fitsCriteriaList = hashmap.filterAllKeyIter(requiredStars, requiredReviewCount, state, city);
		endTime = System.nanoTime();
		System.out.println("Duration taken "+ (endTime-startTime));

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



//		 // Hashmap (Li)
//		 startTime = System.nanoTime();
//		 // call ur method
//		 fitsCriteriaList = hashmap.filterAllKeySet(requiredStars, requiredReviewCount, state, city);
//		 endTime = System.nanoTime();
//		 long Test1duration_Hashmap = (endTime - startTime);  //divide by 1000000 to get milliseconds.
//
//		 System.out.println(); // list
//		 System.out.println("This method took " + Test1duration_Hashmap + "ns to run.");




// --------------------TEST CASE 2----------------------------
		/*
		Test case 2:
		stars - 4.5 and above
		no. of reviews: 20 and above
		state - CO
		city - null
		*/
		System.out.println("Test 2: ");
		requiredStars = 4.0f;
		requiredReviewCount = 20;
		state = "CO";
		city = null;

		// multitree (Jasmine)
		startTime = System.nanoTime();
		fitsCriteriaList = filter.fitsAllList(requiredStars, requiredReviewCount, state, city);
		endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;

		System.out.println(fitsCriteriaList);
		System.out.println("This method took " + duration + "ms to run.");

		/*
		Test case 3:
		stars - null
		no. of reviews: 120 and above
		state - null
		city - null
		*/

		startTime = System.nanoTime();
		System.out.println("Test 3: ");
		requiredStars = null;
		requiredReviewCount = 120;
		state = null;
		city = null;

		// multitree (Jasmine)
		startTime = System.nanoTime();
		fitsCriteriaList = filter.fitsAllList(requiredStars, requiredReviewCount, state, city);
		endTime = System.nanoTime();
		duration = (endTime - startTime)/1000000;

		System.out.println(fitsCriteriaList);
		System.out.println("This method took " + duration + "ms to run.");
	}

	public static void fileSizeCalculator(Object obj){
		try {
			//Saving of object in a file
			String path = "/Users/Li/Desktop/201Proj/test1";
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