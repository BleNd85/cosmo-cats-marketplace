package com.example.cosmocatsmarketplacelabs.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class CosmicCatDTO {
    @NotBlank(message = "Cat name is mandatory")
    @Size(max = 20)
    String catName;

    @Size(max = 64)
    @NotBlank(message = "Cat's real name is mandatory")
    String realName;

    @Email(message = "Catmail must be valid")
    String catMail;
}
