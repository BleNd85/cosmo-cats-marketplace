package com.example.cosmocatsmarketplacelabs.web;

import com.example.cosmocatsmarketplacelabs.dto.cat.CosmicCatDto;
import com.example.cosmocatsmarketplacelabs.featuretoggle.FeatureToggles;
import com.example.cosmocatsmarketplacelabs.featuretoggle.annotation.FeatureToggle;
import com.example.cosmocatsmarketplacelabs.service.CosmicCatService;
import com.example.cosmocatsmarketplacelabs.service.mapper.CosmicCatServiceMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/cosmic-cats")
public class CosmicCatController {
    private final CosmicCatService cosmicCatService;
    private final CosmicCatServiceMapper cosmicCatServiceMapper;

    public CosmicCatController(CosmicCatService cosmicCatService, CosmicCatServiceMapper cosmicCatServiceMapper) {
        this.cosmicCatService = cosmicCatService;
        this.cosmicCatServiceMapper = cosmicCatServiceMapper;
    }

/*    @PreAuthorize("hasRole('COSMO_ADMIN')")*/
    @FeatureToggle(FeatureToggles.COSMO_CATS)
    @GetMapping
    public ResponseEntity<List<CosmicCatDto>> getCosmicCats() {
        return ResponseEntity.ok(cosmicCatServiceMapper.toCosmicCatDTO(cosmicCatService.getAllCosmicCats()));
    }

    @FeatureToggle(FeatureToggles.COSMO_CATS)
    @GetMapping("/{cosmicCatId}")
    public ResponseEntity<CosmicCatDto> getCosmicCat(@PathVariable UUID cosmicCatId) {
        return ResponseEntity.ok(cosmicCatServiceMapper.toCosmicCatDTO(cosmicCatService
                .getCosmicCatByCosmicCatId(cosmicCatId)));
    }

    @PostMapping
    public ResponseEntity<CosmicCatDto> createCosmicCat(@RequestBody @Valid CosmicCatDto cosmicCatDto) {
        return ResponseEntity.ok(cosmicCatServiceMapper.toCosmicCatDTO(cosmicCatService
                .saveCosmicCat(cosmicCatServiceMapper.toCosmicCatDetails(cosmicCatDto))));
    }

    @PutMapping("/{cosmicCatId}")
    public ResponseEntity<CosmicCatDto> updateCosmicCat(@PathVariable UUID cosmicCatId, @RequestBody @Valid CosmicCatDto cosmicCatDto) {
        return ResponseEntity.ok(cosmicCatServiceMapper.toCosmicCatDTO(cosmicCatService
                .saveCosmicCat(cosmicCatId, cosmicCatServiceMapper.toCosmicCatDetails(cosmicCatDto))));
    }

    @DeleteMapping("/{cosmicCatId}")
    public ResponseEntity<Void> deleteCosmicCat(@PathVariable UUID cosmicCatId) {
        cosmicCatService.deleteCosmicCat(cosmicCatId);
        return ResponseEntity.noContent().build();
    }
}
