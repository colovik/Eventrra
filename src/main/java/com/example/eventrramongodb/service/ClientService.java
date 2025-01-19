package com.example.eventrramongodb.service;

import com.example.eventrramongodb.model.Client;
import com.example.eventrramongodb.model.Event;
import com.example.eventrramongodb.model.User;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    Optional<Client> findById(String Id);

    boolean existsById(String id);

    void save(Client client);

    List<Event> getEventsByUser(Client client);

    void delete(Client client);

    List<User> findAllActiveClients();

}