package com.example.cosmocatsmarketplacelabs.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.cosmocatsmarketplacelabs.common.CategoryType;
import com.example.cosmocatsmarketplacelabs.config.MappersTestConfiguration;
import com.example.cosmocatsmarketplacelabs.domain.ProductDetails;
import com.example.cosmocatsmarketplacelabs.service.exception.ProductNotFoundException;
import com.example.cosmocatsmarketplacelabs.service.implementation.ProductServiceImplementation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@SpringBootTest(classes = {ProductServiceImplementation.class})
@Import({MappersTestConfiguration.class})
@DisplayName("Product Service Test")
public class ProductDetailsServiceImplementationTest {
    @Autowired
    private ProductServiceImplementation productService;

    @Test
    void testGetAllProducts() {
        List<ProductDetails> productDetails = productService.getAllProducts();
        assertEquals(6, productDetails.size());
    }

    @Test
    void testGetProductById() {
        ProductDetails productDetails = productService.getProductById(2L);
        assertNotNull(productDetails);
        assertEquals(2L, productDetails.getId());
    }

    @Test
    void testGetProductByIdNotFound() {
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(100L));
    }

    @Test
    void testCreateProduct() {
        ProductDetails newProductDetails = ProductDetails.builder().name("Test product").description("Test product").build();
        ProductDetails createdProductDetails = productService.createProduct(newProductDetails);
        assertNotNull(createdProductDetails);
        assertEquals(7L, createdProductDetails.getId());
    }

    @Test
    void testUpdateProduct() {
        ProductDetails newProductDetails = ProductDetails.builder().id(1L).name("Updated Космічне молоко").categoryType(CategoryType.COSMOFOOD).description("Updated description").price(99.99).build();
        ProductDetails result = productService.updateProduct(newProductDetails);
        assertNotNull(result);
        assertEquals(newProductDetails.getName(), result.getName());
        assertEquals(newProductDetails.getDescription(), result.getDescription());
        assertEquals(newProductDetails.getPrice(), result.getPrice());
        assertEquals(newProductDetails.getCategoryType(), result.getCategoryType());
    }

    @Test
    void testUpdateProductNotFound() {
        ProductDetails newProductDetails = ProductDetails.builder().id(100L).name("Updated Космічне молоко").build();
        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(newProductDetails));
    }

    @Test
    void testDeleteProductById() {
        productService.deleteProductById(1L);
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(1L));
    }

    @Test
    void testDeleteProductByIdNotFound() {
        assertThrows(ProductNotFoundException.class, () -> productService.deleteProductById(100L));
    }
}
