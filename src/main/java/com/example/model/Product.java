package com.example.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "products")
public class Product {

    @Id
    private String id;

    private String name;

    private List<String> cateringIds;

    public Product(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product() {
    }

    //Getters and setters
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
