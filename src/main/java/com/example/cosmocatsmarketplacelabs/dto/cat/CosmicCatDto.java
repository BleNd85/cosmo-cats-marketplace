package com.example.cosmocatsmarketplacelabs.dto.cat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CosmicCatDto {

    private UUID catReference;

    @Schema(description = "Name of the cat")
    @NotBlank(message = "Cat name is mandatory")
    @Size(max = 20)
    private String catName;

    @Schema(description = "Real name of the cat")
    @Size(max = 64)
    @NotBlank(message = "Cat's real name is mandatory")
    private String realName;

    @Schema(description = "Mail of the cat")
    @Email(message = "Catmail must be valid")
    private String catMail;

    private List<UUID> orders;
}
