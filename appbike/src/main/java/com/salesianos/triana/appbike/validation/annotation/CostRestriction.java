package com.salesianos.triana.appbike.validation.annotation;

import com.salesianos.triana.appbike.validation.validator.CostRestrictionValidator;
import com.salesianos.triana.appbike.validation.validator.RechargeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CostRestrictionValidator.class)
@Documented
public @interface CostRestriction {

    String message() default "Invalid value. The cost must be between 0.00€ and 100€";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}