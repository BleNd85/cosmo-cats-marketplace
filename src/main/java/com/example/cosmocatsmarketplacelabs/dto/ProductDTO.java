package com.example.cosmocatsmarketplacelabs.dto;

import com.example.cosmocatsmarketplacelabs.dto.validation.ExtendedValidation;
import com.example.cosmocatsmarketplacelabs.dto.validation.ValidSpaceCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@GroupSequence({ProductDTO.class, ExtendedValidation.class})
public class ProductDTO {
    @Schema(description = "Name of the product")
    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name can't be more than 100 symbols")
    private String name;

    @Schema(description = "Description of the product")
    @NotBlank(message = "Description is mandatory")
    @Size(max = 255, message = "Description can't be more than 255 symbols")
    private String description;

    @Schema(description = "Price of the product")
    @NotNull(message = "Price is mandatory")
    @Min(value = 1, message = "Price can't be negative or zero")
    private Double price;

    @Schema(description = "Category of the product")
    @NotEmpty(message = "Category can't be empty")
    @ValidSpaceCategory(groups = ExtendedValidation.class)
    private String category;

}
