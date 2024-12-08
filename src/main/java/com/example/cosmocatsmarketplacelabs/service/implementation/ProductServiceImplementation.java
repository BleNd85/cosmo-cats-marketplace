package com.example.cosmocatsmarketplacelabs.service.implementation;

import com.example.cosmocatsmarketplacelabs.common.CategoryType;
import com.example.cosmocatsmarketplacelabs.domain.Product;
import com.example.cosmocatsmarketplacelabs.service.ProductService;
import com.example.cosmocatsmarketplacelabs.service.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {
    private final List<Product> listOfProducts = createProductList();

    @Override
    public List<Product> getAllProducts() {
        return listOfProducts;
    }

    @Override
    public Product getProductById(Long id) {
        return listOfProducts.stream()
                .filter(product -> product.getId().equals(id)).
                findFirst()
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product createProduct(Product product) {
        product.setId((long) listOfProducts.size() + 1);
        listOfProducts.add(product);
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        Product oldProduct = getProductById(product.getId());
        oldProduct.setCategories(product.getCategories());
        oldProduct.setName(product.getName());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPrice(product.getPrice());
        return oldProduct;
    }

    @Override
    public void deleteProductById(Long id) {
        listOfProducts.remove(getProductById(id));
    }

    private List<Product> createProductList() {
        List<Product> listOfProducts = new ArrayList<>();
        listOfProducts.add(Product.builder()
                .id(1L)
                .name("Космічне молоко")
                .categories(CategoryType.COSMOFOOD)
                .description("Молоко космічної корови")
                .build());
        listOfProducts.add(Product.builder()
                .id(2L)
                .name("Котячий скафандр")
                .categories(CategoryType.CLOTHES)
                .description("Спеціальний скафандр, що ІДЕАЛЬНО підходить для космічних котиків")
                .build());
        listOfProducts.add(Product.builder()
                .id(3L)
                .name("Космічна хапалка")
                .categories(CategoryType.DEVICES)
                .description("Котячий прилад, який допомагає космічним котикам у дослідах(У них же лапки!)")
                .build());
        listOfProducts.add(Product.builder()
                .id(4L)
                .name("Антигравітаційний клубок ниток")
                .categories(CategoryType.TOYS)
                .description("Літаючий клубок, що слідує за котиком, змінює кольори і дарує невагомі ігри в космосі!")
                .build());
        listOfProducts.add(Product.builder()
                .id(5L)
                .name("Жетон справжнього котика-космонавта")
                .categories(CategoryType.ACCESSORIES)
                .description("Блискучий символ галактичної відваги, який посвідчує, що його власник побував серед зірок і не раз ловив астероїди на льоту!")
                .build());
        listOfProducts.add(Product.builder()
                .id(6L)
                .name("Шерсть космічного котика")
                .categories(CategoryType.OTHER)
                .description("Легендарний матеріал, що переливається зоряним пилом, гріє в холод вакууму і ідеально підходить для плетіння міжгалактичних теплих шкарпеток!")
                .build());
        return listOfProducts;
    }
}
