package com.example.demo.business;

import com.google.gson.annotations.SerializedName;

public class Business {
    @SerializedName("business_id")
    private String businessId;
    private String name;
    private String address;
    private String city;
    private String state;
    @SerializedName("postal_code")
    private String postalCode;
    private String latitude;
    private String longitude;
    private String stars;
    @SerializedName("review_count")
    private int reviewCount;
    @SerializedName("is_open")
    private int isOpen;
    private Attribute attribute;
    private String categories; //String[]
    private Object hours;

    public String getBusinessId() {
        return businessId;
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

    public int getReviewCount() {
        return reviewCount;
    }

    public int getIsOpen() {
        return isOpen;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public String getCategories() {
        return categories;
    }

    public Object getHours() {
        return hours;
    }

    public String toString() {
        return String.format("\n[businessId: " +  businessId + ", name: " + name + ", city: " + city + ", state: " + state +", latitude: " + latitude + ", longitude: " + longitude + ", stars: " + stars + ", review count: " + reviewCount +"]");
    }
}
