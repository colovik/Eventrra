package com.example.eventrramongodb.repository;

import com.example.eventrramongodb.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {
    @Query("{ 'clientId' : ?0 }")
    List<Event> findByClientId(String clientId);

    Optional<Event> findById(String id);

}
