package com.example.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String address;

    String name;

    @Column(name = "phone_number")
    String phoneNumber;

    Integer price;

    @OneToMany(mappedBy = "location")
    List<Event> events;

    public Location(Integer id, String address, String name, String phoneNumber, Integer price, List<Event> events) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.price = price;
        this.events = events;
    }

    public Location() {
    }
}
