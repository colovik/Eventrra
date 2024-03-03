package com.example.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "hrana")
public class Food extends Product{
    @Column(name = "vegetarijansko")
    Boolean vegetarian;

    @Column(name = "kalorii")
    Integer calories;

    Boolean vegan;

    public Food(Integer product_id, String name) {
        super(product_id, name);
    }

    public Food() {
    }
}
