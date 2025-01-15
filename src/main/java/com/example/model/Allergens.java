package com.example.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "allergens")
public class Allergens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    @ManyToMany(mappedBy = "allergens")
    List<Food> foodList;

    public Allergens(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Allergens(String name) {
        this.name = name;
    }

    public Allergens() {

    }
}
