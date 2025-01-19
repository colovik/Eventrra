package com.example.eventrramongodb.model;

import com.example.eventrramongodb.model.Enumerations.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "bands")
public class Band extends User {

    private Integer price;

    private List<String> eventIds;

    public Band(LocalDate dateCreated, String name, String username, String password, String phoneNumber, Role role, Integer price) {
        super(dateCreated, name, username, password, phoneNumber, role);
        this.price = price;
        this.eventIds = new ArrayList<>();
    }

    public Band() {

    }

    //Getters and setters
    public Integer getPrice() {
        return price;
    }

    public List<String> getEventIds() {
        return eventIds;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setEventIds(List<String> eventIds) {
        this.eventIds = eventIds;
    }
}
