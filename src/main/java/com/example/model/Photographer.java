package com.example.model;

import com.example.model.Enumerations.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "photographers")
public class Photographer extends User {

    Integer price;

    String portfolio;

    @ManyToMany(mappedBy = "photographerList")
    List<Event> eventList;

    public Photographer(LocalDate dateCreated, String name, String username, String password, String phoneNumber, Role role, Integer price, String portfolio) {
        super(dateCreated, name, username, password, phoneNumber, role);
        this.price = price;
        this.portfolio = portfolio;
    }

    public Photographer() {
    }
}
