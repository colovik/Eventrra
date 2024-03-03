package com.example.model;

import com.example.model.Enumerations.Role;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "admini")
public class Admin extends User{

    @Column(name = "br_organizirani_nastani")
    Integer number_events;

    @OneToMany(mappedBy = "admin")
    List<Event> events;

    public Admin(String name, String username, String number, String password, Role role) {
        super(name, username, number, password, role);
        this.number_events = 0;
    }

    public Admin() {
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(Role.ROLE_ADMIN);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Admin that = (Admin) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "number_events=" + number_events +
                ", events=" + events.toString() +
                ", id=" + id +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
