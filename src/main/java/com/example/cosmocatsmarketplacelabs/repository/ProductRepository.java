package com.example.cosmocatsmarketplacelabs.repository;

import com.example.cosmocatsmarketplacelabs.repository.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends NaturalIdRepository<ProductEntity, UUID> {
}
