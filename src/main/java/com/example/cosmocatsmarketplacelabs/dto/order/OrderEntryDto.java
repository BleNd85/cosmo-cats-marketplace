package com.example.cosmocatsmarketplacelabs.dto.order;

import com.example.cosmocatsmarketplacelabs.dto.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntryDto {

    private Integer quantity;
    private ProductDTO product;
}
