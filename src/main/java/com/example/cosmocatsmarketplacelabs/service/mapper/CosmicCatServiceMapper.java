package com.example.cosmocatsmarketplacelabs.service.mapper;

import com.example.cosmocatsmarketplacelabs.domain.CosmicCatDetails;
import com.example.cosmocatsmarketplacelabs.domain.order.OrderDetails;
import com.example.cosmocatsmarketplacelabs.dto.cat.CosmicCatDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;


@Mapper(componentModel = "spring")
public interface CosmicCatServiceMapper {
    @Mapping(source = "cosmicCatId", target = "cosmicCatId")
    @Mapping(source = "catName", target = "catName")
    @Mapping(source = "realName", target = "realName")
    @Mapping(source = "catMail", target = "catMail")
    @Mapping(source = "orders", target = "orders", qualifiedByName = "toOrderUUID")
    CosmicCatDto toCosmicCatDTO(CosmicCatDetails cosmicCatDetails);

    List<CosmicCatDto> toCosmicCatDTO(List<CosmicCatDetails> cosmicCatDetails);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cosmicCatId", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "catName", source = "catName")
    @Mapping(source = "realName", target = "realName")
    @Mapping(source = "catMail", target = "catMail")
    @Mapping(target = "orders", ignore = true)
    CosmicCatDetails toCosmicCatDetails(CosmicCatDto cosmicCatDTO);

    List<CosmicCatDetails> toCosmicCatDetails(List<CosmicCatDto> cosmicCatDTO);


    @Named("toOrderUUID")
    default List<UUID> toOrderUUID(List<OrderDetails> orders) {
        if (orders == null) {
            return null;
        }
        return orders.stream()
                .map(OrderDetails::getOrderId)
                .toList();
    }
}
