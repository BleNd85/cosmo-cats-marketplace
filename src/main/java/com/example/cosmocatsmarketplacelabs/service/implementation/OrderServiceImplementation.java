package com.example.cosmocatsmarketplacelabs.service.implementation;

import com.example.cosmocatsmarketplacelabs.domain.order.OrderDetails;
import com.example.cosmocatsmarketplacelabs.repository.CosmicCatRepository;
import com.example.cosmocatsmarketplacelabs.repository.OrderRepository;
import com.example.cosmocatsmarketplacelabs.repository.ProductRepository;
import com.example.cosmocatsmarketplacelabs.repository.entity.CosmicCatEntity;
import com.example.cosmocatsmarketplacelabs.repository.entity.OrderEntity;
import com.example.cosmocatsmarketplacelabs.repository.entity.OrderEntryEntity;
import com.example.cosmocatsmarketplacelabs.repository.entity.ProductEntity;
import com.example.cosmocatsmarketplacelabs.repository.mapper.OrderRepositoryMapper;
import com.example.cosmocatsmarketplacelabs.repository.mapper.ProductRepositoryMapper;
import com.example.cosmocatsmarketplacelabs.service.OrderService;
import com.example.cosmocatsmarketplacelabs.service.ProductService;
import com.example.cosmocatsmarketplacelabs.service.exception.CosmicCatNofFoundException;
import com.example.cosmocatsmarketplacelabs.service.exception.OrderNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImplementation implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderRepositoryMapper orderRepositoryMapper;
    private final CosmicCatRepository cosmicCatRepository;
    private final ProductRepository productRepository;
    private final ProductRepositoryMapper productRepositoryMapper;
    private final ProductService productService;

    public OrderServiceImplementation(OrderRepository orderRepository, OrderRepositoryMapper orderRepositoryMapper,
                                      CosmicCatRepository cosmicCatRepository, ProductRepository productRepository, ProductRepositoryMapper productRepositoryMapper, ProductService productService) {
        this.orderRepository = orderRepository;
        this.orderRepositoryMapper = orderRepositoryMapper;
        this.cosmicCatRepository = cosmicCatRepository;
        this.productRepository = productRepository;
        this.productRepositoryMapper = productRepositoryMapper;
        this.productService = productService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDetails> getOrders() {
        return orderRepositoryMapper.toOrderDetails(orderRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDetails getOrderByOrderId(UUID orderId) {
        return orderRepositoryMapper.toOrderDetails(orderRepository.findByNaturalId(orderId).orElseThrow(() -> new OrderNotFoundException(orderId)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDetails> getOrdersByCosmicCatId(UUID cosmoCatId) {
        return orderRepositoryMapper.toOrderDetails(orderRepository.findByCosmicCatCosmicCatId(cosmoCatId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UUID> getOrdersIdByCosmicCatId(UUID cosmicCatId) {
        return List.of();
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public OrderDetails saveOrder(UUID cosmicCatId, OrderDetails orderDetails) {
        CosmicCatEntity cosmicCat = cosmicCatRepository.findByNaturalId(cosmicCatId)
                .orElseThrow(() -> new CosmicCatNofFoundException(cosmicCatId));
        OrderEntity order = orderRepositoryMapper.toOrderEntity(orderDetails);
        List<OrderEntryEntity> orderEntryEntityList = order.getOrderItems().stream()
                .peek(orderEntryItem -> {
                    UUID productId = orderEntryItem.getProduct().getProductId();
                    ProductEntity productEntity = productRepositoryMapper.toProductEntity(productService.getProductByProductId(productId));
                    orderEntryItem.setProduct(productEntity);
                    orderEntryItem.setOrder(order);
                }).toList();
        order.setOrderItems(orderEntryEntityList);
        order.setCosmicCat(cosmicCat);
        return orderRepositoryMapper.toOrderDetails(orderRepository.save(order));
    }

    @Override
    @Transactional
    public void deleteOrder(UUID orderId) {
        orderRepository.deleteByNaturalId(orderId);
    }
}
