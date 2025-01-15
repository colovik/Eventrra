package com.example.service;

import com.example.model.Client;

import java.util.Optional;

public interface ClientService {

    Optional<Client> findById(Integer Id);

    boolean existsById(Integer id);
}
