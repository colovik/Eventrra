package com.example.service;

import com.example.model.Drink;

import java.util.List;
import java.util.Optional;

public interface DrinkService {
    List<Drink> findAll();

    List<Drink> findAllDrinksByCateringId(String cateringId);

    void deleteDrinkById(String drinkId);

    Optional<Drink> findById(String id);

    void updateDrink(Drink drink);

    void saveDrink(Drink drink);
}
