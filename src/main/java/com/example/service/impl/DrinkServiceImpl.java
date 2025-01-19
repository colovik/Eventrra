package com.example.service.impl;

import com.example.model.Drink;
import com.example.repository.DrinkRepository;
import com.example.repository.ProductRepository;
import com.example.service.DrinkService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrinkServiceImpl implements DrinkService {

    private final DrinkRepository drinkRepository;

    private final ProductRepository productRepository;

    public DrinkServiceImpl(DrinkRepository drinkRepository, ProductRepository productRepository) {
        this.drinkRepository = drinkRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Drink> findAll() {
        return drinkRepository.findAll();
    }

    @Override
    public List<Drink> findAllDrinksByCateringId(String cateringId) {
        return drinkRepository.findAllByCateringId(cateringId);
    }

    @Override
    public void deleteDrinkById(String drinkId) {
        if (this.drinkRepository.existsById(drinkId)) {
            this.drinkRepository.deleteById(drinkId);
            this.productRepository.deleteById(drinkId);
        } else {
            throw new IllegalArgumentException("Drink not found with id: " + drinkId);
        }
    }

    @Override
    public Optional<Drink> findById(String id) {
        return this.drinkRepository.findById(id);
    }

    @Override
    public void updateDrink(Drink drink) {
        this.drinkRepository.save(drink);
    }

    @Override
    public void saveDrink(Drink drink) {
        this.drinkRepository.save(drink);
    }
}
