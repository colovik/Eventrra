package com.example.eventrramongodb.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "allergens")
public class Allergens {

    @Id
    private String id;

    private String name;

    private List<String> foodIds;

    @DBRef
    private Food food;

    public Allergens(String id, String name) {
        this.id = id;
        this.name = name;
        this.foodIds = new ArrayList<>();
    }

    public Allergens(String name) {
        this.name = name;
    }

    public Allergens() {

    }

    //Getters and setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
