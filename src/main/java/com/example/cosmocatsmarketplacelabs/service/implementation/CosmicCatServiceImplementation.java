package com.example.cosmocatsmarketplacelabs.service.implementation;

import com.example.cosmocatsmarketplacelabs.domain.CosmicCatDetails;
import com.example.cosmocatsmarketplacelabs.service.CosmicCatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CosmicCatServiceImplementation implements CosmicCatService {
    private final List<CosmicCatDetails> cosmicCatDetails = createCosmicCats();

    @Override
    public List<CosmicCatDetails> getAllCosmicCats() {
        return cosmicCatDetails;
    }

    private List<CosmicCatDetails> createCosmicCats() {
        return List.of(
                CosmicCatDetails.builder()
                        .id(1L)
                        .catName("Marsik")
                        .realName("Mars Barsovych")
                        .catMail("marsik12@gmail.com")
                        .build(),
                CosmicCatDetails.builder()
                        .id(2L)
                        .catName("Termopricon")
                        .realName("Pricon Termo")
                        .catMail("termopricon7@gmail.com")
                        .build(),
                CosmicCatDetails.builder()
                        .id(3L)
                        .catName("Cyberpatron")
                        .realName("Paton Cyberious")
                        .catMail("cyberiouspaton@gmail.com")
                        .build()
        );
    }

}
