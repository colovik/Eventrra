package com.example.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "food")
public class Food extends Product {

    private Boolean vegetarian;

    private Integer calories;

    private Boolean vegan;

    private List<String> allergenIds;

    @DBRef
    private Catering catering;

    public Food(String productId, String name) {
        super(productId, name);
    }

    public Food() {
    }

    //Getters and setters
    public void setVegetarian(Boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public void setVegan(Boolean vegan) {
        this.vegan = vegan;
    }

    public void setAllergenIds(List<String> allergenIds) {
        this.allergenIds = allergenIds;
    }

    public void setCatering(Catering catering) {
        this.catering = catering;
    }

    public Boolean getVegetarian() {
        return vegetarian;
    }

    public Integer getCalories() {
        return calories;
    }

    public Boolean getVegan() {
        return vegan;
    }

    public List<String> getAllergenIds() {
        return allergenIds;
    }

    public Catering getCatering() {
        return catering;
    }


}
