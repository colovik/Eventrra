package com.example.model;

import com.example.model.Enumerations.Role;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "korisnici")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "korisnik_id")
    Integer id;
    @Column(name = "datum_kreiran")
    LocalDate date;
    @Column(name = "ime")
    String name;

    String username;
    @Column(name = "lozinka")
    String password;
    @Column(name = "tel_broj")
    String number;

    @Enumerated(value = EnumType.STRING)
    Role role;

    public User(String name, String username, String number, String password, Role role) {
        this.date = LocalDate.now();
        this.name = name;
        this.username = username;
        this.password = password;
        this.number = number;
        this.role = role;
    }

    public User(){

    }

//    //    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(Role.ROLE_USER);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User that = (User) o;
        return id != null && Objects.equals(id, that.id);
    }
}