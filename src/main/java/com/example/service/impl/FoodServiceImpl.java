package com.example.service.impl;

import com.example.model.Food;
import com.example.repository.FoodRepository;
import com.example.service.FoodService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public List<Food> findAll() {
        return this.foodRepository.findAll();
    }

    @Override
    public List<Food> findAllFoodByCateringId(Integer cateringId) {
        return this.foodRepository.findAllFoodByCateringId(cateringId);
    }

    @Override
    public void deleteFoodById(Integer foodId) {
        if (this.foodRepository.existsById(foodId)) {
            this.foodRepository.deleteById(foodId);
        } else {
            throw new IllegalArgumentException("Food not found with id: " + foodId);
        }
    }

    @Override
    public Optional<Food> findById(Integer id) {
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
