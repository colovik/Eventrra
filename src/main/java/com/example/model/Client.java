package com.example.model;

import com.example.model.Enumerations.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client extends User {

    @Column(name = "number_events")
    Integer numberEvents;

    @OneToMany(mappedBy = "client")
    List<Event> event;


    public Client(LocalDate dateCreated, String name, String username, String password, String phoneNumber, Role role, Integer numberEvents) {
        super(dateCreated, name, username, password, phoneNumber, role);
        this.numberEvents = numberEvents;
    }

    public Client() {
    }

    public void setNumberEvents(Integer numberEvents) {
        this.numberEvents = numberEvents;
    }
}
