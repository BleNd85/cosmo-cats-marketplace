package com.example.cosmocatsmarketplacelabs.web;

import com.example.cosmocatsmarketplacelabs.dto.order.OrderDto;
import com.example.cosmocatsmarketplacelabs.service.OrderService;
import com.example.cosmocatsmarketplacelabs.service.mapper.OrderServiceMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderServiceMapper orderServiceMapper;

    public OrderController(OrderService orderService, OrderServiceMapper orderServiceMapper) {
        this.orderService = orderService;
        this.orderServiceMapper = orderServiceMapper;
    }

    @PreAuthorize("hasRole('COSMO_ADMIN')")
    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders() {
        return ResponseEntity.ok(orderServiceMapper.toOrderDto(orderService.getOrders()));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable UUID orderId) {
        return ResponseEntity.ok(orderServiceMapper.toOrderDto(orderService.getOrderByOrderId(orderId)));
    }

    @GetMapping("/cosmic-cat/{cosmicCatId}")
    public ResponseEntity<List<OrderDto>> getCosmicCatOrders(@PathVariable UUID cosmicCatId) {
        return ResponseEntity.ok(orderServiceMapper.toOrderDto(orderService.getOrdersByCosmicCatId(cosmicCatId)));
    }

    @PostMapping("/cosmic-cat/{cosmicCatId}")
    public ResponseEntity<OrderDto> createOrder(@RequestBody @Valid OrderDto orderDto, @PathVariable UUID cosmicCatId) {
        return ResponseEntity.ok(orderServiceMapper.toOrderDto(orderService
                .saveOrder(cosmicCatId, orderServiceMapper.toOrderDetails(orderDto))));

    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
