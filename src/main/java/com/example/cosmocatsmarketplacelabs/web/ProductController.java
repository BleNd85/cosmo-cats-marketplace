package com.example.cosmocatsmarketplacelabs.web;

import com.example.cosmocatsmarketplacelabs.domain.ProductDetails;
import com.example.cosmocatsmarketplacelabs.dto.product.ProductDTO;
import com.example.cosmocatsmarketplacelabs.featuretoggle.FeatureToggles;
import com.example.cosmocatsmarketplacelabs.featuretoggle.annotation.FeatureToggle;
import com.example.cosmocatsmarketplacelabs.service.ProductService;
import com.example.cosmocatsmarketplacelabs.service.mapper.ProductServiceMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final ProductServiceMapper productServiceMapper;

    public ProductController(ProductService productService, ProductServiceMapper productServiceMapper) {
        this.productService = productService;
        this.productServiceMapper = productServiceMapper;
    }

    @FeatureToggle(FeatureToggles.KITTY_PRODUCTS)
    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productServiceMapper.toProductDtoList(productService.getAllProducts()));
    }

   /* @FeatureToggle(FeatureToggles.KITTY_PRODUCTS)
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productServiceMapper.toProductDto(productService.getProductById(id)));
    }

    @FeatureToggle(FeatureToggles.KITTY_PRODUCTS)
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO productDTO) {
        return ResponseEntity.ok(productServiceMapper.toProductDto(productService.createProduct(productServiceMapper.toProductDetails(productDTO))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO) {
        ProductDetails productDetails = productServiceMapper.toProductDetails(productDTO);
        productDetails.setId(id);
        return ResponseEntity.ok(productServiceMapper.toProductDto(productService.updateProduct(productDetails)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok("Product was successfully deleted");
    }*/

}
