package com.example.service.impl;

import com.example.exceptions.NoSuchIDException;
import com.example.model.Catering;
import com.example.model.Product;
import com.example.repository.CateringRepository;
import com.example.repository.ProductRepository;
import com.example.service.CateringService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CateringServiceImpl implements CateringService {

    private final CateringRepository cateringRepository;
    private final ProductRepository productRepository;

    public CateringServiceImpl(CateringRepository cateringRepository, ProductRepository productRepository) {
        this.cateringRepository = cateringRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Catering> findAll() {
        return this.cateringRepository.findAll();
    }

    @Override
    public Optional<Catering> findById(Integer Id) {
        return Optional.ofNullable(this.cateringRepository.findById(Id)
                .orElseThrow(() -> new NoSuchIDException(Id)));
    }

    @Override
    public Integer getCateringIdByName(String name) {
        Catering catering = cateringRepository.findByName(name);
        return catering != null ? catering.getId() : null;
    }

    @Override
    public void addProductToCateringOffer(Integer cateringId, Integer productId) {
        Catering catering = cateringRepository.findById(cateringId).orElseThrow(() -> new IllegalArgumentException("Invalid catering ID"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));
        catering.getProductList().add(product);
        cateringRepository.save(catering);
    }

    @Override
    public void deleteProductFromCateringOffer(Integer cateringId, Integer productId) {
        Catering catering = cateringRepository.findById(cateringId).orElseThrow(() -> new IllegalArgumentException("Invalid catering ID"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));
        catering.getProductList().remove(product);
    }

    @Override
    public boolean existsById(Integer id) {
        return this.cateringRepository.existsById(id);
    }
}
