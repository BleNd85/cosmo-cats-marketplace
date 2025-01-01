package com.example.cosmocatsmarketplacelabs.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.cosmocatsmarketplacelabs.common.CategoryType;
import com.example.cosmocatsmarketplacelabs.config.MappersTestConfiguration;
import com.example.cosmocatsmarketplacelabs.domain.ProductDetails;
import com.example.cosmocatsmarketplacelabs.repository.ProductRepository;
import com.example.cosmocatsmarketplacelabs.repository.entity.ProductEntity;
import com.example.cosmocatsmarketplacelabs.repository.mapper.ProductRepositoryMapper;
import com.example.cosmocatsmarketplacelabs.service.exception.ProductNotFoundException;
import com.example.cosmocatsmarketplacelabs.service.implementation.ProductServiceImplementation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest(classes = {ProductServiceImplementation.class})
@Import({MappersTestConfiguration.class})
@ExtendWith(MockitoExtension.class)
@DisplayName("Product Service Test")
public class ProductServiceImplementationTest {
    @Autowired
    private ProductServiceImplementation productService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductRepositoryMapper productRepositoryMapper;

    @Test
    void testGetAllProducts() {
        List<ProductEntity> productEntities = List.of(new ProductEntity());
        List<ProductDetails> productDetails = List.of(new ProductDetails());

        when(productRepository.findAll()).thenReturn(productEntities);
        when(productRepositoryMapper.toProductDetails(productEntities)).thenReturn(productDetails);

        List<ProductDetails> result = productService.getAllProducts();
        assertEquals(productDetails, result);
    }

    @Test
    void testGetProductById() {
        UUID productId = UUID.randomUUID();
        ProductEntity productEntity = new ProductEntity();
        ProductDetails productDetails = new ProductDetails();

        when(productRepository.findByNaturalId(productId)).thenReturn(Optional.of(productEntity));
        when(productRepositoryMapper.toProductDetails(productEntity)).thenReturn(productDetails);

        ProductDetails result = productService.getProductByProductId(productId);
        assertEquals(productDetails, result);
    }

    @Test
    void testGetProductByIdNotFound() {
        UUID productId = UUID.randomUUID();

        when(productRepository.findByNaturalId(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class,
                () -> productService.getProductByProductId(productId));
    }

    @Test
    void testSaveProduct() {
        ProductDetails productDetails = new ProductDetails();
        ProductEntity productEntity = new ProductEntity();
        ProductEntity savedEntity = new ProductEntity();
        ProductDetails savedDetails = new ProductDetails();

        when(productRepositoryMapper.toProductEntity(productDetails)).thenReturn(productEntity);
        when(productRepository.save(productEntity)).thenReturn(savedEntity);
        when(productRepositoryMapper.toProductDetails(savedEntity)).thenReturn(savedDetails);

        ProductDetails result = productService.saveProduct(productDetails);
        assertEquals(savedDetails, result);
    }

    @Test
    void testUpdateProduct() {
        UUID productId = UUID.randomUUID();
        ProductDetails productDetails = new ProductDetails();
        productDetails.setCategoryType(CategoryType.ACCESSORIES);
        ProductEntity existingEntity = new ProductEntity();
        ProductEntity savedEntity = new ProductEntity();
        ProductDetails savedDetails = new ProductDetails();

        when(productRepository.findByNaturalId(productId)).thenReturn(Optional.of(existingEntity));
        when(productRepository.save(existingEntity)).thenReturn(savedEntity);
        when(productRepositoryMapper.toProductDetails(savedEntity)).thenReturn(savedDetails);

        ProductDetails result = productService.saveProduct(productId, productDetails);
        assertEquals(savedDetails, result);
    }

    @Test
    void testUpdateProductNotFound() {
        UUID productId = UUID.randomUUID();
        ProductDetails productDetails = new ProductDetails();

        when(productRepository.findByNaturalId(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class,
                () -> productService.saveProduct(productId, productDetails));
    }

    @Test
    void testDeleteProduct() {
        UUID productId = UUID.randomUUID();
        ProductEntity entity = new ProductEntity();

        when(productRepository.findByNaturalId(productId)).thenReturn(Optional.of(entity));
        doNothing().when(productRepository).deleteByNaturalId(productId);

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).deleteByNaturalId(productId);
    }

}
