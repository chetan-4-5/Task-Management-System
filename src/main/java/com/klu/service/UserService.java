package com.klu.service;

import java.util.List;

import com.klu.model.User;

public interface UserService {
    User findByEmail(String email); 
    void saveUser(User user);
    List<User> findUsersByRole(String role);
    User findById(Long userId);
    void deleteEmployee(Long employeeId);
    
}

