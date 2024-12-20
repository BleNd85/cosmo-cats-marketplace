package com.example.cosmocatsmarketplacelabs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class CosmicCatDTO {
    @Schema(description = "Name of the cat")
    @NotBlank(message = "Cat name is mandatory")
    @Size(max = 20)
    String catName;

    @Schema(description = "Real name of the cat")
    @Size(max = 64)
    @NotBlank(message = "Cat's real name is mandatory")
    String realName;

    @Schema(description = "Mail of the cat")
    @Email(message = "Catmail must be valid")
    String catMail;
}
