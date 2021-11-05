package com.example.demo.multitrees;

import com.example.demo.business.Business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class MultiTreemap2 implements Serializable { // creates all treemap once initialised
    private static final long serialVersionUID = 1L;
    private FeatureTree trees;
    private Map<Float, ArrayList<Business>> starsTreemap;
    private Map<Integer, ArrayList<Business>> reviewCountTreemap;
    private Map<String, ArrayList<Business>> stateTreemap;
    private Map<String, Map<String, ArrayList<Business>>> stateCityTreemap;

    public MultiTreemap2(String filepath) {
        this.trees = new FeatureTree(filepath);
        starsTreemap = trees.createStarsTreeMap();
        reviewCountTreemap = trees.createReviewCountTreeMap();
        stateTreemap = trees.createStateTreeMap();
        stateCityTreemap = trees.createStateCityTreeMap();
    }

    public ArrayList<Business> filterAll(Float minStar, int minReview, String state, String city) {
        ArrayList<Business> toReturn = new ArrayList<Business>();
        ArrayList<Business> fitsStarsList;
        ArrayList<Business> fitsReviewCountList;
        ArrayList<Business> fitsStateList;
        ArrayList<Business> fitsStateCityList;

        if (minStar > 0) {
            fitsStarsList = new ArrayList<Business>();
            for (Entry<Float, ArrayList<Business>> entry : starsTreemap.entrySet()) {
                if (entry.getKey() >= minStar) {
                    for (Business b: entry.getValue()) {
                        fitsStarsList.add(b);
                    }
                }
            }
        } else {
            fitsStarsList = null;
        }

        if (minReview > 0) {
            fitsReviewCountList = new ArrayList<Business>();
            for (Entry<Integer, ArrayList<Business>> entry : reviewCountTreemap.entrySet()) {
                if (entry.getKey() >= minReview) {
                    for (Business b: entry.getValue()) {
                        fitsReviewCountList.add(b);
                    }
                }
            }
        } else {
            fitsReviewCountList = null;
        }

        if (state != null && city == null) {
            fitsStateList = new ArrayList<Business>();
            fitsStateCityList = null;
            for (Entry<String, ArrayList<Business>> entry : stateTreemap.entrySet()) {
                if (entry.getKey().equals(state)) {
                    for (Business b: entry.getValue()) {
                        fitsStateList.add(b);
                    }
                }
            }
        } else if (state != null && city != null) {
            fitsStateList = null;
            fitsStateCityList = new ArrayList<Business>();
            for (Entry<String, Map<String, ArrayList<Business>>> stateEntry : stateCityTreemap.entrySet()) {
                if (stateEntry.getKey().equals(state)) {
                    for (Entry<String, ArrayList<Business>> cityEntry : stateEntry.getValue().entrySet()) {
                        if (cityEntry.getKey().equals(city)) {
                            for (Business b: cityEntry.getValue()) {
                                fitsStateCityList.add(b);
                            }
                        }
                    }
                }
            }
        } else {
            fitsStateList = null;
            fitsStateCityList = null;
        }

        if (minStar > 0 && minReview == 0) {
            if (state != null && city == null) {
                for (Business b : fitsStateList) {
                    if (fitsStarsList.contains(b)) {
                        toReturn.add(b);
                    }
                }
                return toReturn;
            } else if (state != null && city != null) {
                for (Business b : fitsStateCityList) {
                    if (fitsStarsList.contains(b)) {
                        toReturn.add(b);
                    }
                }
                return toReturn;
            } else {
                toReturn = fitsStarsList;
            }
        } else if (minStar == 0 && minReview > 0) {
            if (state != null && city == null) {
                for (Business b : fitsStateList) {
                    if (fitsReviewCountList.contains(b)) {
                        toReturn.add(b);
                    }
                }
                return toReturn;
            } else if (state != null && city != null) {
                for (Business b : fitsStateCityList) {
                    if (fitsReviewCountList.contains(b)) {
                        toReturn.add(b);
                    }
                }
                return toReturn;
            } else {
                toReturn = fitsReviewCountList;
            }
        } else if (minStar > 0 && minReview > 0) {
            ArrayList<Business> fitsStarsAndReviewCountList = new ArrayList<Business>();
            for (Business b : fitsStarsList) {
                if (fitsReviewCountList.contains(b)) {
                    fitsStarsAndReviewCountList.add(b);
                }
            }
            if (state != null && city == null) {
                for (Business b : fitsStateList) {
                    if (fitsStarsAndReviewCountList.contains(b)) {
                        toReturn.add(b);
                    }
                }
                return toReturn;
            } else if (state != null && city != null) {
                for (Business b : fitsStateCityList) {
                    if (fitsStarsAndReviewCountList.contains(b)) {
                        toReturn.add(b);
                    }
                }
                return toReturn;
            } else {
                toReturn = fitsStarsAndReviewCountList;
            }
        } else { // no required stars nor reviewcount 
            if (state != null && city == null) {
                return fitsStateList;
            } else if (state != null && city != null) {
                return fitsStateCityList;
            }
        }
        return toReturn;
    }
}
