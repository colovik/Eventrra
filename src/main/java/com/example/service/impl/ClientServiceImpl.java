package com.example.service.impl;

import com.example.exceptions.NoSuchIDException;
import com.example.model.Client;
import com.example.model.Event;
import com.example.model.User;
import com.example.repository.ClientRepository;
import com.example.repository.EventRepository;
import com.example.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final EventRepository eventRepository;

    public ClientServiceImpl(ClientRepository clientRepository, EventRepository eventRepository) {
        this.clientRepository = clientRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public Optional<Client> findById(String Id) {
        return Optional.ofNullable(this.clientRepository.findById(Id)
                .orElseThrow(() -> new NoSuchIDException(Id)));
    }

    @Override
    public boolean existsById(String id) {
        return this.clientRepository.existsById(id);
    }

    @Override
    public void save(Client client) {
        this.clientRepository.save(client);
    }

    @Override
    public List<Event> getEventsByUser(Client client) {
        List<String> eventIds = client.getEventIds();
        List<Event> events = new ArrayList<>();

        for (String eventId : eventIds) {
            this.eventRepository.findById(eventId).ifPresent(events::add);
        }

        return events;
    }

    @Override
    public void delete(Client client) {
        client.setIsDeleted(true);
        clientRepository.save(client);
    }

    @Override
    public List<User> findAllActiveClients() {
        List<User> activeClients = new ArrayList<>();
        for (Client c : clientRepository.findAll()) {
            if (c.getIsDeleted() == false) {
                activeClients.add(c);
            }
        }

        return activeClients;    }


}
