package com.example.budget_tracker.repository;

import com.example.budget_tracker.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Integer>{
    List<Transactions> findByUserId(String userId);

    List<Transactions> findTop10ByUserIdOrderByDateDesc(String userId);
}


