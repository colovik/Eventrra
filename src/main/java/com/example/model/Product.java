package com.example.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "produkti")
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produkt_id")
    Integer product_id;

    @Column(name = "ime")
    String name;

    @ManyToMany(mappedBy = "productList")
    List<Catering> cateringList;
    public Product(Integer product_id, String name) {
        this.product_id = product_id;
        this.name = name;
    }

    public Product() {
    }
}
