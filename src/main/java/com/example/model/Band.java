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
@Table(name = "bendovi")
public class Band extends User {

    @Column(name = "cena")
    Integer price;

    public Band(String name, String username, String number, String password, Integer price, Role role) {
        super(name, username, number, password, role);
        this.price = price;
    }

    @ManyToMany(mappedBy = "bandList")
    List<Event> eventList;
    public Band() {
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(Role.ROLE_BAND);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Band that = (Band) o;
        return id != null && Objects.equals(id, that.id);
    }
}
