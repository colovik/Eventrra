package com.example.service;

import com.example.model.Allergens;

import java.util.List;

public interface AllergenService {
    List<Allergens> findAll();

    List<Allergens> findAllById(List<String> allergenIds);

    List<Allergens> findAllByFoodId(String id);
}
