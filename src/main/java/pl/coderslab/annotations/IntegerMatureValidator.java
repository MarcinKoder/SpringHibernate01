package pl.coderslab.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class IntegerMatureValidator implements ConstraintValidator<Mature, Integer> {
    public void initialize(Mature constraint) {
    }

    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) return true;
        int currentYear = LocalDate.now().getYear();
        return currentYear - value >= 18;
    }
}
