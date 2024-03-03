package com.example.service.impl;

import com.example.exceptions.NoSuchIDException;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).stream().findFirst()
                .orElseThrow(() -> new NoSuchIDException(id));
    }
}
