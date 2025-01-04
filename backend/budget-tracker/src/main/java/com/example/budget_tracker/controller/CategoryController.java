package com.example.budget_tracker.controller;

import com.example.budget_tracker.model.Categories;
import com.example.budget_tracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public String addUserCategories(@RequestBody Categories category){
        categoryService.postUserCategories(category);
        return "Category added!";
    }

    @GetMapping ("/user")
    public ResponseEntity<List<Categories>> getUserCategories(){
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(categoryService.getUserCategories(userId));
    }
}

