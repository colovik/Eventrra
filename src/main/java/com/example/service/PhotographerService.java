package com.example.service;

import com.example.model.Photographer;

import java.util.List;

public interface PhotographerService {
    List<Photographer> findAll();
    Photographer findByName(String name);
}
