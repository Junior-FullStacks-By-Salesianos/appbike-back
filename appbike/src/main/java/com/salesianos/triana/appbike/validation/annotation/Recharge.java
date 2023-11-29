package com.salesianos.triana.appbike.validation.annotation;

import com.salesianos.triana.appbike.validation.validator.RechargeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RechargeValidator.class)
@Documented
public @interface Recharge {

    String message() default "Invalid value. The recharge must be between 2.00€ and 100€";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
