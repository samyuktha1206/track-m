package com.example.budget_tracker.repository;

import com.example.budget_tracker.model.Subcategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubcategoriesRepository extends JpaRepository<Subcategories, Integer>{
    List<Subcategories> findByUserId(String userId);
    @Query("SELECT c.id FROM Subcategories c WHERE c.name=:subcategoryName")
    Optional<Integer> findIdByName(@Param("subcategoryName") String subCategoryName);
    @Query("SELECT c.name FROM Subcategories c WHERE c.id=:subcategoryId")
    Optional<String> findNameById(@Param("subcategoryId") int subcategoryId);
    //Add custom queries for Subcategories if need.
}

