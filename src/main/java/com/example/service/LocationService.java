package com.example.service;

import com.example.model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    List<Location> findAll();

    void save(Location existingLocation);

    Optional<Location> findById(Integer id);

    void deleteLocationById(Integer id);
}
