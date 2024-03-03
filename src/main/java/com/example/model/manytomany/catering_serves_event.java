package com.example.model.manytomany;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class catering_serves_event implements Serializable {
    @Id
    Integer idCatering;

    @Id
    Integer idEvent;

    String status;

    public catering_serves_event(Integer idCatering, Integer idEvent, String status) {
        this.idCatering = idCatering;
        this.idEvent = idEvent;
        this.status = status;
    }

    public catering_serves_event() {
    }
}
