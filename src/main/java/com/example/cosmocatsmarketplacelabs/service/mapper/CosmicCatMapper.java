package com.example.cosmocatsmarketplacelabs.service.mapper;

import com.example.cosmocatsmarketplacelabs.domain.CosmicCat;
import com.example.cosmocatsmarketplacelabs.dto.CosmicCatDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CosmicCatMapper {
    @Mapping(source = "catName", target = "catName")
    @Mapping(source = "realName", target = "realName")
    @Mapping(source = "catMail", target = "catMail")
    CosmicCatDTO toCosmicCatDTO(CosmicCat cosmicCat);

    List<CosmicCatDTO> toCosmicCatDTO(List<CosmicCat> cosmicCats);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "catName", target = "catName")
    @Mapping(source = "realName", target = "realName")
    @Mapping(source = "catMail", target = "catMail")
    CosmicCat toCosmicCat(CosmicCatDTO cosmicCatDTO);
}
