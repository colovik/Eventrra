package com.example.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "pijaloci")
public class Drink extends Product{

    @Column(name = "dali_alkoholen")
    Boolean if_alcoholic;

    @Column(name = "procenti_alkohol")
    Integer percentage_alcohol;

    public Drink(Integer product_id, String name) {
        super(product_id, name);
    }

    public Drink() {
    }
}
