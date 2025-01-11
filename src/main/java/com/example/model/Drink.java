package com.example.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "drinks")
public class Drink extends Product{

    @Column(name = "is_alcoholic")
    Boolean isAlcoholic;

    @Column(name = "percents_alcohol")
    Integer percentsAlcohol;

    public Drink(Integer id, String name, Boolean isAlcoholic, Integer percentsAlcohol) {
        super(id, name);
        this.isAlcoholic = isAlcoholic;
        this.percentsAlcohol = percentsAlcohol;
    }

    public Drink() {
    }
}
