package com.example.service.impl;

import com.example.exceptions.InvalidArgumentsException;
import com.example.exceptions.NoSuchIDException;
import com.example.model.Client;
import com.example.model.Enumerations.Status;
import com.example.model.Event;
import com.example.repository.ClientRepository;
import com.example.repository.EventRepository;
import com.example.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final ClientRepository clientRepository;

    public EventServiceImpl(EventRepository eventRepository, ClientRepository clientRepository) {
        this.eventRepository = eventRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Event> findAll() {
        return this.eventRepository.findAll();
    }

    @Override
    public void update(Event e) {
        eventRepository.save(e);
    }

    @Override
    public Event save(Event event) {
        this.eventRepository.save(event);
        return event;
    }

    @Override
    public Event findById(String id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new NoSuchIDException("Event not found with id: " + id));
    }


    @Override
    public List<Event> findByClient(String clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new NoSuchIDException(clientId));
        return eventRepository.findByClientId(client.getId());
    }


    @Override
    public Optional<Event> updateStatus(String event_id, Status status) {
        Event event = eventRepository.findById(event_id).orElseThrow(InvalidArgumentsException::new);
        event.setStatus(status);
        return Optional.of(eventRepository.save(event));
    }

}
