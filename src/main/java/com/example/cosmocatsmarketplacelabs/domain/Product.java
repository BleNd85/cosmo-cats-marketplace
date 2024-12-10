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
    Long id;
    Double price;
    String name;
    CategoryType category;
    String description;
}
