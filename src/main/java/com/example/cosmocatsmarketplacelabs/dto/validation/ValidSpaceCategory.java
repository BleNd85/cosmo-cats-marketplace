package com.example.cosmocatsmarketplacelabs.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SpaceCategoryValidator.class)
@Documented
public @interface ValidSpaceCategory {
    String message() default "Invalid Space Category it must be: CosmoFood, Clothes, Devices, Toys, Accessories, or Other if you didn't find right categoryType";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}