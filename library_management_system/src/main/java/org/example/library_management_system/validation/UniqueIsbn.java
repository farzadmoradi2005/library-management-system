package org.example.library_management_system.validation;

import jakarta.validation.*;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueIsbnValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueIsbn {
    String message() default "این ISBN قبلاً ثبت شده است";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}