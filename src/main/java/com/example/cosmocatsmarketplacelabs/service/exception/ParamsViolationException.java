package com.example.cosmocatsmarketplacelabs.service.exception;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class ParamsViolationException {
    String fieldName;
    String reason;
}
