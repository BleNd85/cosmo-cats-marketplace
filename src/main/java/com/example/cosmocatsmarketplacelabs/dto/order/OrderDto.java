package com.example.cosmocatsmarketplacelabs.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private UUID orderId;
    String catName;
    private List<OrderEntryDto> orderItems;
}
