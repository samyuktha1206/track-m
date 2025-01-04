package com.example.budget_tracker.repository;

import com.example.budget_tracker.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {
    List<Categories> findByUserId(String userId);
    @Query("SELECT c.id FROM Categories c WHERE c.name =:categoryName")
    Optional<Integer> findIdByName(@Param("categoryName") String categoryName);
    @Query("SELECT c.name FROM Categories c WHERE c.id =:categoryId")
    Optional<String> findNameById(int categoryId);
}

