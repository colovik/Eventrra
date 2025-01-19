package com.example.repository;

import com.example.model.Drink;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinkRepository extends MongoRepository<Drink, String> {

    List<Drink> findAllByCateringId(String cateringId);
}
