package com.example.cosmocatsmarketplacelabs.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.cosmocatsmarketplacelabs.config.MappersTestConfiguration;
import com.example.cosmocatsmarketplacelabs.domain.order.OrderDetails;
import com.example.cosmocatsmarketplacelabs.repository.CosmicCatRepository;
import com.example.cosmocatsmarketplacelabs.repository.OrderRepository;
import com.example.cosmocatsmarketplacelabs.repository.ProductRepository;
import com.example.cosmocatsmarketplacelabs.repository.entity.CosmicCatEntity;
import com.example.cosmocatsmarketplacelabs.repository.entity.OrderEntity;
import com.example.cosmocatsmarketplacelabs.repository.entity.ProductEntity;
import com.example.cosmocatsmarketplacelabs.repository.mapper.OrderRepositoryMapper;
import com.example.cosmocatsmarketplacelabs.repository.mapper.ProductRepositoryMapper;
import com.example.cosmocatsmarketplacelabs.service.exception.OrderNotFoundException;

import java.util.ArrayList;

import com.example.cosmocatsmarketplacelabs.service.implementation.OrderServiceImplementation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

@SpringBootTest(classes = {OrderServiceImplementation.class})
@Import(MappersTestConfiguration.class)
@ExtendWith(MockitoExtension.class)
@DisplayName("Order Service Test")
public class OrderServiceImplementationTest {

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductRepositoryMapper productRepositoryMapper;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private CosmicCatRepository cosmicCatRepository;

    @MockBean
    private OrderRepositoryMapper orderRepositoryMapper;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private OrderServiceImplementation orderService;

    @Test
    void testGetAllOrders() {
        List<OrderEntity> entities = List.of(new OrderEntity());
        List<OrderDetails> details = List.of(new OrderDetails());

        when(orderRepository.findAll()).thenReturn(entities);
        when(orderRepositoryMapper.toOrderDetails(entities)).thenReturn(details);

        List<OrderDetails> result = orderService.getOrders();
        assertEquals(details, result);
    }

    @Test
    void testGetOrderByOrderId() {
        UUID orderId = UUID.randomUUID();
        OrderEntity entity = new OrderEntity();
        OrderDetails details = new OrderDetails();

        when(orderRepository.findByNaturalId(orderId)).thenReturn(Optional.of(entity));
        when(orderRepositoryMapper.toOrderDetails(entity)).thenReturn(details);

        OrderDetails result = orderService.getOrderByOrderId(orderId);
        assertEquals(details, result);
    }

    @Test
    void testGetOrderByOrderIdNotFound() {
        UUID orderId = UUID.randomUUID();

        when(orderRepository.findByNaturalId(orderId)).thenReturn(Optional.empty());
        assertThrows(OrderNotFoundException.class, () -> orderService.getOrderByOrderId(orderId));
    }

    @Test
    void testSaveOrder() {
        UUID cosmicCatId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrderId(orderId);
        orderDetails.setOrderItems(new ArrayList<>());

        CosmicCatEntity cosmicCatEntity = new CosmicCatEntity();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(orderId);
        orderEntity.setCosmicCat(cosmicCatEntity);
        orderEntity.setOrderItems(new ArrayList<>());

        when(cosmicCatRepository.findByNaturalId(cosmicCatId)).thenReturn(Optional.of(cosmicCatEntity));
        when(productRepository.findByNaturalId(productId)).thenReturn(Optional.of(new ProductEntity()));
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);
        when(orderRepositoryMapper.toOrderDetails(any(OrderEntity.class))).thenReturn(orderDetails);
        when(orderRepositoryMapper.toOrderEntity(any(OrderDetails.class))).thenReturn(orderEntity);

        OrderDetails result = orderService.saveOrder(cosmicCatId, orderDetails);
        assertEquals(orderDetails, result);
    }

    @Test
    void testDeleteOrderById() {
        UUID orderId = UUID.randomUUID();
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrderId(orderId);

        when(orderRepository.findByNaturalId(orderId)).thenReturn(Optional.of(new OrderEntity()));
        doNothing().when(orderRepository).deleteByNaturalId(orderId);

        orderService.deleteOrder(orderId);
        verify(orderRepository, times(1)).deleteByNaturalId(orderId);
    }

}