package com.example.eventrramongodb.service.impl;

import com.example.eventrramongodb.exceptions.NoSuchIDException;
import com.example.eventrramongodb.model.Event;
import com.example.eventrramongodb.model.Photographer;
import com.example.eventrramongodb.model.User;
import com.example.eventrramongodb.repository.EventRepository;
import com.example.eventrramongodb.repository.PhotographerRepository;
import com.example.eventrramongodb.service.PhotographerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhotographerServiceImpl implements PhotographerService {

    private final PhotographerRepository photographerRepository;
    private final EventRepository eventRepository;

    public PhotographerServiceImpl(PhotographerRepository photographerRepository, EventRepository eventRepository) {
        this.photographerRepository = photographerRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Photographer> findAll() {
        return this.photographerRepository.findAll();
    }

    @Override
    public Optional<Photographer> findById(String Id) {
        return Optional.ofNullable(this.photographerRepository.findById(Id)
                .orElseThrow(() -> new NoSuchIDException(Id)));
    }

    @Override
    public boolean existsById(String id) {
        return this.photographerRepository.existsById(id);
    }

    @Override
    public void save(Photographer p) {
        this.photographerRepository.save(p);
    }

    @Override
    public List<Event> getEventsByUser(Photographer photographer) {
        List<String> eventIds = photographer.getEventIds();
        List<Event> events = new ArrayList<>();

        for (String eventId : eventIds) {
            this.eventRepository.findById(eventId).ifPresent(events::add);
        }

        return events;
    }

    @Override
    public void delete(Photographer photographer) {
        photographer.setIsDeleted(true);
        photographerRepository.save(photographer);
    }

    @Override
    public List<User> findAllActivePhotographers() {
        List<User> activePhotographers = new ArrayList<>();
        for (Photographer p : photographerRepository.findAll()) {
            if (p.getIsDeleted() == false) {
                activePhotographers.add(p);
            }
        }
        return activePhotographers;
    }

}
