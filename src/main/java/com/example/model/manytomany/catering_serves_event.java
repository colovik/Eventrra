package com.example.model.manytomany;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class catering_serves_event implements Serializable {
    @Id
    Integer id_catering;

    @Id
    Integer id_event;

    String status;

    public catering_serves_event(Integer id_catering, Integer id_event, String status) {
        this.id_catering = id_catering;
        this.id_event = id_event;
        this.status = status;
    }

    public catering_serves_event() {
    }
}
