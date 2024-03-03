package com.example.service;

import com.example.model.Location;

import java.util.List;

public interface LocationService {
    List<Location> findAll();
    Location findByAddress(String address);
}
