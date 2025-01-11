package com.example.model.manytomany;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class band_plays_event implements Serializable {

    @Id
    Integer id_band;

    @Id
    Integer id_event;

    String status;

    public band_plays_event(Integer id_band, Integer id_event, String status) {
        this.id_band = id_band;
        this.id_event = id_event;
        this.status = status;
    }

    public band_plays_event() {
    }
}
