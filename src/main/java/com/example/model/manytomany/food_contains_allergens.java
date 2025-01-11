package com.example.model.manytomany;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class food_contains_allergens implements Serializable {
    @Id
    Integer id_food;

    @Id
    Integer id_allergen;

    public food_contains_allergens(Integer id_food, Integer id_allergen) {
        this.id_food = id_food;
        this.id_allergen = id_allergen;
    }

    public food_contains_allergens() {

    }
}
