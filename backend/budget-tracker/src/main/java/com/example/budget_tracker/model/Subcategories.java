package com.example.budget_tracker.model;

import jakarta.persistence.*;

import javax.annotation.processing.Generated;

@Entity
@Table (name="subcategories")
public class Subcategories {
    @Id
    @GeneratedValue
    private int id;

    @Column (nullable=false)
    private String name;

    private final boolean is_predefined=false;

    @Column (name="user_id")
    private String userId;

    @Column (name="category_id")
    private int categoryId;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public boolean isPredefined(){
        return is_predefined;
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
}

