package com.example.budget_tracker.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name="transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="trans_id")
    private int id;
    @Column (name="user_id")
    private String userId;
    @Column (nullable=false)
    private float amount_spent;
    private String details;
    @Column (name="category_id")
    private int categoryId;
    @Column (name="subcategory_id")
    private int subcategoryId;
    @Column(name="date", nullable = false) // Map the "date" column
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId=userId;
    }

    public int getCategoryId(){
        return categoryId;
    }

    public void setCategoryId(int categoryId){
        this.categoryId=categoryId;
    }

    public int getSubcategoryId(){
        return subcategoryId;
    }

    public void setSubcategoryId(int subcategoryId){
        this.subcategoryId=subcategoryId;
    }

    public String getDetails(){
        return details;
    }

    public void setDetails(String details){
        this.details=details;
    }

    public float getAmountSpent(){
        return amount_spent;
    }

    public void setAmountSpent(float amount_spent){
        this.amount_spent=amount_spent;
    }
}

