package com.example.repository;

import com.example.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Integer> {

    List<Drink> findAllByIsAlcoholic (Boolean isAlcoholic);

    List<Drink> findAllByPercentsAlcoholLessThan (Integer before);
    List<Drink> findAllByPercentsAlcoholGreaterThan (Integer before);

    @Query("SELECT d FROM Drink d " +
            "JOIN catering_offers_products cop ON d.id = cop.id_product " +
            "WHERE cop.id_catering = :cateringId")
    List<Drink> findAllDrinksByCateringId(Integer cateringId);





}
