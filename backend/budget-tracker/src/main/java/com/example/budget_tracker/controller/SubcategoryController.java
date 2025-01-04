package com.example.budget_tracker.controller;

import com.example.budget_tracker.model.Subcategories;
import com.example.budget_tracker.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/subcategories")
public class SubcategoryController {

    @Autowired
    SubcategoryService subcategoryService;

    @PostMapping
    public String addUserSubcategories(@RequestBody Subcategories subcategory){
        subcategoryService.postUserSubcategories(subcategory);
        return "Subcategory added!";
    }

    @GetMapping ("/user")
    public ResponseEntity<List<Subcategories>> getUserSubcategories(){
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(subcategoryService.getUserSubcategories(userId));
    }
}

