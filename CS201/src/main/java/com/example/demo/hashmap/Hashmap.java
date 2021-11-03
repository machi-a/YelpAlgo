package com.example.demo.hashmap;

import com.example.demo.business.Business;
import com.example.demo.reader.ReadToArray;

import java.util.*;

public class Hashmap {
    private static List<Business> businessList;
    private static HashMap<String,Business> bizList;

    public Hashmap(){
        ReadToArray fileReader = new ReadToArray();
        businessList = fileReader.readFile("CS201/src/main/resources/yelp_academic_dataset_business.json");
        bizList = new HashMap<>();
        for (Business b: businessList) {
            bizList.put(b.getBusinessId(), b);
        }

    }

    public static ArrayList<Business> filterAllKeySet(Float reqStars, int reqRC, String reqState, String reqCity){
        ArrayList<Business> retList = new ArrayList<>();
        for (String i : bizList.keySet()) {
            if (bizList.get(i).getStars() >= reqStars && bizList.get(i).getReviewCount() >= reqRC && bizList.get(i).getState().equals(reqState) && bizList.get(i).getCity().equals(reqCity) )
                retList.add(bizList.get(i));
        }
        return  retList;
    }

    public static ArrayList<Business> filterAllEntrySet(Float reqStars, int reqRC, String reqState, String reqCity){
        ArrayList<Business> retList = new ArrayList<>();
        for (Map.Entry<String, Business> entry : bizList.entrySet()) {
            if (entry.getValue().getStars() >= reqStars && entry.getValue().getReviewCount() >= reqRC && entry.getValue().getState().equals(reqState) && entry.getValue().getCity().equals(reqCity) )
                retList.add(entry.getValue());
        }
        return  retList;
    }

    public static ArrayList<Business> filterAllEntryIter(Float reqStars, int reqRC, String reqState, String reqCity){
        ArrayList<Business> retList = new ArrayList<>();
        Iterator<Map.Entry<String,Business>> itr1 = bizList.entrySet().iterator();
        while(itr1.hasNext())
        {
            Map.Entry<String, Business> entry = itr1.next();
            if (entry.getValue().getStars() >= reqStars && entry.getValue().getReviewCount() >= reqRC && entry.getValue().getState().equals(reqState) && entry.getValue().getCity().equals(reqCity) )
                retList.add(entry.getValue());
        }
        return  retList;
    }

    public static ArrayList<Business> filterAllKeyIter(Float reqStars, int reqRC, String reqState, String reqCity){
        ArrayList<Business> retList = new ArrayList<>();
        Iterator itr2 = bizList.keySet().iterator();
        while(itr2.hasNext())
        {
            String i = (String) itr2.next();
            if (bizList.get(i).getStars() >= reqStars && bizList.get(i).getReviewCount() >= reqRC && bizList.get(i).getState().equals(reqState) && bizList.get(i).getCity().equals(reqCity) )
                retList.add(bizList.get(i));
        }
        return  retList;
    }
}
