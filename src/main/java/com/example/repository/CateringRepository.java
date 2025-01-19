package com.example.repository;

import com.example.model.Catering;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CateringRepository extends MongoRepository<Catering, String> {
    Catering findByName(String name);

    boolean existsById(String id);

    Optional<Catering> findById(String id);
}
