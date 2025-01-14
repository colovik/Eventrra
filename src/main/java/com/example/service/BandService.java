package com.example.service;

import com.example.model.Band;

import java.util.List;
import java.util.Optional;

public interface BandService {

    List<Band> findAll();

    Optional<Band> findById(Integer id);

    boolean existsById(Integer id);
}
