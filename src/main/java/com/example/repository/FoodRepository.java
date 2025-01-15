package com.example.repository;

import com.example.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
    @Query("SELECT f FROM Food f " +
            "JOIN catering_offers_products cop ON f.id = cop.id_product " +
            "WHERE cop.id_catering = :cateringId")
    List<Food> findAllFoodByCateringId(Integer cateringId);
}
