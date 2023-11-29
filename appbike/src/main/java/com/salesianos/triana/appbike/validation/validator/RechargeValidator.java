package com.salesianos.triana.appbike.validation.validator;

import com.salesianos.triana.appbike.validation.annotation.Recharge;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RechargeValidator implements ConstraintValidator<Recharge, Double> {

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value != null && value >= 2 && value <= 100;
    }
}
