package com.example.eventrramongodb.service;

import com.example.eventrramongodb.model.Food;

import java.util.List;
import java.util.Optional;

public interface FoodService{

    List<Food> findAll();

    List<Food> findAllFoodByCateringId(String cateringId);

    void deleteFoodById(String foodId);

    Optional<Food> findById(String id);

    void updateFood(Food food);

    void saveFood(Food food);

}
