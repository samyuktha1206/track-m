package com.example.budget_tracker.controller;

import com.example.budget_tracker.dto.TransactionRequest;
import com.example.budget_tracker.dto.TransactionResponse;
import com.example.budget_tracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public String addUserTransactions(@RequestBody TransactionRequest transactionRequest){
        transactionService.postUserTransactions(transactionRequest);
        return "Transaction posted!";
    }

    @GetMapping ("/user")
    public ResponseEntity<List<TransactionResponse>> getUserTransactions(){
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(transactionService.getUserTransactions(userId));
    }

    @GetMapping ("/recent10")
    public ResponseEntity<List<TransactionResponse>> getRecent10Transactions(){
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(transactionService.getRecent10Transactions(userId));
    }
}

