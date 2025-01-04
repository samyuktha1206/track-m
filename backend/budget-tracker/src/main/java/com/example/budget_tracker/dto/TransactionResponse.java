package com.example.budget_tracker.dto;

import java.time.LocalDate;

public class TransactionResponse {
    private String userId;
    private float amount_spent;
    private String details;
    private String categoryName;
    private String subcategoryName;
    private LocalDate date;

    //Constructor
    public TransactionResponse(LocalDate date, String userId, String categoryName, String subcategoryName, float amount_spent, String details){
        this.date=date;
        this.userId=userId;
        this.categoryName=categoryName;
        this.subcategoryName=subcategoryName;
        this.amount_spent=amount_spent;
        this.details=details;
    }
    //getters and setters

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId=userId;
    }

    public float getAmount(){
        return amount_spent;
    }

    public void setAmount(float amount_spent){
        this.amount_spent=amount_spent;
    }

    public String getDetails(){
        return details;
    }

    public void setDetails(String Details){
        this.details=details;
    }

    public String getCategoryName(){
        return categoryName;
    }

    public void setCategoryName(String categoryName){
        this.categoryName=categoryName;
    }

    public String getSubcategoryName(){
        return subcategoryName=subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName){
        this.subcategoryName=subcategoryName;
    }

    public LocalDate getDate(){
        return date;
    }

}

