package com.example.model;

import com.example.model.Enumerations.Role;
import lombok.Data;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "kelneri")
public class Waiter extends User{

    @Column(name = "slobodni_denovi")
    Integer free_day;

    @Column(name = "god_iskustvo")
    Integer experience;

    @ManyToOne
    @JoinColumn(name = "korisnik_id_keterinzi")
    Catering catering;

    @ManyToMany(mappedBy = "waiterList")
    List<Event> eventList;

    public Waiter(String name, String username, String number, String password, Integer free_day,
                  Integer experience, Catering catering, Role role) {
        super(name, username, number, password, role);
        this.free_day = free_day;
        this.experience = experience;
        this.catering = catering;
    }

    public Waiter() {
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(Role.ROLE_WAITER);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Waiter that = (Waiter) o;
        return id != null && Objects.equals(id, that.id);
    }
}
