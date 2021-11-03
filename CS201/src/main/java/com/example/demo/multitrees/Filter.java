package com.example.demo.multitrees;

import com.example.demo.business.Business;
import com.example.demo.multitrees.FeatureTree;

import java.io.Serializable;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Filter implements Serializable {
    private static final long serialVersionUID = 1L;
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

    // public ArrayList<Business> fitsStarsListSubMapImplementation(Float requiredStars) {
    //     TreeMap<Float, ArrayList<Business>> starsMap = trees.createStarsTreeMap();
    //     Map<Float, ArrayList<Business>> fitsStarsMap = starsMap.subMap(requiredStars, true, 5.0f, true);
    //     List<Business> fitsStarsList = new ArrayList<Business>(fitsStarsMap.values());
    //     return fitsStarsList;
    // }

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

    public ArrayList<Business> fitsStateList(String requiredState) {
        ArrayList<Business> fitsStateList = new ArrayList<Business>();
        Map<String, ArrayList<Business>> stateMap = trees.createStateTreeMap();
        
        for (Entry<String, ArrayList<Business>> stateEntry : stateMap.entrySet()) {
            if (stateEntry.getKey().equals(requiredState)) {
                for (Business b: stateEntry.getValue()) {
                    fitsStateList.add(b);
                }
            }
        }
        return fitsStateList;
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

    // err where to call 
    public Boolean validifyRequirements(Float requiredStars, int requiredReviewCount) { // String requiredState, String requiredCity: how to verify
        if (requiredStars < 0 || requiredStars > 5.0) { // exceed range of 0~5.0 
            return false;
        } else if (requiredStars % 0.5 != 0) { // not in denominations of 0.5
            return false;
        }

        if (requiredReviewCount < 0) { // negative
            return false;
        } else if (requiredReviewCount % 1 != 0) { // not an integer, shouldnt come here i think
            return false;
        }
        return true;
    }

    public ArrayList<Business> fitsAllList(Float requiredStars, int requiredReviewCount, String requiredState, String requiredCity) {
        ArrayList<Business> fitsAllList = new ArrayList<Business>();
        
        if (requiredStars != null && requiredReviewCount == 0) {
            ArrayList<Business> fitsStarsList = fitsStarsList(requiredStars);
            if (requiredState != null && requiredCity == null) {
                ArrayList<Business> fitsStateList = fitsStateList(requiredState);
                for (Business b : fitsStateList) {
                    if (fitsStarsList.contains(b)) {
                        fitsAllList.add(b);
                    }
                }
                return fitsAllList;
            } else if (requiredState != null && requiredCity != null) {
                ArrayList<Business> fitsStateCityList = fitsStateCityList(requiredState, requiredCity);
                for (Business b : fitsStateCityList) {
                    if (fitsStarsList.contains(b)) {
                        fitsAllList.add(b);
                    }
                }
                return fitsAllList;
            } else {
                fitsAllList = fitsStarsList;
            }
        } else if (requiredStars == null && requiredReviewCount > 0) {
            ArrayList<Business> fitsReviewCountList = fitsReviewCountList(requiredReviewCount);
            if (requiredState != null && requiredCity == null) {
                ArrayList<Business> fitsStateList = fitsStateList(requiredState);
                for (Business b : fitsStateList) {
                    if (fitsReviewCountList.contains(b)) {
                        fitsAllList.add(b);
                    }
                }
                return fitsAllList;
            } else if (requiredState != null && requiredCity != null) {
                ArrayList<Business> fitsStateCityList = fitsStateCityList(requiredState, requiredCity);
                for (Business b : fitsStateCityList) {
                    if (fitsReviewCountList.contains(b)) {
                        fitsAllList.add(b);
                    }
                }
                return fitsAllList;
            } else {
                fitsAllList = fitsReviewCountList;
            }
        } else if (requiredStars != null && requiredReviewCount > 0) {
            ArrayList<Business> fitsStarsAndReviewCountList = fitsStarsAndReviewCountList(requiredStars, requiredReviewCount);
            if (requiredState != null && requiredCity == null) {
                ArrayList<Business> fitsStateList = fitsStateList(requiredState);
                for (Business b : fitsStateList) {
                    if (fitsStarsAndReviewCountList.contains(b)) {
                        fitsAllList.add(b);
                    }
                }
                return fitsAllList;
            } else if (requiredState != null && requiredCity != null) {
                ArrayList<Business> fitsStateCityList = fitsStateCityList(requiredState, requiredCity);
                for (Business b : fitsStateCityList) {
                    if (fitsStarsAndReviewCountList.contains(b)) {
                        fitsAllList.add(b);
                    }
                }
                return fitsAllList;
            } else {
                fitsAllList = fitsStarsAndReviewCountList;
            }
        } else { // no required stars nor reviewcount 
            if (requiredState != null && requiredCity == null) {
                ArrayList<Business> fitsStateList = fitsStateList(requiredState);
                return fitsStateList;
            } else if (requiredState != null && requiredCity != null) {
                ArrayList<Business> fitsStateCityList = fitsStateCityList(requiredState, requiredCity);
                return fitsStateCityList;
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
