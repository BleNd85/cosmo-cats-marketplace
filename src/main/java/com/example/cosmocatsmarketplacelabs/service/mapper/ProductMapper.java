package com.example.cosmocatsmarketplacelabs.service.mapper;

import com.example.cosmocatsmarketplacelabs.common.CategoryType;
import com.example.cosmocatsmarketplacelabs.domain.Product;
import com.example.cosmocatsmarketplacelabs.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "name", source = "name")
    @Mapping(target = "category", source = "category", qualifiedByName = "toCategoryString")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "description", source = "description")
    ProductDTO toProductDto(Product product);

    List<ProductDTO> toProductDtoList(List<Product> products);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "category", source = "category", qualifiedByName = "toCategoryType")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "description", source = "description")
    Product toProduct(ProductDTO productDto);

    @Named("toCategoryString")
    default String toCategoryString(CategoryType categoryType) {
        return categoryType.getDisplayName();
    }

    @Named("toCategoryType")
    default CategoryType toCategoryType(String categoryString) {
        return CategoryType.valueOf(categoryString.toUpperCase());
    }
}
