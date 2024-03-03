package com.example.service.impl;

import com.example.model.Location;
import com.example.repository.LocationRepository;
import com.example.service.LocationService;
import org.springframework.stereotype.Service;
import com.example.exceptions.NoSuchAddressException;

import java.util.List;

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
    public Location findByAddress(String address) {
        return locationRepository.findAllByAddress(address).stream().findFirst()
                .orElseThrow(() -> new NoSuchAddressException(address));
    }
}
