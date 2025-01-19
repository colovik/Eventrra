package com.example.service;

import com.example.model.Enumerations.Status;
import com.example.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {

    Event findById(String id);

    List<Event> findByClient(String clientId);

    Optional<Event> updateStatus(String event_id, Status status);

    List<Event> findAll();

    void update(Event event);

    Event save(Event event);

}
