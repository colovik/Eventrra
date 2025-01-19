package com.example.eventrramongodb.repository;

import com.example.eventrramongodb.model.Allergens;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllergenRepository extends MongoRepository<Allergens, String> {

    @Query("{ 'allergenIds' : ?0 }")
    List<Allergens> findAllByFoodId(String foodId);

    List<Allergens> findAllByIdIn(List<String> allergens);
}
