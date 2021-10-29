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




}
