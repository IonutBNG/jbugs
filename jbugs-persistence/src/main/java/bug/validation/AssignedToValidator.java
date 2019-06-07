package bug.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AssignedToValidator implements ConstraintValidator<AssignedTo, String> {
    @Override
    public void initialize(AssignedTo assignedTo) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (username == null || username.equals("")) {
            return false;
        }
        return true;
    }
}
