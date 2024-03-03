package com.example.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "lokacii")
public class Location {

    @Id
    @Column(name = "adresa")
    String address;

    @Column(name = "cena")
    Integer price;

    @OneToMany(mappedBy = "location")
    List<Event> events;

    public Location(String address, Integer price) {
        this.address = address;
        this.price = price;
    }

    public Location() {
    }

    @Override
    public String toString() {
        return address;
    }
}
