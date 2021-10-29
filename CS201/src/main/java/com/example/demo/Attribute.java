package com.example.demo;

public class Attribute {
    boolean RestaurantsTakeOut;
    BusinessParking businessParking;

    public void setRestaurantsTakeOut(boolean restaurantsTakeOut) {
        RestaurantsTakeOut = restaurantsTakeOut;
    }

    public void setBusinessParking(BusinessParking businessParking) {
        this.businessParking = businessParking;
    }

    public boolean isRestaurantsTakeOut() {
        return RestaurantsTakeOut;
    }

    public BusinessParking getBusinessParking() {
        return businessParking;
    }
}
