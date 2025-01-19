package com.example.eventrramongodb.service.impl;

import com.example.eventrramongodb.model.Food;
import com.example.eventrramongodb.model.Product;
import com.example.eventrramongodb.repository.ProductRepository;
import com.example.eventrramongodb.service.ProductService;
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
