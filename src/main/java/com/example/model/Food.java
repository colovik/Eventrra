package com.example.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "food")
public class Food extends Product {

    Boolean vegetarian;

    Integer calories;

    Boolean vegan;

    @ManyToMany
    @JoinTable(name = "food_contains_allergens",
            joinColumns = @JoinColumn(name = "idFood"),
            inverseJoinColumns = @JoinColumn(name = "idAllergen"))
    List<Allergens> allergens;

    public Food(Integer product_id, String name) {
        super(product_id, name);
    }

    public Food() {
    }
}
