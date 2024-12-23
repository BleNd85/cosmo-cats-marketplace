package com.example.cosmocatsmarketplacelabs.repository;

import com.example.cosmocatsmarketplacelabs.repository.entity.CosmicCatEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CosmicCatRepository extends NaturalIdRepository<CosmicCatEntity, UUID> {

}
