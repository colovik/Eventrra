package com.example.model.manytomany;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class photographer_works_event implements Serializable {

    @Id
    Integer idPhotographer;

    @Id
    Integer idEvent;

    String status;

    public photographer_works_event(Integer idPhotographer, Integer idEvent, String status) {
        this.idPhotographer = idPhotographer;
        this.idEvent = idEvent;
        this.status = status;
    }

    public photographer_works_event() {
    }
}
