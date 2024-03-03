package com.example.service;

import com.example.model.Band;

import java.util.List;

public interface BandService {

    List<Band> findAll();
    Band findByName(String name);
}
