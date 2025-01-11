package com.example.service;

import com.example.model.Photographer;

import java.util.List;
import java.util.Optional;

public interface PhotographerService {
    List<Photographer> findAll();
    Photographer findByName(String name);

    Optional<Photographer> findById(Integer id);
}
