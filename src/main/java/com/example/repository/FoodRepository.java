package com.example.repository;

import com.example.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends MongoRepository<Food, String> {

    @Query("{ 'cateringId' : ?0 }")
    List<Food> findByCateringId (String cateringId);
}
