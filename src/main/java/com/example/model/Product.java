package com.example.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    @ManyToMany(mappedBy = "productList")
    List<Catering> cateringList;

    public Product(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product() {
    }
}
