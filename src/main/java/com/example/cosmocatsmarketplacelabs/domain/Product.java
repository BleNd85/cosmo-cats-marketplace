package com.example.cosmocatsmarketplacelabs.domain;

import com.example.cosmocatsmarketplacelabs.common.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private Long id;
    private Double price;
    private String name;
    private CategoryType categories;
    private String description;
}
