package com.example.cosmocatsmarketplacelabs.web;

import com.example.cosmocatsmarketplacelabs.AbstractIt;
import com.example.cosmocatsmarketplacelabs.domain.CosmicCatDetails;
import com.example.cosmocatsmarketplacelabs.dto.cat.CosmicCatDto;
import com.example.cosmocatsmarketplacelabs.featuretoggle.FeatureToggleExtension;
import com.example.cosmocatsmarketplacelabs.featuretoggle.FeatureToggles;
import com.example.cosmocatsmarketplacelabs.featuretoggle.annotation.DisabledFeatureToggle;
import com.example.cosmocatsmarketplacelabs.featuretoggle.annotation.EnabledFeatureToggle;
import com.example.cosmocatsmarketplacelabs.repository.CosmicCatRepository;
import com.example.cosmocatsmarketplacelabs.repository.entity.CosmicCatEntity;
import com.example.cosmocatsmarketplacelabs.repository.mapper.CosmicCatRepositoryMapper;
import com.example.cosmocatsmarketplacelabs.service.CosmicCatService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("CosmicCat Controller IT")
@ExtendWith(FeatureToggleExtension.class)
public class CosmicCatControllerIT extends AbstractIt {
    @Autowired
    MockMvc mockMvc;

    @SpyBean
    private CosmicCatService cosmicCatService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CosmicCatRepositoryMapper cosmicCatRepositoryMapper;

    @Autowired
    private CosmicCatRepository cosmicCatRepository;

    @BeforeEach
    void setUp() {
        reset(cosmicCatService);
        cosmicCatRepository.deleteAll();
    }

    @Test
    @SneakyThrows
    @EnabledFeatureToggle(FeatureToggles.COSMO_CATS)
    void shouldReturnAllCosmicCats() {
        mockMvc.perform(get("/api/v1/cosmic-cats")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @DisabledFeatureToggle(FeatureToggles.COSMO_CATS)
    void shouldReturnAllCosmicCatsDisabled() {
        mockMvc.perform(get("/api/v1/cosmic-cats")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    @EnabledFeatureToggle(FeatureToggles.COSMO_CATS)
    void shouldReturnCosmicCatByCosmicCatIdEnabled() {
        CosmicCatEntity cosmicCatEntity = createCosmicCat();

        mockMvc.perform(get("/api/v1/cosmic-cats/{cosmicCatId}", cosmicCatEntity.getCosmicCatId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @SneakyThrows
    @DisabledFeatureToggle(FeatureToggles.COSMO_CATS)
    void shouldReturnCosmicCatByCosmicCatIdDisabled() {
        CosmicCatEntity cosmicCatEntity = createCosmicCat();

        mockMvc.perform(get("/api/v1/cosmic-cats/{cosmicCatId}", cosmicCatEntity.getCosmicCatId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    @EnabledFeatureToggle(FeatureToggles.COSMO_CATS)
    void shouldReturnCosmicCatByCosmicCatIdNotFound() {
        mockMvc.perform(get("/api/v1/cosmic-cats/{cosmicCatId}", UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    @SneakyThrows
    void shouldCreateCosmicCat() {
        CosmicCatDto cosmicCatDto = new CosmicCatDto().toBuilder()
                .catName("Create")
                .realName("Create Test")
                .catMail("createtest@catmail.com")
                .build();

        mockMvc.perform(post("/api/v1/cosmic-cats")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cosmicCatDto)))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void shouldCreateCosmicCatValidationError() {
        CosmicCatDto cosmicCatDto = new CosmicCatDto().toBuilder()
                .catName("Create")
                .realName("Create Test")
                .catMail("createtest.com")
                .build();

        mockMvc.perform(post("/api/v1/cosmic-cats")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cosmicCatDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void shouldUpdateCosmicCat() {
        CosmicCatEntity cosmicCatEntity = createCosmicCat();

        CosmicCatDto cosmicCatDto = new CosmicCatDto().toBuilder()
                .catName("Update")
                .realName("Update Test")
                .catMail("updatetetest@catmail.com")
                .build();

        mockMvc.perform(put("/api/v1/cosmic-cats/{cosmicCatId}", cosmicCatEntity.getCosmicCatId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cosmicCatDto)))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void shouldUpdateCosmicCatValidationError() {
        CosmicCatEntity cosmicCatEntity = createCosmicCat();

        CosmicCatDto cosmicCatDto = new CosmicCatDto().toBuilder()
                .catName("Update")
                .realName("Update Test")
                .catMail("updatetetest.com")
                .build();

        mockMvc.perform(put("/api/v1/cosmic-cats/{cosmicCatId}", cosmicCatEntity.getCosmicCatId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cosmicCatDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void shouldUpdateCosmicCatNotFound() {
        CosmicCatDto cosmicCatDto = new CosmicCatDto().toBuilder()
                .catName("Update")
                .realName("Update Test")
                .catMail("updatetetest@catmail.com")
                .build();

        mockMvc.perform(put("/api/v1/cosmic-cats/{cosmicCatId}", UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cosmicCatDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    void shouldDeleteCosmicCat() {
        CosmicCatEntity cosmicCatEntity = createCosmicCat();

        mockMvc.perform(delete("/api/v1/cosmic-cats/{cosmicCatId}", cosmicCatEntity.getCosmicCatId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        mockMvc.perform(get("/api/v1/cosmic-cats/{cosmicCatId}", cosmicCatEntity.getCosmicCatId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }


    private CosmicCatEntity createCosmicCat() {
        return cosmicCatRepositoryMapper.toCosmicCatEntity(cosmicCatService.saveCosmicCat(CosmicCatDetails.builder()
                .cosmicCatId(UUID.randomUUID())
                .catName("TestCat")
                .realName("Test Cat")
                .catMail("TestMail@catmail.com")
                .build()));
    }
}
