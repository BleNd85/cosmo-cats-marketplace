package com.example.cosmocatsmarketplacelabs.domain;

import com.example.cosmocatsmarketplacelabs.common.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProductDetails {
    Long id;
    UUID productId;
    Double price;
    String name;
    CategoryType categoryType;
    String description;
}
