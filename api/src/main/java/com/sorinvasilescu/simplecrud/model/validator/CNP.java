package com.sorinvasilescu.simplecrud.model.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CNPValidator.class)
public @interface CNP {
    String message() default "CNP is invalid";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
