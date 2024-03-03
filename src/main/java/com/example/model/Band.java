package com.example.model;

import com.example.model.Enumerations.Role;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "bands")
public class Band extends User {

    Integer price;

    @ManyToMany(mappedBy = "bandList")
    List<Event> eventList;

    public Band(LocalDate dateCreated, String name, String username, String password, String phoneNumber, Role role, Integer price) {
        super(dateCreated, name, username, password, phoneNumber, role);
        this.price = price;
    }

    public Band() {
    }
}
