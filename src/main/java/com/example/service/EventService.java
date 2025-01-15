package com.example.service;

import com.example.model.Enumerations.Status;
import com.example.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {

    Event findById(Integer id);

    List<Event> findByClient(Integer client_id);

    Optional<Event> updateStatus(Integer event_id, Status status);

    List<Event> findAll();

    void update(Event event);

    void save(Event event);
}
