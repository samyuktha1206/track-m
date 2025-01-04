package com.example.budget_tracker.controller;

import com.example.budget_tracker.dto.UserRequest;
import com.example.budget_tracker.dto.UserResponse;
import com.example.budget_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String addUser(@RequestBody UserRequest userRequest){
        userService.createNewUser(userRequest);
        return "User added!";
    }

    @GetMapping
    public ResponseEntity<UserResponse> getUserInfo(){
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserResponse userResponse = userService.getUserDetails(userId);
        return ResponseEntity.ok(userResponse);
    }

}



