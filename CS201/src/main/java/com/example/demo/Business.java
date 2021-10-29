package com.example.demo;


import java.util.*;

public class Business {
    String business_id;
    String name;
    String address;
    String city;
    String state;
    String postalCode;
    String latitude;
    String longitude;
    String stars;
    String review_count;
    String is_open;
    Attribute attribute;
    String categories;

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public void setReview_count(String review_count) {
        this.review_count = review_count;
    }

    public void setIs_open(String is_open) {
        this.is_open = is_open;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getStars() {
        return stars;
    }

    public String getReview_count() {
        return review_count;
    }

    public String getIs_open() {
        return is_open;
    }

//    public Attribute getAttribute() {
//        return attribute;
//    }

    // public String getCategories() {
    //     return categories;
    // }
}
