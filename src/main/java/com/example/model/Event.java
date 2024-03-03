package com.example.model;

import com.example.model.Enumerations.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "nastani")
public class Event {

    @Id
    @Column(name = "nastan_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Enumerated(EnumType.STRING)
    Status status;

    @Column(name = "vreme")
    String time;
    @Column(name = "tip")
    String type;

    @Column(name = "datum")
    LocalDate date;

    @Column(name = "opis")
    String description;

    @ManyToOne
    @JoinColumn(name = "adresa")
    Location location;

    @ManyToOne
    @JoinColumn(name = "korisnik_id_admini")
    Admin admin;

    @ManyToOne
    @JoinColumn(name = "korisnik_id_klienti")
    Client client;

    @ManyToMany
    @JoinTable(name = "bend_sviri_nastan",
            joinColumns = @JoinColumn(name = "nastan_id"),
            inverseJoinColumns = @JoinColumn(name = "korisnik_id"))
//    @ToString.Exclude
    List<Band> bandList;

    @ManyToMany
    @JoinTable(name = "fotograf_slika_nastan",
            joinColumns = @JoinColumn(name = "nastan_id"),
            inverseJoinColumns = @JoinColumn(name = "korisnik_id"))
//    @ToString.Exclude
    List<Photographer> photographerList;

    @ManyToMany
    @JoinTable(name = "kelner_raboti_na_nastan",
            joinColumns = @JoinColumn(name = "nastan_id"),
            inverseJoinColumns = @JoinColumn(name = "korisnik_id"))
//    @ToString.Exclude
    List<Waiter> waiterList;

    @ManyToMany
    @JoinTable(name = "ketering_rezervira_nastan",
            joinColumns = @JoinColumn(name = "nastan_id"),
            inverseJoinColumns = @JoinColumn(name = "korisnik_id"))
//    @ToString.Exclude
    List<Catering> cateringList;

    public Event(String time, LocalDate date, Location location, String type,
                 String description, Client client, List<Band> bandList,
                 List<Catering> cateringList,
                 List<Photographer> photographerList, Admin admin
    ) {
        this.time = time;
        this.date = date;
        this.location = location;
        this.type = type;
        this.description = description;
        this.status = Status.CREATED;
        this.client = client;
        this.bandList = bandList;
        this.cateringList=cateringList;
        this.photographerList=photographerList;
        this.admin = admin;
    }



    public Event() {
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", status=" + status +
                ", time='" + time + '\'' +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", location=" + location.toString() +
                ", admin=" + admin.toString() +
                ", client=" + client.toString() +
                ", bandList=" + bandList +
                ", photographerList=" + photographerList +
                ", waiterList=" + waiterList +
                ", cateringList=" + cateringList +
                '}';
    }
}
