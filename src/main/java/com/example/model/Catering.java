package com.example.model;

import com.example.model.Enumerations.Role;
import lombok.Data;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "keterinzi")
public class Catering extends User {

    @Column(name = "cena")
    Integer price;

    @Column(name = "adresa")
    String address;

    @OneToMany(mappedBy = "catering_id")
    List<Client> clients;

    @OneToMany(mappedBy = "catering")
    List<Waiter> waiters;

    @ManyToMany
    @JoinTable(name = "ketering_nudi_produkt",
            joinColumns = @JoinColumn(name = "korisnik_id"),
            inverseJoinColumns = @JoinColumn(name = "produkt_id"))
//    @ToString.Exclude
    List<Product> productList;

    @ManyToMany(mappedBy = "cateringList")
    List<Event> eventList;

    public Catering(String name, String username, String number, String password,
                    Integer price, String address, Role role) {
        super(name, username, number, password, role);
        this.price = price;
        this.address = address;
    }

    public Catering() {
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(Role.ROLE_CATERING);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Catering that = (Catering) o;
        return id != null && Objects.equals(id, that.id);
    }
}
