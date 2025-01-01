package com.example.cosmocatsmarketplacelabs.repository;

import com.example.cosmocatsmarketplacelabs.repository.entity.OrderEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderRepositoryTest {

    @Mock
    private OrderRepository orderRepository;

    @Test
    void shouldFindByCosmicCatCosmicCatId() {
        UUID cosmicCatId = UUID.randomUUID();
        UUID orderId1 = UUID.randomUUID();
        UUID orderId2 = UUID.randomUUID();
        OrderEntity order1 = new OrderEntity().toBuilder()
                .orderId(orderId1)
                .orderItems(List.of())
                .build();
        OrderEntity order2 = new OrderEntity().toBuilder()
                .orderId(orderId2)
                .orderItems(List.of())
                .build();

        when(orderRepository.findByCosmicCatCosmicCatId(cosmicCatId)).thenReturn(List.of(order1, order2));

        List<OrderEntity> result = orderRepository.findByCosmicCatCosmicCatId(cosmicCatId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(orderId1, result.get(0).getOrderId());
        assertEquals(orderId2, result.get(1).getOrderId());
    }
}
