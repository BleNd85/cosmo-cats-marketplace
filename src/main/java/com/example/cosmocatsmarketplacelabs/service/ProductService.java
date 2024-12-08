package com.example.cosmocatsmarketplacelabs.service;

import com.example.cosmocatsmarketplacelabs.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product createProduct(Product product);

    Product updateProduct(Product product);

    void deleteProductById(Long id);
}