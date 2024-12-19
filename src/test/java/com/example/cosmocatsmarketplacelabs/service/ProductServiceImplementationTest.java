package com.example.cosmocatsmarketplacelabs.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.cosmocatsmarketplacelabs.common.CategoryType;
import com.example.cosmocatsmarketplacelabs.config.MappersTestConfiguration;
import com.example.cosmocatsmarketplacelabs.domain.Product;
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
public class ProductServiceImplementationTest {
    @Autowired
    private ProductServiceImplementation productService;

    @Test
    void testGetAllProducts() {
        List<Product> products = productService.getAllProducts();
        assertEquals(6, products.size());
    }

    @Test
    void testGetProductById() {
        Product product = productService.getProductById(1L);
        assertNotNull(product);
        assertEquals(1L, product.getId());
    }

    @Test
    void testGetProductByIdNotFound() {
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(100L));
    }

    @Test
    void testCreateProduct() {
        Product newProduct = Product.builder().name("Test product").description("Test product").build();
        Product createdProduct = productService.createProduct(newProduct);
        assertNotNull(createdProduct);
        assertEquals(7L, createdProduct.getId());
    }

    @Test
    void testUpdateProduct() {
        Product newProduct = Product.builder().id(1L).name("Updated Космічне молоко").category(CategoryType.COSMOFOOD).description("Updated description").price(99.99).build();
        Product result = productService.updateProduct(newProduct);
        assertNotNull(result);
        assertEquals(newProduct.getName(), result.getName());
        assertEquals(newProduct.getDescription(), result.getDescription());
        assertEquals(newProduct.getPrice(), result.getPrice());
        assertEquals(newProduct.getCategory(), result.getCategory());
    }

    @Test
    void testUpdateProductNotFound() {
        Product newProduct = Product.builder().id(100L).name("Updated Космічне молоко").build();
        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(newProduct));
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
