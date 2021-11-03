package com.example.demo.business;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.HashCodeBuilder;
import java.io.Serializable;

public class Business implements Serializable {
    private static final long serialVersionUID = 1L;
    @SerializedName("business_id")
    private String businessId;
    private String name;
    private String address;
    private String city;
    private String state;
    @SerializedName("postal_code")
    private String postalCode;
    private Float latitude;
    private Float longitude;
    private Float stars;
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

    public Float getLatitude() {
        return latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public Float getStars() {
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

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 97).
                append(businessId).
                append(name).
                toHashCode();
    }
}
