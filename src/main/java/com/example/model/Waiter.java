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
@Table(name = "waiters")
public class Waiter extends User{

    Integer daysOff;

    Integer yearsOfExperience;

    @ManyToOne
    @JoinColumn(name = "idCatering")
    Catering catering;

    @ManyToMany(mappedBy = "waiterList")
    List<Event> eventList;

    public Waiter(LocalDate dateCreated, String name, String username, String password, String phoneNumber, Role role, Integer daysOff, Integer yearsOfExperience, Catering catering) {
        super(dateCreated, name, username, password, phoneNumber, role);
        this.daysOff = daysOff;
        this.yearsOfExperience = yearsOfExperience;
        this.catering = catering;
    }

    public Waiter() {
    }
}
