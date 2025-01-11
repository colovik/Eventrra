package com.example.model;

import com.example.model.Enumerations.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "date_created")
    LocalDate dateCreated;

    String name;

    String username;

    String password;

    @Column(name = "phone_number")
    String phoneNumber;

    @Enumerated(value = EnumType.STRING)
    Role role;

    public User(LocalDate dateCreated, String name, String username, String password, String phoneNumber, Role role) {
        this.dateCreated = dateCreated;
        this.name = name;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public User() {

    }
}