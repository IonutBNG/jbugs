package bug.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.sql.Date;

public class DateValidation implements ConstraintValidator<DateV, Date> {

     String regex = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";

    @Override
    public void initialize(DateV dateV) {
    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {

        if (date == null || date.equals("")) {
            return false;
        }

//        Pattern pattern = Pattern.compile(regex);
//        return pattern.matcher(date.toString()).matches();

        return true;
    }


}
