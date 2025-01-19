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
@Document(collection = "clients")
public class Client extends User {

    private Integer numberEvents;

    private List<String> eventIds;

    public Client(LocalDate dateCreated, String name, String username, String password, String phoneNumber, Role role, Integer numberEvents) {
        super(dateCreated, name, username, password, phoneNumber, role);
        this.numberEvents = numberEvents;
        this.eventIds = new ArrayList<>();
    }

    public Client() {
    }

    //Getters and setters
    public Integer getNumberEvents() {
        return numberEvents;
    }

    public void setNumberEvents(Integer numberEvents) {
        this.numberEvents = numberEvents;
    }

    public List<String> getEventIds() {
        return eventIds;
    }

}
