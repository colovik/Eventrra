package com.example.service;

import com.example.model.Catering;

import java.util.List;
import java.util.Optional;

public interface CateringService {
    List<Catering> findAll();

    Optional<Catering> findById(Integer id);

    Integer getCateringIdByName(String name);

    void addProductToCateringOffer(Integer cateringId, Integer productId);

    void deleteProductFromCateringOffer(Integer cateringId, Integer productId);

    boolean existsById(Integer id);
}
