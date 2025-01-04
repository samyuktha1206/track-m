package com.example.budget_tracker.dto;

public class UserRequest {
    private String name;
    private String email;

    //getters & setters

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(){
        this.email = email;
    }
}

