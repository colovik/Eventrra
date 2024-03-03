package com.example.model;

import com.example.model.Enumerations.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "admins")
public class Admin extends User{

    Integer numberEvents;

    @OneToMany(mappedBy = "admin")
    List<Event> events;

    public Admin(LocalDate dateCreated, String name, String username, String password, String phoneNumber, Role role, Integer numberEvents) {
        super(dateCreated, name, username, password, phoneNumber, role);
        this.numberEvents = numberEvents;
    }

    public Admin() {
    }

    public void setNumberEvents(Integer numberEvents) {
        this.numberEvents = numberEvents;
    }
}
