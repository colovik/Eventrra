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
    String address;

    @Column(name = "phone_number")
    String phoneNumber;

    Integer price;

    @OneToMany(mappedBy = "location")
    List<Event> events;

    public Location(String address, String phoneNumber, Integer price) {
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.price = price;
    }

    public Location() {
    }
}
