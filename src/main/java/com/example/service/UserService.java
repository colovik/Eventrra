package com.example.service;

import com.example.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAllUsers();
    Optional<User> findByUsername(String username);
    User findById(Integer id);

    List<User> getUsersByRole(String roleFilter);

    List<User> getAllUsers();
}
