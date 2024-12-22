package com.example.cosmocatsmarketplacelabs.repository.mapper;

import com.example.cosmocatsmarketplacelabs.domain.CosmicCatDetails;
import com.example.cosmocatsmarketplacelabs.repository.entity.CosmicCatEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = OrderRepositoryMapper.class)
public interface CosmicCatRepositoryMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "cosmicCatReference", source = "cosmicCatReference")
    @Mapping(target = "catName", source = "catName")
    @Mapping(target = "realName", source = "realName")
    @Mapping(target = "catMail", source = "catMail")
    @Mapping(target = "orders", source = "orders", qualifiedByName = "toOrderDetails")
    CosmicCatDetails toCosmicCatDetails(CosmicCatEntity cosmicCatEntity);

    List<CosmicCatDetails> toCosmicCatDetails(List<CosmicCatEntity> cosmicCatEntities);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "cosmicCatReference", source = "cosmicCatReference")
    @Mapping(target = "catName", source = "catName")
    @Mapping(target = "realName", source = "realName")
    @Mapping(target = "catMail", source = "catMail")
    @Mapping(target = "orders", source = "orders", qualifiedByName = "toOrderEntity")
    CosmicCatEntity toCosmicCatEntity(CosmicCatDetails cosmicCatDetails);

    List<CosmicCatEntity> toCosmicCatEntity(List<CosmicCatDetails> cosmicCatDetails);


}
