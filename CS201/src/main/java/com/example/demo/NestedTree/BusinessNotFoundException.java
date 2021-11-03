package com.example.demo.NestedTree;

public class BusinessNotFoundException extends RuntimeException{
    public BusinessNotFoundException(){
        super("Business is not found");
    }
}
