package com.example.cosmocatsmarketplacelabs.repository.mapper;

import com.example.cosmocatsmarketplacelabs.common.CategoryType;
import com.example.cosmocatsmarketplacelabs.domain.ProductDetails;
import com.example.cosmocatsmarketplacelabs.repository.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductRepositoryMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "categoryType", source = "categoryType")
    @Named("toProductDetails")
    ProductDetails toProductDetails(ProductEntity productEntity);

    List<ProductDetails> toProductDetails(List<ProductEntity> productEntity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "categoryType", source = "categoryType")
    @Named("toProductEntity")
    ProductEntity toProductEntity(ProductDetails productDetails);

    List<ProductEntity> toProductEntity(List<ProductDetails> productDetails);

    default List<CategoryType> categoryToList(CategoryType categoryType) {
        List<CategoryType> categories = new ArrayList<>();
        if (categoryType != null) {
            categories.add(categoryType);
        }
        return categories;
    }

    default CategoryType listToCategoryType(List<CategoryType> categoryType) {
        return categoryType != null && !categoryType.isEmpty() ? categoryType.get(0) : null;
    }
}
