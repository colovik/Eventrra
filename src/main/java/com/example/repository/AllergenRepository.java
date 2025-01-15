package com.example.repository;

import com.example.model.Allergens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllergenRepository extends JpaRepository<Allergens, Integer> {
    @Query("SELECT a FROM Allergens a " +
            "JOIN food_contains_allergens fca ON a.id = fca.id_allergen " +
            "WHERE fca.id_food = :foodId")
    List<Allergens> findAllAllergensByFoodId(Integer foodId);
}
