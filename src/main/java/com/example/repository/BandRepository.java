package com.example.repository;

import com.example.model.Band;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BandRepository extends MongoRepository<Band, String> {

    boolean existsById(String id);

    Optional<Band> findById(String id);
}
