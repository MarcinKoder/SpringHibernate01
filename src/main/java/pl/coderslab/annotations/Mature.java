package pl.coderslab.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IntegerMatureValidator.class)
public @interface Mature {
    String message() default "{pl.coderslab.annotations.Mature.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default  {};
}
