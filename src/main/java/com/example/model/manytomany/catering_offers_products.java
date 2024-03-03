package com.example.model.manytomany;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class catering_offers_products implements Serializable {
    @Id
    Integer idCatering;

    @Id
    Integer idProduct;

    public catering_offers_products(Integer idCatering, Integer idProduct) {
        this.idCatering = idCatering;
        this.idProduct = idProduct;
    }

    public catering_offers_products() {
    }
}
