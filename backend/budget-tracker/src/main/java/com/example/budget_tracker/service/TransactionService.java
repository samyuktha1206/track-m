package com.example.budget_tracker.service;

import com.example.budget_tracker.dto.TransactionRequest;
import com.example.budget_tracker.dto.TransactionResponse;
import com.example.budget_tracker.model.Transactions;
import com.example.budget_tracker.repository.CategoriesRepository;
import com.example.budget_tracker.repository.SubcategoriesRepository;
import com.example.budget_tracker.repository.TransactionsRepository;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionsRepository transactionsRepository;

    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    SubcategoriesRepository subcategoriesRepository;

    public List<TransactionResponse> getUserTransactions(String userId){
        List<Transactions> transactions=transactionsRepository.findByUserId(userId);
        return transactions.stream().map(transaction -> {
            String categoryName = categoriesRepository.findNameById(transaction.getCategoryId()).
                    orElse("Unknown category");
            String subcategoryName = subcategoriesRepository.findNameById(transaction.getSubcategoryId()).
                    orElse("Unknown subcategory");
            return new TransactionResponse(transaction.getDate(), transaction.getUserId(),categoryName,subcategoryName,transaction.getAmountSpent(),transaction.getDetails());
        }).toList();
    }

    public List<TransactionResponse> getRecent10Transactions(String userId){
        List<Transactions> transactions = transactionsRepository.findTop10ByUserIdOrderByDateDesc(userId);
        return transactions.stream().map(transaction->{
            String categoryName = categoriesRepository.findNameById(transaction.getCategoryId()).
                    orElse("Unknown Category");
            String subcategoryName=subcategoriesRepository.findNameById(transaction.getSubcategoryId()).
                    orElse("Unknown Subcategory");
            return new TransactionResponse(transaction.getDate(), transaction.getUserId(),categoryName,subcategoryName,transaction.getAmountSpent(),transaction.getDetails());
        }).toList();
    }

    public String postUserTransactions(TransactionRequest transactionRequest){

        int categoryId= categoriesRepository.findIdByName(transactionRequest.getCategoryName()).
                orElseThrow(() -> new RuntimeException("Subcategory not found: "+transactionRequest.getCategoryName()));

        int subcategoryId = subcategoriesRepository.findIdByName(transactionRequest.getSubcategoryName()).
                orElseThrow(()-> new RuntimeException("Subcategory not found: "+transactionRequest.getSubcategoryName()));
        Transactions transaction = new Transactions();
        transaction.setUserId((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        transaction.setAmountSpent(transactionRequest.getAmount_spent());
        transaction.setDetails(transactionRequest.getDetails());
        transaction.setCategoryId(categoryId);
        transaction.setSubcategoryId(subcategoryId);
        transaction.setDate(LocalDate.now());
        transactionsRepository.save(transaction);
        return "Transaction Posted!";
    }
}

