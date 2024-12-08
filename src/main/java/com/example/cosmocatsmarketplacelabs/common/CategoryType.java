package com.example.cosmocatsmarketplacelabs.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CategoryType {

    COSMOFOOD("CosmoFood"),
    CLOTHES("Clothes"),
    DEVICES("Devices"),
    TOYS("Toys"),
    ACCESSORIES("Accessories"),
    OTHER("Other");

    private final String displayName;
}