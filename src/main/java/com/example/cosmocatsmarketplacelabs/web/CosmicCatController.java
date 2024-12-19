package com.example.cosmocatsmarketplacelabs.web;

import com.example.cosmocatsmarketplacelabs.dto.CosmicCatDTO;
import com.example.cosmocatsmarketplacelabs.featuretoggle.FeatureToggles;
import com.example.cosmocatsmarketplacelabs.featuretoggle.annotation.FeatureToggle;
import com.example.cosmocatsmarketplacelabs.service.CosmicCatService;
import com.example.cosmocatsmarketplacelabs.service.mapper.CosmicCatMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/cosmic-cats")
public class CosmicCatController {
    private final CosmicCatService cosmicCatService;
    private final CosmicCatMapper cosmicCatMapper;

    public CosmicCatController(CosmicCatService cosmicCatService, CosmicCatMapper cosmicCatMapper) {
        this.cosmicCatService = cosmicCatService;
        this.cosmicCatMapper = cosmicCatMapper;
    }

    @FeatureToggle(FeatureToggles.COSMO_CATS)
    @GetMapping
    public ResponseEntity<List<CosmicCatDTO>> getCosmicCats() {
        return ResponseEntity.ok(cosmicCatMapper.toCosmicCatDTO(cosmicCatService.getAllCosmicCats()));
    }
}
