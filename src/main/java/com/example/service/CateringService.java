package com.example.service;

import com.example.model.Catering;

import java.util.List;

public interface CateringService {
    List<Catering> findAll();
    Catering findByName(String name);
}
