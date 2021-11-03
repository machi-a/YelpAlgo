package com.example.demo;

import com.example.demo.business.Business;
import com.example.demo.reader.ReadToArray;
import com.example.demo.multitrees.Filter;
import com.example.demo.hashmap.Hashmap;

import org.springframework.boot.autoconfigure.SpringBootApplication;
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
		System.out.println("first line of expected output: ");
		System.out.println("[businessId: 6iYb2HFDywm3zjuRg0shjw, name: Oskar Blues Taproom, city: Boulder, state: CO, latitude: 40.0175444, longitude: -105.2833481, stars: 4.0, review count: 86]");
		System.out.println(businessList);

		/*
		Test case 1:
		stars - 4.0 and above
		no. of reviews: 20 and above
		*/
		System.out.println("Test 1: ");
		Float requiredStars = 4.0f;
		int requiredReviewCount = 100;

		Filter filter = new Filter();
		ArrayList<Business> fitsCriteriaList = filter.fitsStarsAndReviewCountList(requiredStars, requiredReviewCount);
		System.out.println(fitsCriteriaList);

		/*
		Test case 2:
		state - OR
		city - Portland
		*/
		System.out.println("Test 2: ");
		String state = "OR";
		String city = "Portland";

		// Filter filter = new Filter();
		fitsCriteriaList = filter.fitsStateCityList(state, city);
		System.out.println(fitsCriteriaList);

		/*
		Test case 3:
		stars - 4.0 and above
		no. of reviews: 20 and above
		state - OR
		city - Portland
		*/

		startTime = System.nanoTime();
		System.out.println("Test 3: ");
		fitsCriteriaList = filter.fitsAllList(requiredStars, requiredReviewCount, state, city);
		System.out.println(fitsCriteriaList);
		endTime = System.nanoTime();
		System.out.println("Duration taken "+ (endTime-startTime));
		startTime = System.nanoTime();

		System.out.println("Test 3: HashMap Version - key set");
		fitsCriteriaList = hashmap.filterAllKeySet(requiredStars, requiredReviewCount, state, city);
		System.out.println(fitsCriteriaList);
		endTime = System.nanoTime();
		System.out.println("Duration taken "+ (endTime-startTime));

		System.out.println("Test 3: HashMap Version - entry set");
		fitsCriteriaList = hashmap.filterAllEntrySet(requiredStars, requiredReviewCount, state, city);
		System.out.println(fitsCriteriaList);
		endTime = System.nanoTime();
		System.out.println("Duration taken "+ (endTime-startTime));

		System.out.println("Test 3: HashMap Version - entry iter");
		fitsCriteriaList = hashmap.filterAllEntryIter(requiredStars, requiredReviewCount, state, city);
		System.out.println(fitsCriteriaList);
		endTime = System.nanoTime();
		System.out.println("Duration taken "+ (endTime-startTime));

		System.out.println("Test 3: HashMap Version - key iter");
		fitsCriteriaList = hashmap.filterAllKeyIter(requiredStars, requiredReviewCount, state, city);
		System.out.println(fitsCriteriaList);
		endTime = System.nanoTime();
		System.out.println("Duration taken "+ (endTime-startTime));
	}
}