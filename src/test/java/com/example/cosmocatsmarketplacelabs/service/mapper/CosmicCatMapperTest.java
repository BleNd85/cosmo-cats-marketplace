package com.example.cosmocatsmarketplacelabs.service.mapper;

import com.example.cosmocatsmarketplacelabs.domain.CosmicCat;
import com.example.cosmocatsmarketplacelabs.dto.CosmicCatDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

public class CosmicCatMapperTest {
    private CosmicCatMapper cosmicCatMapper;

    @BeforeEach
    public void setUp() {
        cosmicCatMapper = Mappers.getMapper(CosmicCatMapper.class);
    }

    @Test
    void shouldMapCosmicCatToCosmicCatDto() {
        CosmicCat cosmicCat = CosmicCat.builder()
                .id(1L)
                .catName("TestCat1")
                .realName("One TestCat")
                .catMail("test@catmail.com")
                .build();

        CosmicCatDTO cosmicCatDTO = cosmicCatMapper.toCosmicCatDTO(cosmicCat);

        assertNotNull(cosmicCatDTO);
        assertEquals(cosmicCatDTO.getCatName(), cosmicCat.getCatName());
        assertEquals(cosmicCatDTO.getRealName(), cosmicCat.getRealName());
        assertEquals(cosmicCatDTO.getCatMail(), cosmicCat.getCatMail());
    }

    @Test
    void shouldMapCosmicCatDTOToCosmicCat() {
        CosmicCatDTO cosmicCatDTO = CosmicCatDTO.builder()
                .catName("TestCat1")
                .realName("One TestCat")
                .catMail("test@catmail.com")
                .build();

        CosmicCat cosmicCat = cosmicCatMapper.toCosmicCat(cosmicCatDTO);

        assertNotNull(cosmicCat);
        assertEquals(cosmicCat.getCatName(), cosmicCatDTO.getCatName());
        assertEquals(cosmicCat.getRealName(), cosmicCatDTO.getRealName());
        assertEquals(cosmicCat.getCatMail(), cosmicCatDTO.getCatMail());
    }
}
