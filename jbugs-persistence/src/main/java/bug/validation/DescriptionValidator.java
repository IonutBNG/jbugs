package bug.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DescriptionValidator  implements ConstraintValidator<Description, String> {

    private static final Integer DESCRIPTION_MIN_SIZE = 250;

    @Override
    public void initialize(Description description) {

    }

    @Override
    public boolean isValid(String description, ConstraintValidatorContext constraintValidatorContext) {

        if(description.length() < DESCRIPTION_MIN_SIZE){
            return false;
        }

     return true;
    }

}
