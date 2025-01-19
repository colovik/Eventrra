package com.example.service.impl;

import com.example.model.Food;
import com.example.repository.FoodRepository;
import com.example.repository.ProductRepository;
import com.example.service.FoodService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    private final ProductRepository productRepository;

    public FoodServiceImpl(FoodRepository foodRepository, ProductRepository productRepository) {
        this.foodRepository = foodRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Food> findAll() {
        return this.foodRepository.findAll();
    }

    @Override
    public List<Food> findAllFoodByCateringId(String cateringId) {
        return this.foodRepository.findByCateringId(cateringId);
    }

    @Override
    public void deleteFoodById(String foodId) {
        if (this.foodRepository.existsById(foodId)) {
            this.foodRepository.deleteById(foodId);
            this.productRepository.deleteById(foodId);
        } else {
            throw new IllegalArgumentException("Food not found with id: " + foodId);
        }
    }

    @Override
    public Optional<Food> findById(String id) {
        return this.foodRepository.findById(id);
    }

    @Override
    public void updateFood(Food food) {
        this.foodRepository.save(food);
    }

    @Override
    public void saveFood(Food food) {
        this.foodRepository.save(food);
    }
}
