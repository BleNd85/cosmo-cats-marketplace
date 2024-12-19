package com.example.cosmocatsmarketplacelabs.dto;

import com.example.cosmocatsmarketplacelabs.dto.validation.ExtendedValidation;
import com.example.cosmocatsmarketplacelabs.dto.validation.ValidSpaceCategory;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//todo add doc
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@GroupSequence({ProductDTO.class, ExtendedValidation.class})
public class ProductDTO {

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name can't be more than 100 symbols")
    private String name;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 255, message = "Description can't be more than 255 symbols")
    private String description;

    @NotNull(message = "Price is mandatory")
    @Min(value = 1, message = "Price can't be negative or zero")
    private Double price;

    @NotEmpty(message = "Category can't be empty")
    @ValidSpaceCategory(groups = ExtendedValidation.class)
    private String category;

}
