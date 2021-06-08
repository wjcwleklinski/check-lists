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

    String message() default "Must be one of enum";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<? extends Enum<?>> enumClass();

    class OneOfEnumValidator implements ConstraintValidator<OneOfEnum, String> {

        List<String> enumValues;

        @Override
        public void initialize(OneOfEnum constraintAnnotation) {
            enumValues = new ArrayList<>();
            Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClass();
            Enum<?>[] enumConstants = enumClass.getEnumConstants();
            for (Enum<?> enumConstant: enumConstants) {
                enumValues.add(enumConstant.toString().toUpperCase());
            }
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            return enumValues.contains(value.toUpperCase());
        }
    }
}
