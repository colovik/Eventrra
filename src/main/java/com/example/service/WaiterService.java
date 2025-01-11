package com.example.service;

import com.example.model.Waiter;

import java.util.Optional;

public interface WaiterService {
    Optional<Waiter> findById(Integer id);
}
