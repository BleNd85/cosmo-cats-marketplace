package com.example.cosmocatsmarketplacelabs.service;


import com.example.cosmocatsmarketplacelabs.domain.order.OrderDetails;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<OrderDetails> getOrders();

    List<OrderDetails> getOrdersByCosmicCatId(UUID cosmoCatId);

    OrderDetails getOrderByOrderId(UUID orderId);

    List<UUID> getOrdersIdByCosmicCatId(UUID cosmicCatId);

    OrderDetails saveOrder(UUID cosmicCatId, OrderDetails orderDetails);

    void deleteOrder(UUID orderId);

}
