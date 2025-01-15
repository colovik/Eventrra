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
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Enumerated(EnumType.STRING)
    Status status;

    String time;

    String type;

    LocalDate date;

    String description;

    @ManyToOne
    @JoinColumn(name = "id_location", nullable = false)
    Location location;

    @ManyToOne
    @JoinColumn(name = "id_admin")
    Admin admin;

    @ManyToOne
    @JoinColumn(name = "id_client")
    Client client;

    @ManyToMany
    @JoinTable(name = "band_plays_event",
            joinColumns = @JoinColumn(name = "id_event"),
            inverseJoinColumns = @JoinColumn(name = "id_band"))
    List<Band> bandList;

    @ManyToMany
    @JoinTable(name = "photographer_works_events",
            joinColumns = @JoinColumn(name = "id_event"),
            inverseJoinColumns = @JoinColumn(name = "id_photographer"))
    List<Photographer> photographerList;

    @ManyToMany
    @JoinTable(name = "catering_serves_event",
            joinColumns = @JoinColumn(name = "id_event"),
            inverseJoinColumns = @JoinColumn(name = "id_catering"))
    List<Catering> cateringList;

    public Event(String time, LocalDate date, Location location, String type,
                 String description, Client client, List<Band> bandList,
                 List<Catering> cateringList,
                 List<Photographer> photographerList, Admin admin, Status status
    ) {
        this.time = time;
        this.date = date;
        this.location = location;
        this.type = type;
        this.description = description;
        this.status = status;
        this.client = client;
        this.bandList = bandList;
        this.cateringList = cateringList;
        this.photographerList = photographerList;
        this.admin = admin;
    }

    public Event() {
    }
}