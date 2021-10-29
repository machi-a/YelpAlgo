package com.example.demo.reader;

import com.example.demo.business.Business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;

import com.google.gson.Gson;

public class ReadToArray {
    
    public ArrayList<Business> readFile(String filepath){
		ArrayList<Business> businessList = new ArrayList<>();
		try {
			FileReader reader = new FileReader(filepath);

			// System.out.println("FILE");
			BufferedReader bufferedReader = new BufferedReader(reader);

			String currentLine;
			while ((currentLine = bufferedReader.readLine()) != null) {
				Gson gson = new Gson();

				Business business = gson.fromJson(currentLine.toString(), Business.class);
				businessList.add(business);
			}
		} catch (IOException e) {
			System.out.println("No File Found");
        }
		return businessList;
	}
}
