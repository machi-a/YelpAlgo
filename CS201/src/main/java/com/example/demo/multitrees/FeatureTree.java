package com.example.demo.multitrees;

import com.example.demo.business.Business;
import com.example.demo.reader.ReadToArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FeatureTree { 
    private List<Business> businessList;

    public FeatureTree() {
        ReadToArray fileReader = new ReadToArray();
		businessList = fileReader.readFile("CS201/src/main/resources/yelp_academic_dataset_business.json");
    }

    public Map<Float, ArrayList<Business>> createStarsTreeMap() {
        Map<Float, ArrayList<Business>> starsMap = new TreeMap<Float, ArrayList<Business>>();
        for (Business b: businessList) {
            if (!starsMap.containsKey(b.getStars())) {
                ArrayList<Business> bizList = new ArrayList<Business>();
                starsMap.put(b.getStars(), bizList);
                bizList.add(b);
            } else {
                ArrayList<Business> bizList = starsMap.get(b.getStars());
                bizList.add(b);
            }
        }
        return starsMap; 
    }

    public Map<Integer, ArrayList<Business>> createReviewCountTreeMap() {
        Map<Integer, ArrayList<Business>> reviewCountMap = new TreeMap<Integer, ArrayList<Business>>();
        for (Business b: businessList) {
            if (!reviewCountMap.containsKey(b.getReviewCount())) {
                ArrayList<Business> bizList = new ArrayList<Business>();
                reviewCountMap.put(b.getReviewCount(), bizList);
                bizList.add(b);
            } else {
                ArrayList<Business> bizList = reviewCountMap.get(b.getReviewCount());
                bizList.add(b);
            }
        }
        return reviewCountMap; 
    }

    public Map<String, Map<String, ArrayList<Business>>> createStateCityTreeMap() { // nested treemap, 1st layer key: state, second layer key: city
        Map<String, Map<String, ArrayList<Business>>> stateMap = new TreeMap<String, Map<String, ArrayList<Business>>>();

        for (Business b: businessList) {
            if (!stateMap.containsKey(b.getState())) {
                Map<String, ArrayList<Business>> cityMap = new TreeMap<String, ArrayList<Business>>();
                ArrayList<Business> bizList = new ArrayList<Business>();
                stateMap.put(b.getState(), cityMap);
                cityMap.put(b.getCity(), bizList);
                bizList.add(b);
            } else {
                Map<String, ArrayList<Business>> cityMap = stateMap.get(b.getState());
                if (!cityMap.containsKey(b.getCity())) {
                    ArrayList<Business> bizList = new ArrayList<Business>();
                    cityMap.put(b.getCity(), bizList);
                    bizList.add(b);
                } else {
                    ArrayList<Business> bizList = cityMap.get(b.getCity());
                    bizList.add(b);
                }
            }
        }
        return stateMap; 
    }

    // public Map<String, ArrayList<Business>> createStateTreeMap() {
    //     Map<String, ArrayList<Business>> stateMap = new TreeMap<String, ArrayList<Business>>();
    //     for (Business b: businessList) {
    //         if (!stateMap.containsKey(b.getState())) {
    //             ArrayList<Business> bizList = new ArrayList<Business>();
    //             stateMap.put(b.getState(), bizList);
    //             bizList.add(b);
    //         } else {
    //             ArrayList<Business> bizList = stateMap.get(b.getState());
    //             bizList.add(b);
    //         }
    //     }
    //     return stateMap; 
    // }


}

