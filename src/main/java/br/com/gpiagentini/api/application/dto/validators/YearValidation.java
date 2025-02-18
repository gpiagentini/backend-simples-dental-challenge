package br.com.gpiagentini.api.application.dto.validators;


import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = YearValidator.class)
public @interface YearValidation {
    String message() default "Date must be before 15 year.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}


class YearValidator implements ConstraintValidator<YearValidation, LocalDate> {

    @Override
    public boolean isValid(LocalDate bornDate, ConstraintValidatorContext context) {
        LocalDate currentDate = LocalDate.now();
        LocalDate date15YearsAgo = currentDate.minusYears(15);

        return bornDate.isBefore(date15YearsAgo);
    }
}
