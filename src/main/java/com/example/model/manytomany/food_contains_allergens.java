package com.example.model.manytomany;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class food_contains_allergens implements Serializable {
    @Id
    Integer idFood;

    @Id
    Integer idAllergen;

    public food_contains_allergens(Integer idFood, Integer idAllergen) {
        this.idFood = idFood;
        this.idAllergen = idAllergen;
    }

    public food_contains_allergens() {

    }
}
