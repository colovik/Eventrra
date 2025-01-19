package com.example.eventrramongodb.service;

import com.example.eventrramongodb.model.Band;
import com.example.eventrramongodb.model.Event;
import com.example.eventrramongodb.model.User;

import java.util.List;
import java.util.Optional;

public interface BandService {

    List<Band> findAll();

    Optional<Band> findById(String id);

    boolean existsById(String id);

    void save(Band b);

    List<Event> getEventsByUser(Band band);

    void delete(Band band);

    List<User> findAllActiveBands();
}
