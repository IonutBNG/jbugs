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
        if(s.equals(bug.entity.Severity.getSeverityByString("Critical"))||
                s.equals(bug.entity.Severity.getSeverityByString("High")) ||
                s.equals(bug.entity.Severity.getSeverityByString("Medium")) ||
                s.equals(bug.entity.Severity.getSeverityByString("Low"))){
                    return false;
        }


        return true;
    }
}
