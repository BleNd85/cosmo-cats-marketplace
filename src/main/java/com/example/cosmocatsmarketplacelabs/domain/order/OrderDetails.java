package com.example.cosmocatsmarketplacelabs.domain.order;

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
public class OrderDetails {
    Long id;
    UUID orderId;
    String catName;
    List<OrderEntryDetails> orderItems;
}
