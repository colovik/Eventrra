package com.example.eventrramongodb.repository;

import com.example.eventrramongodb.model.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends MongoRepository<Location, String> {
    Optional<Location> findById(String id);

    void deleteById(String id);

    Location findByName(String name);
}
