package com.example.model.manytomany;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class waiter_works_event implements Serializable {
    @Id
    Integer id_waiter;

    @Id
    Integer id_event;

    String status;

    public waiter_works_event(Integer id_waiter, Integer id_event, String status) {
        this.id_waiter = id_waiter;
        this.id_event = id_event;
        this.status = status;
    }

    public waiter_works_event() {
    }
}
