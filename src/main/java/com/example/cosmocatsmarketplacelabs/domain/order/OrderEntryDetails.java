package com.example.cosmocatsmarketplacelabs.domain.order;


import com.example.cosmocatsmarketplacelabs.domain.ProductDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntryDetails {
    Long id;
    ProductDetails product;
    Integer quantity;
}
