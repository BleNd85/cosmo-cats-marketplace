package com.example.cosmocatsmarketplacelabs.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class CosmicCat {
    Long id;
    String catName;
    String realName;
    String catMail;
}
