package com.example.cosmocatsmarketplacelabs.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.cosmocatsmarketplacelabs.config.MappersTestConfiguration;
import com.example.cosmocatsmarketplacelabs.domain.CosmicCatDetails;
import com.example.cosmocatsmarketplacelabs.repository.CosmicCatRepository;
import com.example.cosmocatsmarketplacelabs.repository.entity.CosmicCatEntity;
import com.example.cosmocatsmarketplacelabs.repository.mapper.CosmicCatRepositoryMapper;
import com.example.cosmocatsmarketplacelabs.service.exception.CosmicCatNofFoundException;
import com.example.cosmocatsmarketplacelabs.service.implementation.CosmicCatServiceImplementation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

@SpringBootTest(classes = {CosmicCatServiceImplementation.class})
@ExtendWith(MockitoExtension.class)
@Import({MappersTestConfiguration.class})
@DisplayName("CosmicCat Service Test")
class CosmicCatServiceImplementationTest {

    @MockBean
    private CosmicCatRepository cosmoCatRepository;

    @MockBean
    private CosmicCatRepositoryMapper cosmoCatRepositoryMapper;

    @Autowired
    private CosmicCatServiceImplementation cosmoCatService;

    @Test
    void testGetAllCosmicCats() {
        List<CosmicCatEntity> entities = List.of(new CosmicCatEntity());
        List<CosmicCatDetails> details = List.of(new CosmicCatDetails());

        when(cosmoCatRepository.findAll()).thenReturn(entities);
        when(cosmoCatRepositoryMapper.toCosmicCatDetails(entities)).thenReturn(details);

        List<CosmicCatDetails> result = cosmoCatService.getAllCosmicCats();
        assertEquals(details, result);
    }

    @Test
    void testGetCosmicCatByCosmicCatId() {
        UUID cosmicCatId = UUID.randomUUID();
        CosmicCatEntity entity = new CosmicCatEntity();
        CosmicCatDetails details = new CosmicCatDetails();

        when(cosmoCatRepository.findByNaturalId(cosmicCatId)).thenReturn(Optional.of(entity));
        when(cosmoCatRepositoryMapper.toCosmicCatDetails(entity)).thenReturn(details);

        CosmicCatDetails result = cosmoCatService.getCosmicCatByCosmicCatId(cosmicCatId);
        assertEquals(details, result);
    }

    @Test
    void testGetCosmoCatByCosmicCatIdNotFound() {
        UUID cosmicCatId = UUID.randomUUID();

        when(cosmoCatRepository.findByNaturalId(cosmicCatId)).thenReturn(Optional.empty());

        assertThrows(CosmicCatNofFoundException.class, () -> cosmoCatService.getCosmicCatByCosmicCatId(cosmicCatId));
    }

    @Test
    void testSaveCosmoCat() {
        CosmicCatDetails details = new CosmicCatDetails();
        CosmicCatEntity entity = new CosmicCatEntity();

        when(cosmoCatRepositoryMapper.toCosmicCatEntity(details)).thenReturn(entity);
        when(cosmoCatRepository.save(entity)).thenReturn(entity);
        when(cosmoCatRepositoryMapper.toCosmicCatDetails(entity)).thenReturn(details);

        CosmicCatDetails result = cosmoCatService.saveCosmicCat(details);
        assertEquals(details, result);
    }

    @Test
    void testSaveCosmicCatWithCosmicCatId() {
        UUID cosmicCatId = UUID.randomUUID();
        CosmicCatDetails details = new CosmicCatDetails();
        CosmicCatEntity entity = new CosmicCatEntity();

        when(cosmoCatRepository.findByNaturalId(cosmicCatId)).thenReturn(Optional.of(entity));
        when(cosmoCatRepository.save(entity)).thenReturn(entity);
        when(cosmoCatRepositoryMapper.toCosmicCatDetails(entity)).thenReturn(details);

        CosmicCatDetails result = cosmoCatService.saveCosmicCat(cosmicCatId, details);
        assertEquals(details, result);
    }

    @Test
    void testSaveCosmicCatWithCosmicCatIdNotFound() {
        UUID cosmicCatId = UUID.randomUUID();
        CosmicCatDetails details = new CosmicCatDetails();

        when(cosmoCatRepository.findByNaturalId(cosmicCatId)).thenReturn(Optional.empty());

        assertThrows(CosmicCatNofFoundException.class, () -> cosmoCatService.saveCosmicCat(cosmicCatId, details));
    }

    @Test
    void testDeleteCosmoCat() {
        UUID cosmicCatId = UUID.randomUUID();
        CosmicCatEntity entity = new CosmicCatEntity();

        when(cosmoCatRepository.findByNaturalId(cosmicCatId)).thenReturn(Optional.of(entity));

        cosmoCatService.deleteCosmicCat(cosmicCatId);

        verify(cosmoCatRepository).deleteByNaturalId(cosmicCatId);
    }

}
