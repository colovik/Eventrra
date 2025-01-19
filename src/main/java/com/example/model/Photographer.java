package com.example.model;

import com.example.model.Enumerations.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "photographers")
public class Photographer extends User {

    private Integer price;

    private String portfolio;

    private List<String> eventIds;

    public Photographer(LocalDate dateCreated, String name, String username,
                        String password, String phoneNumber, Role role,
                        Integer price, String portfolio) {
        super(dateCreated, name, username, password, phoneNumber, role);
        this.price = price;
        this.portfolio = portfolio;
        this.eventIds = new ArrayList<>();
    }

    public Photographer() {
    }

    //Getters and setters
    public void setPrice(Integer price) {
        this.price = price;
    }
    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public List<String> getEventIds() {
        return eventIds;
    }

    public Integer getPrice() {
        return price;
    }
}
