package com.example.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
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

    public Allergens() {

    }
}
