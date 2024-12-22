package com.example.cosmocatsmarketplacelabs.web;

import com.example.cosmocatsmarketplacelabs.dto.cat.CosmicCatDto;
import com.example.cosmocatsmarketplacelabs.featuretoggle.FeatureToggles;
import com.example.cosmocatsmarketplacelabs.featuretoggle.annotation.FeatureToggle;
import com.example.cosmocatsmarketplacelabs.service.CosmicCatService;
import com.example.cosmocatsmarketplacelabs.service.mapper.CosmicCatServiceMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/cosmic-cats")
public class CosmicCatController {
    private final CosmicCatService cosmicCatService;
    private final CosmicCatServiceMapper cosmicCatServiceMapper;

    public CosmicCatController(CosmicCatService cosmicCatService, CosmicCatServiceMapper cosmicCatServiceMapper) {
        this.cosmicCatService = cosmicCatService;
        this.cosmicCatServiceMapper = cosmicCatServiceMapper;
    }

    @FeatureToggle(FeatureToggles.COSMO_CATS)
    @GetMapping
    public ResponseEntity<List<CosmicCatDto>> getCosmicCats() {
        return ResponseEntity.ok(cosmicCatServiceMapper.toCosmicCatDTO(cosmicCatService.getAllCosmicCats()));
    }
}
