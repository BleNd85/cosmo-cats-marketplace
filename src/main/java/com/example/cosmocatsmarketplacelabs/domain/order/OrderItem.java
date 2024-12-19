package com.example.cosmocatsmarketplacelabs.domain.order;


import com.example.cosmocatsmarketplacelabs.domain.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItem {
    Product product;
    Integer quantity;
}
