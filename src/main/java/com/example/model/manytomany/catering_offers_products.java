package com.example.model.manytomany;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ketering_nudi_produkt")
public class catering_offers_products implements Serializable {
    @Id
    @Column(name = "korisnik_id")
    Integer user_id;

    @Id
    @Column(name = "produkt_id")
    Integer product_id;

    public catering_offers_products(Integer user_id, Integer product_id) {
        this.user_id = user_id;
        this.product_id = product_id;
    }

    public catering_offers_products() {
    }
}
