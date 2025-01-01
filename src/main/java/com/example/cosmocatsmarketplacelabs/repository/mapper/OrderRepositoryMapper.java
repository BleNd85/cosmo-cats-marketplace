package com.example.cosmocatsmarketplacelabs.repository.mapper;

import com.example.cosmocatsmarketplacelabs.domain.order.OrderDetails;
import com.example.cosmocatsmarketplacelabs.domain.order.OrderEntryDetails;
import com.example.cosmocatsmarketplacelabs.repository.entity.OrderEntity;
import com.example.cosmocatsmarketplacelabs.repository.entity.OrderEntryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = ProductRepositoryMapper.class)
public interface OrderRepositoryMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "orderItems", source = "orderItems", qualifiedByName = "toOrderEntryDetails")
    @Named("toOrderDetails")
    OrderDetails toOrderDetails(OrderEntity orderEntity);

    List<OrderDetails> toOrderDetails(List<OrderEntity> orderEntities);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "product", source = "product", qualifiedByName = "toProductDetails")
    @Mapping(target = "quantity", source = "quantity")
    @Named("toOrderEntryDetails")
    OrderEntryDetails toOrderEntryDetails(OrderEntryEntity orderEntryEntity);

    List<OrderEntryDetails> toOrderEntryDetails(List<OrderEntryEntity> orderEntryEntities);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "orderItems", source = "orderItems", qualifiedByName = "toOrderEntryEntity")
    @Named("toOrderEntity")
    OrderEntity toOrderEntity(OrderDetails orderDetails);

    List<OrderEntity> toOrderEntity(List<OrderDetails> orderDetails);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "product", source = "product", qualifiedByName = "toProductEntity")
    @Mapping(target = "quantity", source = "quantity")
    @Named("toOrderEntryEntity")
    OrderEntryEntity toOrderEntryEntity(OrderEntryDetails orderEntryDetails);

    List<OrderEntryEntity> toOrderEntryEntity(List<OrderEntryDetails> orderEntryDetails);
}
