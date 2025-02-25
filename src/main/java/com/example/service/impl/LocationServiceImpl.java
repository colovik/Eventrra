package com.example.service.impl;

import com.example.model.Location;
import com.example.repository.LocationRepository;
import com.example.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> findAll() {
        return this.locationRepository.findAll();
    }

    @Override
    public void save(Location existingLocation) {
        this.locationRepository.save(existingLocation);
    }

    @Override
    public Optional<Location> findById(String id) {
        return this.locationRepository.findById(id);
    }

    @Override
    public void deleteLocationById(String id) {
        this.locationRepository.deleteById(id);
    }
}
