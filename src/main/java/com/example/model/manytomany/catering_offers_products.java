package com.example.model.manytomany;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class catering_offers_products implements Serializable {
    @Id
    Integer id_catering;

    @Id
    Integer id_product;

    public catering_offers_products(Integer id_catering, Integer id_product) {
        this.id_catering = id_catering;
        this.id_product = id_product;
    }

    public catering_offers_products() {
    }
}
