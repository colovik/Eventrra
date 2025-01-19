package com.example.service.impl;

import com.example.exceptions.NoSuchIDException;
import com.example.model.Band;
import com.example.model.Event;
import com.example.model.User;
import com.example.repository.BandRepository;
import com.example.repository.EventRepository;
import com.example.service.BandService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BandServiceImpl implements BandService {

    private final BandRepository bandRepository;

    private final EventRepository eventRepository;

    public BandServiceImpl(BandRepository bandRepository, EventRepository eventRepository) {
        this.bandRepository = bandRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Band> findAll() {
        return this.bandRepository.findAll();
    }

    @Override
    public Optional<Band> findById(String Id) {
        return Optional.ofNullable(this.bandRepository.findById(Id)
                .orElseThrow(() -> new NoSuchIDException(Id)));
    }

    @Override
    public boolean existsById(String id) {
        return this.bandRepository.existsById(id);
    }

    @Override
    public void save(Band b) {
        this.bandRepository.save(b);
    }

    @Override
    public List<Event> getEventsByUser(Band band) {
        List<String> eventIds = band.getEventIds();
        List<Event> events = new ArrayList<>();

        for (String eventId : eventIds) {
            this.eventRepository.findById(eventId).ifPresent(events::add);
        }

        return events;
    }

    @Override
    public void delete(Band band) {
        band.setIsDeleted(true);
        bandRepository.save(band);
    }

    @Override
    public List<User> findAllActiveBands() {
        List<User> activeBands = new ArrayList<>();
        for (Band b : bandRepository.findAll()) {
            if (b.getIsDeleted() == false) {
                activeBands.add(b);
            }
        }
        return activeBands;
    }

}

