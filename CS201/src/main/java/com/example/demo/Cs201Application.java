package com.example.demo;

import com.example.demo.business.Business;
import com.example.demo.reader.ReadToArray;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.*;

@SpringBootApplication
public class Cs201Application {
	public static void main(String[] args) {
//		SpringApplication.run(Cs201Application.class, args);

		ReadToArray fileReader = new ReadToArray();
		ArrayList<Business> businessList = fileReader.readFile("CS201/src/main/resources/yelp_academic_dataset_business.json");

		// for testing
		System.out.println("first line of expected output: ");
		System.out.println("[businessId: 6iYb2HFDywm3zjuRg0shjw, name: Oskar Blues Taproom, city: Boulder, state: CO, latitude: 40.0175444, longitude: -105.2833481, stars: 4.0, review count: 86]");
		System.out.println(businessList);
	}
}