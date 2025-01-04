package com.example.budget_tracker.dto;

public class UserResponse {
    private String name;
    private String email;

    //constructor

    public UserResponse(String name, String email){
        this.name = name;
        this.email = email;
    }

    //setters & getters

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }
}


