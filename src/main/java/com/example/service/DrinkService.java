package com.example.service;

import com.example.model.Drink;

import java.util.List;
import java.util.Optional;

public interface DrinkService {
    List<Drink> findAll();

    List<Drink> findAllDrinksByCateringId(Integer cateringId);

    void deleteDrinkById(Integer drinkId);

    Optional<Drink> findById(Integer id);

    void updateDrink(Drink drink);

    void saveDrink(Drink drink);
}
