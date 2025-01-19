package com.example.service;

import com.example.model.Event;
import com.example.model.Photographer;
import com.example.model.User;

import java.util.List;
import java.util.Optional;

public interface PhotographerService {
    List<Photographer> findAll();

    Optional<Photographer> findById(String id);

    boolean existsById(String id);

    void save(Photographer p);

    List<Event> getEventsByUser(Photographer photographer);

    void delete(Photographer photographer);

    List<User> findAllActivePhotographers();

}
