package com.example.service.impl;

import com.example.exceptions.CateringNotFoundException;
import com.example.exceptions.NoSuchIDException;
import com.example.model.*;
import com.example.repository.CateringRepository;
import com.example.repository.EventRepository;
import com.example.service.CateringService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CateringServiceImpl implements CateringService {

    private final CateringRepository cateringRepository;

    private final EventRepository eventRepository;

    public CateringServiceImpl(CateringRepository cateringRepository, EventRepository eventRepository) {
        this.cateringRepository = cateringRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Catering> findAll() {
        return this.cateringRepository.findAll();
    }

    @Override
    public Optional<Catering> findById(String Id) {
        return Optional.ofNullable(this.cateringRepository.findById(Id)
                .orElseThrow(() -> new NoSuchIDException(Id)));
    }

    @Override
    public String getCateringIdByName(String name) {
        Catering catering = cateringRepository.findByName(name);
        return catering != null ? catering.getId() : null;
    }

    @Override
    public void addProductToCateringOffer(String cateringId, String productId) {
        Catering catering = cateringRepository.findById(cateringId)
                .orElseThrow(() -> new CateringNotFoundException("Catering with %s id not found", cateringId));
        catering.getProductIds().add(productId);
        cateringRepository.save(catering);
    }

    @Override
    public void deleteProductFromCateringOffer(String cateringId, String productId) {
        Catering catering = cateringRepository.findById(cateringId).orElseThrow(() -> new IllegalArgumentException("Invalid catering ID"));
        catering.getProductIds().add(productId);
        cateringRepository.save(catering);
    }

    @Override
    public boolean existsById(String id) {
        return this.cateringRepository.existsById(id);
    }

    @Override
    public void save(Catering c) {
        this.cateringRepository.save(c);
    }

    @Override
    public List<Event> getEventsByUser(Catering catering) {
        List<String> eventIds = catering.getEventIds();
        List<Event> events = new ArrayList<>();

        for (String eventId : eventIds) {
            this.eventRepository.findById(eventId).ifPresent(events::add);
        }

        return events;
    }

    @Override
    public void delete(Catering catering) {
        catering.setIsDeleted(true);
        cateringRepository.save(catering);
    }

    @Override
    public List<User> findAllActiveCaterings() {
        List<User> activeBands = new ArrayList<>();
        for (Catering c : cateringRepository.findAll()) {
            if (c.getIsDeleted() == false) {
                activeBands.add(c);
            }
        }
        return activeBands;    }
}
