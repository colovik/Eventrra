package com.example.repository;

import com.example.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findById(Integer id);

    List<Client> findAllByName (String name);

    Client findByUsername (String username);
}
