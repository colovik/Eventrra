package com.example.eventrramongodb.repository;

import com.example.eventrramongodb.model.Band;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BandRepository extends MongoRepository<Band, String> {

    boolean existsById(String id);

    Optional<Band> findById(String id);
}
