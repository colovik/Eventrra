package com.example.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "drinks")
public class Drink extends Product{

    Boolean isAlcoholic;

    Integer percentsAlcohol;

    public Drink(Integer id, String name, Boolean isAlcoholic, Integer percentsAlcohol) {
        super(id, name);
        this.isAlcoholic = isAlcoholic;
        this.percentsAlcohol = percentsAlcohol;
    }

    public Drink() {
    }
}
