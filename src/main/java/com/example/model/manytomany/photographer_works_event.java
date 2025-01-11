package com.example.model.manytomany;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class photographer_works_event implements Serializable {

    @Id
    Integer id_photographer;

    @Id
    Integer id_event;

    String status;

    public photographer_works_event(Integer id_photographer, Integer id_event, String status) {
        this.id_photographer = id_photographer;
        this.id_event = id_event;
        this.status = status;
    }

    public photographer_works_event() {
    }
}
