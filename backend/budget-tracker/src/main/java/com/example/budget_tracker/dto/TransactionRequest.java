package com.example.budget_tracker.dto;

public class TransactionRequest {
    private float amount_spent;
    private String details;
    private String categoryName;
    private String subcategoryName;

    //getters and setters

    public float getAmount_spent(){
        return amount_spent;
    }

    public void setAmount_spent(float amount_spent){
        this.amount_spent=amount_spent;
    }

    public String getDetails(){
        return details;
    }

    public void setDetails(String details){
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
}

