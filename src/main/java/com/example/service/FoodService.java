package com.example.service;

import com.example.model.Food;

import java.util.List;
import java.util.Optional;

public interface FoodService{

    List<Food> findAll();

    List<Food> findAllFoodByCateringId(Integer cateringId);

    void deleteFoodById(Integer foodId);

    Optional<Food> findById(Integer id);

    void updateFood(Food food);

    void saveFood(Food food);

}
