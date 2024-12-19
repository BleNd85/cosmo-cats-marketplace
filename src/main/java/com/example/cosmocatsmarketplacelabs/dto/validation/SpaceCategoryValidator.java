package com.example.cosmocatsmarketplacelabs.dto.validation;

import com.example.cosmocatsmarketplacelabs.common.CategoryType;
import com.example.cosmocatsmarketplacelabs.service.mapper.ProductMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Component
public class SpaceCategoryValidator implements ConstraintValidator<ValidSpaceCategory, String> {
    private final ProductMapper productMapper;

    @Override
    public boolean isValid(String category, ConstraintValidatorContext constraintValidatorContext) {
        return Stream.of(CategoryType.values())
                .anyMatch(type -> productMapper.toCategoryString(type).equalsIgnoreCase(category));
    }
}
