package com.example.cosmocatsmarketplacelabs.service;


import com.example.cosmocatsmarketplacelabs.domain.CosmicCatDetails;

import java.util.List;
import java.util.UUID;

public interface CosmicCatService {
    List<CosmicCatDetails> getAllCosmicCats();

    CosmicCatDetails getCosmicCatByCosmicCatId(UUID cosmicCatId);

    CosmicCatDetails saveCosmicCat(CosmicCatDetails cosmoCatDetails);

    CosmicCatDetails saveCosmicCat(UUID catReference, CosmicCatDetails cosmoCatDetails);

    void deleteCosmicCat(UUID cosmicCatId);

}
