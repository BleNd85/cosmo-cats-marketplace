package com.example.cosmocatsmarketplacelabs.service.implementation;

import com.example.cosmocatsmarketplacelabs.domain.ProductDetails;
import com.example.cosmocatsmarketplacelabs.repository.ProductRepository;
import com.example.cosmocatsmarketplacelabs.repository.entity.ProductEntity;
import com.example.cosmocatsmarketplacelabs.repository.mapper.ProductRepositoryMapper;
import com.example.cosmocatsmarketplacelabs.service.ProductService;
import com.example.cosmocatsmarketplacelabs.service.exception.ProductNotFoundException;
import com.example.cosmocatsmarketplacelabs.service.mapper.ProductServiceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ProductServiceImplementation implements ProductService {
    private final ProductRepositoryMapper productRepositoryMapper;
    private final ProductRepository productRepository;

    public ProductServiceImplementation(ProductRepositoryMapper productRepositoryMapper, ProductRepository productRepository) {
        this.productRepositoryMapper = productRepositoryMapper;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDetails> getAllProducts() {
        return productRepositoryMapper.toProductDetails(productRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDetails getProductByProductId(UUID productReference) {
        return productRepositoryMapper.toProductDetails(productRepository.findByNaturalId(productReference)
                .orElseThrow(() -> new ProductNotFoundException(productReference)));
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public ProductDetails saveProduct(ProductDetails productDetails) {
        return productRepositoryMapper.toProductDetails(
                productRepository.save(productRepositoryMapper.toProductEntity(productDetails)));
    }


    @Override
    @Transactional(propagation = Propagation.NESTED)
    public ProductDetails saveProduct(UUID productReference, ProductDetails productDetails) {
        ProductEntity oldProduct = productRepository.findByNaturalId(productReference)
                .orElseThrow(() -> new ProductNotFoundException(productReference));
        oldProduct.setName(productDetails.getName());
        oldProduct.setDescription(productDetails.getDescription());
        oldProduct.setPrice(productDetails.getPrice());
        oldProduct.setCategoryType(productRepositoryMapper.categoryToList(productDetails.getCategoryType()));
        productRepository.save(oldProduct);
        return productRepositoryMapper.toProductDetails(oldProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(UUID productReference) {
        productRepository.findByNaturalId(productReference);
        productRepository.deleteByNaturalId(productReference);
    }

}
