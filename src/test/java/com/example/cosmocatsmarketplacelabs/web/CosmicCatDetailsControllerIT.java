package com.example.cosmocatsmarketplacelabs.web;

import com.example.cosmocatsmarketplacelabs.featuretoggle.FeatureToggleExtension;
import com.example.cosmocatsmarketplacelabs.featuretoggle.FeatureToggles;
import com.example.cosmocatsmarketplacelabs.featuretoggle.annotation.DisabledFeatureToggle;
import com.example.cosmocatsmarketplacelabs.featuretoggle.annotation.EnabledFeatureToggle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName("CosmicCat Controller IT")
@ExtendWith(FeatureToggleExtension.class)
public class CosmicCatDetailsControllerIT {
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisabledFeatureToggle(FeatureToggles.COSMO_CATS)
    void shouldReturnAllCosmicCatsDisabled() throws Exception {
        mockMvc.perform(get("/api/v1/cosmic-cats")).andExpect(status().isNotFound());
    }

    @Test
    @EnabledFeatureToggle(FeatureToggles.COSMO_CATS)
    void shouldReturnAllCosmicCats() throws Exception {
        mockMvc.perform(get("/api/v1/cosmic-cats")).andExpect(status().isOk());
    }
}
