package com.example.model.manytomany;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ketering_rezervira_nastan")
public class catering_reserved_for_event implements Serializable {
    @Id
    @Column(name = "korisnik_id")
    Integer user_id;

    @Id
    @Column(name = "nastan_id")
    Integer event_id;

    String status;

    @Column(name = "kolicina")
    Integer quantity;

    public catering_reserved_for_event(Integer user_id, Integer event_id, String status, Integer quantity) {
        this.user_id = user_id;
        this.event_id = event_id;
        this.status = status;
        this.quantity = quantity;
    }

    public catering_reserved_for_event() {
    }
}
