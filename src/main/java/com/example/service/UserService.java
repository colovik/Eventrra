package com.example.service;

import com.example.model.Event;
import com.example.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAllUsers();
    Optional<User> findByUsername(String username);
    User findById(String id);

    List<User> getUsersByRole(String roleFilter);

    List<User> getAllUsers();

    List<Event> getEventsByUser(User user);

    void save(User user);

    void delete(User user);

    List<User> findAllActiveUsers();

    List<User> getActiveUsersByRole(String roleFilter);
}
