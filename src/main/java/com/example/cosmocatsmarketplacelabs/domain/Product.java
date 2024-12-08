package com.example.cosmocatsmarketplacelabs.domain;

import com.example.cosmocatsmarketplacelabs.common.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private Long id;
    private Double price;
    private String name;
    private List<CategoryType> categories;
    private String description;
}
