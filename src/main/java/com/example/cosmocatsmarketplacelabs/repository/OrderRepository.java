package com.example.cosmocatsmarketplacelabs.repository;

import com.example.cosmocatsmarketplacelabs.repository.entity.OrderEntity;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends NaturalIdRepository<OrderEntity, UUID> {
    List<OrderEntity> findByCosmicCatCosmicCatId(UUID cosmoCatId);
}
