package com.salesianos.triana.appbike.validation.annotation;

import com.salesianos.triana.appbike.validation.validator.PasswordsMatchValidator;
import com.salesianos.triana.appbike.validation.validator.PinMatchValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PinMatchValidator.class)
@Documented
public @interface PinMatch {

    String message() default "Pins don't match";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};
    String pinField();
    String pinCormirmationField();
}
