package com.example.cosmocatsmarketplacelabs.domain.order;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class Order {
    Long id;
    String customerName;
    List<OrderItem> orderItems;
    Double totalPrice;
}
