package com.example.repository;

import com.example.model.Photographer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotographerRepository extends MongoRepository<Photographer, String> {
    List<Photographer> findAll();

    boolean existsById(String id);
}
