package com.example.budget_tracker.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Generated;

@Entity
@Table (name="categories")
public class Categories {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column (nullable=false, length=100)
    private String name;
    @Column (nullable=false)
    private final boolean is_predefined = false;
    @Column(name="user_id")
    private String userId;

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
}
