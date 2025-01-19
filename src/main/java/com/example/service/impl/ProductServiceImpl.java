package com.example.service.impl;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void updateProduct(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public void saveProduct(Product product) {
        this.productRepository.save(product);
    }
}
