package com.example.demo.hashmap;

import com.example.demo.business.Business;
import com.example.demo.reader.ReadToArray;

import java.io.Serializable;
import java.util.*;

public class Hashmap implements Serializable {
    private static final long serialVersionUID = 1L;
    private static List<Business> businessList;
    private static HashMap<String,Business> bizList;

    public Hashmap(String filepath){
        ReadToArray fileReader = new ReadToArray();
        businessList = fileReader.readFile(filepath);
        bizList = new HashMap<>();
        for (Business b: businessList) {
            bizList.put(b.getBusinessId(), b);
        }

    }

    public static ArrayList<Business> filterAllKeySet(Float reqStars, int reqRC, String reqState, String reqCity){
        ArrayList<Business> retList = new ArrayList<>();
        if(reqStars == null) reqStars = 0.0f;
        if(reqCity == null && reqState == null){
            for (String i : bizList.keySet()) {
                if (bizList.get(i).getStars() >= reqStars && bizList.get(i).getReviewCount() >= reqRC)
                    retList.add(bizList.get(i));
            }
            return  retList;
        } else if (reqCity == null) {
            for (String i : bizList.keySet()) {
                if (bizList.get(i).getStars() >= reqStars && bizList.get(i).getReviewCount() >= reqRC && bizList.get(i).getState().equals(reqState) )
                    retList.add(bizList.get(i));
            }
            return  retList;
        } else if  (reqState == null){
            for (String i : bizList.keySet()) {
                if (bizList.get(i).getStars() >= reqStars && bizList.get(i).getReviewCount() >= reqRC &&  bizList.get(i).getCity().equals(reqCity) )
                    retList.add(bizList.get(i));
            }
            return  retList;
        } else {
            for (String i : bizList.keySet()) {
                if (bizList.get(i).getStars() >= reqStars && bizList.get(i).getReviewCount() >= reqRC && bizList.get(i).getState().equals(reqState) && bizList.get(i).getCity().equals(reqCity) )
                    retList.add(bizList.get(i));
            }
            return  retList;
        }
    }

    public static ArrayList<Business> filterAllEntrySet(Float reqStars, int reqRC, String reqState, String reqCity){
        ArrayList<Business> retList = new ArrayList<>();
        if(reqStars == null) reqStars = 0.0f;
        if(reqCity == null && reqState == null){
            for (Map.Entry<String, Business> entry : bizList.entrySet()) {
                if (entry.getValue().getStars() >= reqStars && entry.getValue().getReviewCount() >= reqRC )
                    retList.add(entry.getValue());
            }
            return  retList;
        } else if (reqCity == null) {
            for (Map.Entry<String, Business> entry : bizList.entrySet()) {
                if (entry.getValue().getStars() >= reqStars && entry.getValue().getReviewCount() >= reqRC && entry.getValue().getState().equals(reqState) )
                    retList.add(entry.getValue());
            }
            return  retList;
        } else if  (reqState == null){
            for (Map.Entry<String, Business> entry : bizList.entrySet()) {
                if (entry.getValue().getStars() >= reqStars && entry.getValue().getReviewCount() >= reqRC && entry.getValue().getCity().equals(reqCity) )
                    retList.add(entry.getValue());
            }
            return  retList;
        } else {
            for (Map.Entry<String, Business> entry : bizList.entrySet()) {
                if (entry.getValue().getStars() >= reqStars && entry.getValue().getReviewCount() >= reqRC && entry.getValue().getState().equals(reqState) && entry.getValue().getCity().equals(reqCity) )
                    retList.add(entry.getValue());
            }
            return  retList;
        }

    }

    public static ArrayList<Business> filterAllEntryIter(Float reqStars, int reqRC, String reqState, String reqCity){
        ArrayList<Business> retList = new ArrayList<>();
        if(reqStars == null) reqStars = 0.0f;
        if(reqCity == null && reqState == null){
            Iterator<Map.Entry<String,Business>> itr1 = bizList.entrySet().iterator();
            while(itr1.hasNext())
            {
                Map.Entry<String, Business> entry = itr1.next();
                if (entry.getValue().getStars() >= reqStars && entry.getValue().getReviewCount() >= reqRC )
                    retList.add(entry.getValue());
            }
            return  retList;
        } else if (reqCity == null) {
            Iterator<Map.Entry<String,Business>> itr1 = bizList.entrySet().iterator();
            while(itr1.hasNext())
            {
                Map.Entry<String, Business> entry = itr1.next();
                if (entry.getValue().getStars() >= reqStars && entry.getValue().getReviewCount() >= reqRC && entry.getValue().getState().equals(reqState)  )
                    retList.add(entry.getValue());
            }
            return  retList;
        } else if  (reqState == null){
            Iterator<Map.Entry<String,Business>> itr1 = bizList.entrySet().iterator();
            while(itr1.hasNext())
            {
                Map.Entry<String, Business> entry = itr1.next();
                if (entry.getValue().getStars() >= reqStars && entry.getValue().getReviewCount() >= reqRC && entry.getValue().getCity().equals(reqCity) )
                    retList.add(entry.getValue());
            }
            return  retList;
        } else {
            Iterator<Map.Entry<String,Business>> itr1 = bizList.entrySet().iterator();
            while(itr1.hasNext())
            {
                Map.Entry<String, Business> entry = itr1.next();
                if (entry.getValue().getStars() >= reqStars && entry.getValue().getReviewCount() >= reqRC && entry.getValue().getState().equals(reqState) && entry.getValue().getCity().equals(reqCity) )
                    retList.add(entry.getValue());
            }
            return  retList;
        }
    }

    public static ArrayList<Business> filterAllKeyIter(Float reqStars, int reqRC, String reqState, String reqCity){
        ArrayList<Business> retList = new ArrayList<>();

        if(reqStars == null) reqStars = 0.0f;
        if(reqCity == null && reqState == null){
            Iterator itr2 = bizList.keySet().iterator();
            while(itr2.hasNext())
            {
                String i = (String) itr2.next();
                if (bizList.get(i).getStars() >= reqStars && bizList.get(i).getReviewCount() >= reqRC )
                    retList.add(bizList.get(i));
            }
            return  retList;
        } else if (reqCity == null) {
            Iterator itr2 = bizList.keySet().iterator();
            while(itr2.hasNext())
            {
                String i = (String) itr2.next();
                if (bizList.get(i).getStars() >= reqStars && bizList.get(i).getReviewCount() >= reqRC && bizList.get(i).getState().equals(reqState)  )
                    retList.add(bizList.get(i));
            }
            return  retList;
        } else if  (reqState == null){
            Iterator itr2 = bizList.keySet().iterator();
            while(itr2.hasNext())
            {
                String i = (String) itr2.next();
                if (bizList.get(i).getStars() >= reqStars && bizList.get(i).getReviewCount() >= reqRC && bizList.get(i).getCity().equals(reqCity) )
                    retList.add(bizList.get(i));
            }
            return  retList;
        } else {
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
}
