package com.example.cosmocatsmarketplacelabs.service.implementation;

import com.example.cosmocatsmarketplacelabs.domain.CosmicCat;
import com.example.cosmocatsmarketplacelabs.service.CosmicCatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CosmicCatServiceImplementation implements CosmicCatService {
    private final List<CosmicCat> cosmicCats = createCosmicCats();

    @Override
    public List<CosmicCat> getAllCosmicCats() {
        return cosmicCats;
    }

    private List<CosmicCat> createCosmicCats() {
        return List.of(
                CosmicCat.builder()
                        .id(1L)
                        .catName("Marsik")
                        .realName("Mars Barsovych")
                        .catMail("marsik12@gmail.com")
                        .build(),
                CosmicCat.builder()
                        .id(2L)
                        .catName("Termopricon")
                        .realName("Pricon Termo")
                        .catMail("termopricon7@gmail.com")
                        .build(),
                CosmicCat.builder()
                        .id(3L)
                        .catName("Cyberpatron")
                        .realName("Paton Cyberious")
                        .catMail("cyberiouspaton@gmail.com")
                        .build()
        );
    }

}
