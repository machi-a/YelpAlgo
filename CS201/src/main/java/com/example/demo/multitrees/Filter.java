package com.example.demo.multitrees;

import com.example.demo.business.Business;
import com.example.demo.multitrees.FeatureTree;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class Filter {
    private FeatureTree trees;
    // private String state;
    // private String city;
    // private Float stars;
    // private int reviewCount;

    public Filter() {
        this.trees = new FeatureTree();
    }

    public ArrayList<Business> fitsStarsList(Float requiredStars) {
        ArrayList<Business> fitsStarsList = new ArrayList<Business>();
        Map<Float, ArrayList<Business>> starsMap = trees.createStarsTreeMap();

        for (Entry<Float, ArrayList<Business>> entry : starsMap.entrySet()) {
            if (entry.getKey() >= requiredStars) {
                for (Business b: entry.getValue()) {
                    fitsStarsList.add(b);
                }
            }
        }
        return fitsStarsList;
    }

    public ArrayList<Business> fitsReviewCountList(int requiredReviewCount) {
        ArrayList<Business> fitsReviewCountList = new ArrayList<Business>();
        Map<Integer, ArrayList<Business>> reviewCountMap = trees.createReviewCountTreeMap();

        for (Entry<Integer, ArrayList<Business>> entry : reviewCountMap.entrySet()) {
            if (entry.getKey() >= requiredReviewCount) {
                for (Business b: entry.getValue()) {
                    fitsReviewCountList.add(b);
                }
            }
        }
        return fitsReviewCountList;
    }

    public ArrayList<Business> fitsStateCityList(String requiredState, String requiredCity) {
        ArrayList<Business> fitsStateCityList = new ArrayList<Business>();
        Map<String, Map<String, ArrayList<Business>>> stateCityMap = trees.createStateCityTreeMap();
        
        for (Entry<String, Map<String, ArrayList<Business>>> stateEntry : stateCityMap.entrySet()) {
            if (stateEntry.getKey().equals(requiredState)) {
                for (Entry<String, ArrayList<Business>> cityEntry : stateEntry.getValue().entrySet()) {
                    if (cityEntry.getKey().equals(requiredCity)) {
                        for (Business b: cityEntry.getValue()) {
                            fitsStateCityList.add(b);
                        }
                    }
                }
            }
        }
        return fitsStateCityList;
    }

    public ArrayList<Business> fitsStarsAndReviewCountList(Float requiredStars, int requiredReviewCount) {
        ArrayList<Business> fitsStarsList = fitsStarsList(requiredStars);
        ArrayList<Business> fitsReviewCountList = fitsReviewCountList(requiredReviewCount);
        
        ArrayList<Business> fitsStarsAndReviewCountList = new ArrayList<Business>();
        for (Business b : fitsStarsList) {
            if (fitsReviewCountList.contains(b)) {
                fitsStarsAndReviewCountList.add(b);
            }
        }
        return fitsStarsAndReviewCountList;
    }

    public ArrayList<Business> fitsAllList(Float requiredStars, int requiredReviewCount, String requiredState, String requiredCity) {
        ArrayList<Business> fitsStateCityList = fitsStateCityList(requiredState, requiredCity);
        ArrayList<Business> fitsStarsAndReviewCountList = fitsStarsAndReviewCountList(requiredStars, requiredReviewCount);

        ArrayList<Business> fitsAllList = new ArrayList<Business>();
        for (Business b : fitsStateCityList) {
            if (fitsStarsAndReviewCountList.contains(b)) {
                fitsAllList.add(b);
            }
        }
        return fitsAllList;
    }



    // public ArrayList<Business> fitsCriteriaList(String state, String city, Float stars, int reviewCount) {
    //     ArrayList<String> fitsStars;

    //     ArrayList<String> idList = new ArrayList<String>();
    //     for (Entry<Float, ArrayList<String>> entry : starsMap.entrySet()) {
    //         if (entry.getKey() > 4.0) {
    //             for (String id: entry.getValue()) {
    //                 idList.add(id);
    //             }
    //         }
    //     }

    //     System.out.println(idList);

    //     for (Business b: businessList) {
    //         if (idList.contains(b.getBusinessId())) {
    //             System.out.println(b);
    //         }
    //     }
    // }
}
