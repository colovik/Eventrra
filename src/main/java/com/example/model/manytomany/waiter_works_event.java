package com.example.model.manytomany;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class waiter_works_event implements Serializable {
    @Id
    Integer idWaiter;

    @Id
    Integer idEvent;

    String status;

    public waiter_works_event(Integer idWaiter, Integer idEvent, String status) {
        this.idWaiter = idWaiter;
        this.idEvent = idEvent;
        this.status = status;
    }

    public waiter_works_event() {
    }
}
