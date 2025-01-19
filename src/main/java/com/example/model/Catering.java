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
@Document(collection = "caterings")
public class Catering extends User {

    private Integer price;

    private String address;

    private List<String> productIds;

    private List<String> eventIds;

    public Catering(LocalDate dateCreated, String name, String username,
                    String password, String phoneNumber, Role role, Integer price, String address) {
        super(dateCreated, name, username, password, phoneNumber, role);
        this.price = price;
        this.address = address;
        this.productIds = new ArrayList<>();
        this.eventIds = new ArrayList<>();
    }

    public Catering() {

    }

    //Getters and setters
    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }

    public void setEventIds(List<String> eventIds) {
        this.eventIds = eventIds;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public List<String> getEventIds() {
        return eventIds;
    }

    public Integer getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }
}
