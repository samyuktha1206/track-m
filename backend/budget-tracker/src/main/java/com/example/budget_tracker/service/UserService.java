package com.example.budget_tracker.service;

import com.example.budget_tracker.dto.UserRequest;
import com.example.budget_tracker.dto.UserResponse;
import com.example.budget_tracker.model.User;
import com.example.budget_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserResponse getUserDetails(String userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found with ID: "+ userId));
        return new UserResponse(user.getName(), user.getEmail());
    }

    public String createNewUser(UserRequest userRequest){
        User user = new User();
        user.setId((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setDate(LocalDateTime.now());
        userRepository.save(user);
        return "User added!";
    }
}

