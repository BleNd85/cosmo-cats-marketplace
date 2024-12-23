package com.example.cosmocatsmarketplacelabs.service.exception;

import java.util.UUID;

public class CosmicCatNofFoundException extends RuntimeException {
    private static final String COSMIC_CAT_NOT_FOUND_MESSAGE = "Cosmic cat with id %s not found";

    public CosmicCatNofFoundException(UUID id) {
        super(String.format(COSMIC_CAT_NOT_FOUND_MESSAGE, id));
    }
}
