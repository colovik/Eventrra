package com.example.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "drinks")
public class Drink extends Product {

    private Boolean isAlcoholic;

    private Integer percentsAlcohol;

    @DBRef
    private Catering catering;

    public Drink(String id, String name, Boolean isAlcoholic, Integer percentsAlcohol) {
        super(id, name);
        this.isAlcoholic = isAlcoholic;
        this.percentsAlcohol = percentsAlcohol;
    }

    public Drink() {
    }

    //Getters and setters
    public void setIsAlcoholic(Boolean alcoholic) {
        isAlcoholic = alcoholic;
    }

    public void setPercentsAlcohol(Integer percentsAlcohol) {
        this.percentsAlcohol = percentsAlcohol;
    }

    public void setCatering(Catering catering) {
        this.catering = catering;
    }

    public Boolean getIsAlcoholic() {
        return isAlcoholic;
    }

    public Integer getPercentsAlcohol() {
        return percentsAlcohol;
    }

    public Catering getCatering() {
        return catering;
    }


}
