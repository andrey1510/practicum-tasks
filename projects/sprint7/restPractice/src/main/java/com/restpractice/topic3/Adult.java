package com.restpractice.topic3;

import javax.validation.*;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AdultValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Adult {
    String message() default "Возраст должен быть 18 лет и старше";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

class AdultValidator implements ConstraintValidator<Adult, Integer> {
    @Override
    public void initialize(Adult constraintAnnotation) {
        // Инициализация при необходимости
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value >= 18;
    }
}