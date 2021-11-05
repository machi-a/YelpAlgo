package com.example.demo.nestedTree;

public class BusinessNotFoundException extends RuntimeException{
    public BusinessNotFoundException(){
        super("Business is not found");
    }
}
