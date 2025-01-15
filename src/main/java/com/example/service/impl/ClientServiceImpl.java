package com.example.service.impl;

import com.example.exceptions.NoSuchIDException;
import com.example.model.Client;
import com.example.repository.ClientRepository;
import com.example.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Optional<Client> findById(Integer Id) {
        return Optional.ofNullable(this.clientRepository.findById(Id)
                .orElseThrow(() -> new NoSuchIDException(Id)));
    }

    @Override
    public boolean existsById(Integer id) {
        return this.clientRepository.existsById(id);
    }
}
