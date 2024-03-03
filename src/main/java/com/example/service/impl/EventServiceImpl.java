package com.example.service.impl;

import com.example.exceptions.InvalidArgumentsException;
import com.example.model.Client;
import com.example.model.Enumerations.Status;
import com.example.model.Event;
import com.example.repository.ClientRepository;
import com.example.repository.EventRepository;
import com.example.repository.LocationRepository;
import com.example.repository.UserRepository;
import com.example.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final ClientRepository clientRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;

    public EventServiceImpl(EventRepository eventRepository, ClientRepository clientRepository, LocationRepository locationRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.clientRepository = clientRepository;
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
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
    public Event findById(Integer id) {
        return eventRepository.findById(id).get();
    }

    @Override
    public List<Event> findByClient(Integer client_id) {
        Optional<Client> client = clientRepository.findById(client_id);
//        User user = userRepository.findById(user_id).get();
//        return eventRepository.findByUser(user);
        return eventRepository.findByClient(client);
    }

    @Override
    public Optional<Event> updateStatus(Integer event_id, Status status) {
        Event event = eventRepository.findById(event_id).orElseThrow(InvalidArgumentsException::new);
        event.setStatus(status);
        return Optional.of(eventRepository.save(event));
    }

}
