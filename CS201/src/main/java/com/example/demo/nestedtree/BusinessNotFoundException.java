package com.example.demo.nestedtree;

public class BusinessNotFoundException extends RuntimeException{
    public BusinessNotFoundException(){
        super("Business is not found");
    }
}
