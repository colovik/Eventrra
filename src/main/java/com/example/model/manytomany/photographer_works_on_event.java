package com.example.model.manytomany;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "fotograf_slika_nastan")
public class photographer_works_on_event implements Serializable {

    @Id
    @Column(name = "korisnik_id")
    Integer user_id;

    @Id
    @Column(name = "nastan_id")
    Integer event_id;

    String status;

    public photographer_works_on_event(Integer user_id, Integer event_id, String status) {
        this.user_id = user_id;
        this.event_id = event_id;
        this.status = status;
    }

    public photographer_works_on_event() {
    }
}