package com.example.cosmocatsmarketplacelabs.service.mapper;

import com.example.cosmocatsmarketplacelabs.common.CategoryType;
import com.example.cosmocatsmarketplacelabs.domain.ProductDetails;
import com.example.cosmocatsmarketplacelabs.dto.product.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductServiceMapper {

    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "categoryType", source = "categoryType", qualifiedByName = "toCategoryString")
    @Named("toProductDto")
    ProductDTO toProductDto(ProductDetails productDetails);

    List<ProductDTO> toProductDto(List<ProductDetails> productDetailsList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productId", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "categoryType", source = "categoryType", qualifiedByName = "toCategoryType")
    @Mapping(target = "description", source = "description")
    ProductDetails toProductDetails(ProductDTO productDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "categoryType", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Named("toProductDetailsWithReference")
    ProductDetails toProductDetailsWithReference(ProductDTO productDto);

    @Named("toCategoryString")
    default String toCategoryString(CategoryType categoryType) {
        return categoryType.getDisplayName();
    }
    @Named("toCategoryType")
    default CategoryType toCategoryType(String categoryString) {
        return CategoryType.valueOf(categoryString.toUpperCase());
    }
}
