package com.meraj;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.Assert.*;

public class ValidationObjectTest {

    private static Validator validator = Validation
            .byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory()
            .getValidator();

    @Test
    public void testValidation() {
        ValidationObject validationObject = buildValidationObject();
        final Set<ConstraintViolation<ValidationObject>> violations = validator.validate(validationObject);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testValidation_validateSize() {
        ValidationObject validationObject = buildValidationObject();
        validationObject.setSize("12");
        Set<ConstraintViolation<ValidationObject>> violations = validator.validate(validationObject);
        assertFalse(violations.isEmpty());
        assertEquals("size must be between 3 and 10", violations.stream().findFirst().get().getMessage());
        validationObject.setSize("this is more that 10");
        violations = validator.validate(validationObject);
        assertEquals("size must be between 3 and 10", violations.stream().findFirst().get().getMessage());
    }

    @Test
    public void testValidation_notNull() {
        ValidationObject validationObject = buildValidationObject();
        validationObject.setNotNull(null);
        Set<ConstraintViolation<ValidationObject>> violations = validator.validate(validationObject);
        assertFalse(violations.isEmpty());
        assertEquals("must not be null", violations.stream().findFirst().get().getMessage());
    }

    @Test
    public void testValidation_isFalse() {
        ValidationObject validationObject = buildValidationObject();
        validationObject.setIsFalse(true);
        Set<ConstraintViolation<ValidationObject>> violations = validator.validate(validationObject);
        assertFalse(violations.isEmpty());
        assertEquals("must be false", violations.stream().findFirst().get().getMessage());
    }

    private ValidationObject buildValidationObject() {
        return new ValidationObject("12345", "not Null", false);
    }

}