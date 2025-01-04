package com.example.budget_tracker.service;

import com.example.budget_tracker.model.Categories;
import com.example.budget_tracker.repository.CategoriesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    CategoriesRepository categoriesRepository;

    public List<Categories> getUserCategories(String userId){
        return categoriesRepository.findByUserId(userId);
    }

    public String postUserCategories(Categories category){
        categoriesRepository.save(category);
        return "Category posted!";
    }
}

