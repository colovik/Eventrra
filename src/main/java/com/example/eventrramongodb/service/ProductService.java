package com.example.eventrramongodb.service;

import com.example.eventrramongodb.model.Food;
import com.example.eventrramongodb.model.Product;

public interface ProductService {

    void updateProduct(Product product);

    void saveProduct(Product product);
}
