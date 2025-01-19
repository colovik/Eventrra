package com.example.eventrramongodb.repository;

import com.example.eventrramongodb.model.Catering;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CateringRepository extends MongoRepository<Catering, String> {
    Catering findByName(String name);

    boolean existsById(String id);

    Optional<Catering> findById(String id);
}
