package com.example.cosmocatsmarketplacelabs.service.mapper;

import com.example.cosmocatsmarketplacelabs.domain.CosmicCatDetails;
import com.example.cosmocatsmarketplacelabs.dto.cat.CosmicCatDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

public class CosmicCatDetailsMapperTest {
    private CosmicCatServiceMapper cosmicCatServiceMapper;

    @BeforeEach
    public void setUp() {
        cosmicCatServiceMapper = Mappers.getMapper(CosmicCatServiceMapper.class);
    }

    @Test
    void shouldMapCosmicCatToCosmicCatDto() {
        CosmicCatDetails cosmicCatDetails = CosmicCatDetails.builder()
                .id(1L)
                .catName("TestCat1")
                .realName("One TestCat")
                .catMail("test@catmail.com")
                .build();

        CosmicCatDto cosmicCatDTO = cosmicCatServiceMapper.toCosmicCatDTO(cosmicCatDetails);

        assertNotNull(cosmicCatDTO);
        assertEquals(cosmicCatDTO.getCatName(), cosmicCatDetails.getCatName());
        assertEquals(cosmicCatDTO.getRealName(), cosmicCatDetails.getRealName());
        assertEquals(cosmicCatDTO.getCatMail(), cosmicCatDetails.getCatMail());
    }

    @Test
    void shouldMapCosmicCatDTOToCosmicCat() {
        CosmicCatDto cosmicCatDTO = CosmicCatDto.builder()
                .catName("TestCat1")
                .realName("One TestCat")
                .catMail("test@catmail.com")
                .build();

        CosmicCatDetails cosmicCatDetails = cosmicCatServiceMapper.toCosmicCatDetails(cosmicCatDTO);

        assertNotNull(cosmicCatDetails);
        assertEquals(cosmicCatDetails.getCatName(), cosmicCatDTO.getCatName());
        assertEquals(cosmicCatDetails.getRealName(), cosmicCatDTO.getRealName());
        assertEquals(cosmicCatDetails.getCatMail(), cosmicCatDTO.getCatMail());
    }
}
