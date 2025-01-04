package com.example.budget_tracker.service;

import com.example.budget_tracker.model.Subcategories;
import com.example.budget_tracker.repository.SubcategoriesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubcategoryService {
    SubcategoriesRepository subcategoriesRepository;

    public List<Subcategories> getUserSubcategories(String userId){
        return subcategoriesRepository.findByUserId(userId);
    }

    public String postUserSubcategories(Subcategories subcategory){
        subcategoriesRepository.save(subcategory);
        return "Subcategory Posted!";
    }
}

