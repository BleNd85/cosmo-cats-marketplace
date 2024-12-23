package com.example.cosmocatsmarketplacelabs.service;

import com.example.cosmocatsmarketplacelabs.domain.ProductDetails;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductDetails> getAllProducts();

    ProductDetails getProductByProductId(UUID productReference);

    ProductDetails saveProduct(ProductDetails productDetails);

    ProductDetails saveProduct(UUID productReference, ProductDetails productDetails);

    void deleteProduct(UUID productReference);
}