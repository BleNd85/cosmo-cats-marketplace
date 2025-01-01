package com.example.cosmocatsmarketplacelabs.web;

import com.example.cosmocatsmarketplacelabs.dto.product.ProductDTO;
import com.example.cosmocatsmarketplacelabs.featuretoggle.FeatureToggles;
import com.example.cosmocatsmarketplacelabs.featuretoggle.annotation.FeatureToggle;
import com.example.cosmocatsmarketplacelabs.service.ProductService;
import com.example.cosmocatsmarketplacelabs.service.mapper.ProductServiceMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final ProductServiceMapper productServiceMapper;

    public ProductController(ProductService productService, ProductServiceMapper productServiceMapper) {
        this.productService = productService;
        this.productServiceMapper = productServiceMapper;
    }

    @FeatureToggle(FeatureToggles.KITTY_PRODUCTS)
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productServiceMapper.toProductDto(productService.getAllProducts()));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductByProductId(@PathVariable UUID productId) {
        return ResponseEntity.ok(productServiceMapper.toProductDto(productService.getProductByProductId(productId)));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO productDTO) {
        return ResponseEntity.ok(productServiceMapper.toProductDto(
                productService.saveProduct(productServiceMapper.toProductDetails(productDTO))));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable UUID productId, @RequestBody @Valid ProductDTO productDTO) {
        return ResponseEntity.ok(productServiceMapper.toProductDto(productService
                .saveProduct(productId, productServiceMapper.toProductDetails(productDTO))));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
