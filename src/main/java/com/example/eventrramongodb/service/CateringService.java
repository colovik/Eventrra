package com.example.eventrramongodb.service;

import com.example.eventrramongodb.model.Catering;
import com.example.eventrramongodb.model.Event;
import com.example.eventrramongodb.model.User;

import java.util.List;
import java.util.Optional;

public interface CateringService {
    List<Catering> findAll();

    Optional<Catering> findById(String id);

    String getCateringIdByName(String name);

    void addProductToCateringOffer(String cateringId, String productId);

    void deleteProductFromCateringOffer(String cateringId, String productId);

    boolean existsById(String id);

    void save(Catering c);

    List<Event> getEventsByUser(Catering catering);

    void delete(Catering catering);

    List<User> findAllActiveCaterings();
}
