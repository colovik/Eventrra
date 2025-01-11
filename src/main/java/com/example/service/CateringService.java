package com.example.service;

import com.example.model.Catering;

import java.util.List;
import java.util.Optional;

public interface CateringService {
    List<Catering> findAll();
    Catering findByName(String name);

    Optional<Catering> findById(Integer id);
}
