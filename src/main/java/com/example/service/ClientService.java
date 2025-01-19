package com.example.service;

import com.example.model.Client;
import com.example.model.Event;
import com.example.model.User;

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