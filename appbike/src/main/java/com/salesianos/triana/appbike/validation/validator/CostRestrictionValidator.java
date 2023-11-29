package com.salesianos.triana.appbike.validation.validator;

import com.salesianos.triana.appbike.validation.annotation.CostRestriction;
import com.salesianos.triana.appbike.validation.annotation.Recharge;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CostRestrictionValidator implements ConstraintValidator<CostRestriction, Double> {

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value != null && value >= 0 && value <= 100;
    }
}