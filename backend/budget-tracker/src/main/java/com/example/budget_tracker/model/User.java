package com.example.budget_tracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Generated;

import java.time.LocalDateTime;

@Entity
@Table (name="users")
public class User {
    @Id
    @Column(name="user_id")
    private String id;
    private String name;
    private String email;
    @Column (name="created_at")
    private LocalDateTime date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDate(LocalDateTime date){
        this.date = date;
    }

    public LocalDateTime getDate(){
        return date;
    }
}

