package com.wjcwleklinski.shoppingserver.validation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Documented
@Constraint(validatedBy = OneOfEnum.OneOfEnumValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OneOfEnum {

    String message() default "Invalid value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<? extends Enum<?>> enumClass();

    class OneOfEnumValidator implements ConstraintValidator<OneOfEnum, String> {

        List<String> enumValues;
        String message;

        @Override
        public void initialize(OneOfEnum constraintAnnotation) {
            enumValues = new ArrayList<>();
            message = constraintAnnotation.message();
            Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClass();
            Enum<?>[] enumConstants = enumClass.getEnumConstants();
            for (Enum<?> enumConstant: enumConstants) {
                enumValues.add(enumConstant.toString().toUpperCase());
            }
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (!enumValues.contains(value.toUpperCase())) {
                context.disableDefaultConstraintViolation();
                message = "Must be one of: " + enumValues.toString();
                context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
                return false;
            }
            return true;
        }
    }
}
