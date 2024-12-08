package com.example.cosmocatsmarketplacelabs.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
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
    private List<String> categories;

}
