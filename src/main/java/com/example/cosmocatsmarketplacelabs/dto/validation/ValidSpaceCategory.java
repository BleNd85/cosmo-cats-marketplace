package com.example.cosmocatsmarketplacelabs.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SpaceCategoryValidator.class)

public @interface ValidSpaceCategory {
    String message() default "Invalid Space Category it must be: CosmoFood, Clothes, Devices, Toys, Accessories, or Other if you didn't find right category";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}