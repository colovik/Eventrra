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
@Table(name = "caterings")
public class Catering extends User {

    Integer price;

    String address;

    @OneToMany(mappedBy = "catering")
    List<Waiter> waiters;

    @ManyToMany
    @JoinTable(name = "catering_offers_products",
            joinColumns = @JoinColumn(name = "id_catering"),
            inverseJoinColumns = @JoinColumn(name = "id_product"))
    List<Product> productList;

    @ManyToMany(mappedBy = "cateringList")
    List<Event> eventList;

    public Catering(LocalDate dateCreated, String name, String username, String password, String phoneNumber, Role role, Integer price, String address) {
        super(dateCreated, name, username, password, phoneNumber, role);
        this.price = price;
        this.address = address;
    }

    public Catering() {
    }
}
