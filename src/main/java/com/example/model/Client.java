package com.example.model;

import com.example.model.Enumerations.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "klienti")
public class Client extends User {
    @Column(name = "br_organizirani_nastani")
    Integer number_events;

    @OneToMany(mappedBy = "client")
    List<Event> event;

    @ManyToOne
    @JoinColumn(name = "korisnik_id_keterinzi")
    Catering catering_id;

//    @OneToMany(mappedBy = "client")
//    List<Event> events;


    public Client(String name, String username, String number, String password, Role role) {
        super(name, username, number, password, role);
        this.number_events = 0;
    }

    public Client() {

    }

    //    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(Role.ROLE_CLIENT);
//    }

    @Override
    public String toString() {
        return "Client{" +
                "number_events=" + number_events +
                ", event=" + event.toString() +
                ", catering_id=" + catering_id +
                ", id=" + id +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
