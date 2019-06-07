package bug.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SeverityValidator implements ConstraintValidator<Severity, String> {

    @Override
    public void initialize(Severity severity) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if(s == null || s.equals("")){
            return false;
        }
        return true;
    }
}
