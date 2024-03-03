package com.example.model.manytomany;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class band_plays_event implements Serializable {

    @Id
    Integer idBand;

    @Id
    Integer idEvent;

    String status;

    public band_plays_event(Integer idBand, Integer idEvent, String status) {
        this.idBand = idBand;
        this.idEvent = idEvent;
        this.status = status;
    }

    public band_plays_event() {
    }
}
