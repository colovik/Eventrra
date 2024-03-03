package com.example.model;

import com.example.model.Enumerations.Role;
import lombok.Data;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table (name = "fotografi")
public class Photographer extends User{

    @Column(name = "cena")
    Integer price;

    String portfolio;

    @ManyToMany(mappedBy = "photographerList")
    List<Event> eventList;

    public Photographer(String name, String username, String number, String password,
                        Integer price, String portfolio, Role role) {
        super(name, username, number, password, role);
        this.price = price;
        this.portfolio = portfolio;
    }

    public Photographer() {
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(Role.ROLE_PHOTOGRAPHER);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Photographer that = (Photographer) o;
        return id != null && Objects.equals(id, that.id);
    }
}
