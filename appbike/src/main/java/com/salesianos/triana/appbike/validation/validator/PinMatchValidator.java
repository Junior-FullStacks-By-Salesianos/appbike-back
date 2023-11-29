package com.salesianos.triana.appbike.validation.validator;

import com.salesianos.triana.appbike.validation.annotation.PasswordsMatch;
import com.salesianos.triana.appbike.validation.annotation.PinMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.util.StringUtils;

public class PinMatchValidator implements ConstraintValidator<PinMatch, Object> {
    String pinField;
    String pinConfirmationField;

    @Override
    public void initialize(PinMatch constraintAnnotation) {
        pinField = constraintAnnotation.pinField();
        pinConfirmationField  = constraintAnnotation.pinCormirmationField();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String pin = (String) PropertyAccessorFactory
                .forBeanPropertyAccess(o)
                .getPropertyValue(String.valueOf(pinField));

        String pinConfirmation = (String) PropertyAccessorFactory
                .forBeanPropertyAccess(o)
                .getPropertyValue(pinConfirmationField);

        return StringUtils.hasText(pin) && pin.contentEquals(pinConfirmation);
    }
}
