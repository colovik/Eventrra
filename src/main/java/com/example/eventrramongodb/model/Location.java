package com.example.eventrramongodb.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "locations")
public class Location {

    @Id
    private String id;

    private String address;

    private String name;

    private String phoneNumber;

    private Integer price;

    private List<String> eventIds;

    public Location(String id, String address, String name, String phoneNumber, Integer price) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.price = price;
        this.eventIds = new ArrayList<>();
    }

    public Location() {
    }

    //Getters and setters
    public void setId(String id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Integer getPrice() {
        return price;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<String> getEventIds() {
        return eventIds;
    }
}
