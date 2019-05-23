package user.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public class MobileNumberValidator implements ConstraintValidator<MobileNumber, String> {
   public void initialize(MobileNumber constraint) {
   }

   public boolean isValid(String phonenumber, ConstraintValidatorContext context) {

      if (phonenumber == null) {
         return false;
      }
      //pattern to validate international phonenumbers, according to ITU-T standards
      String regex = "^\\+(?:[0-9] ?){6,14}[0-9]$";
      Pattern pattern = Pattern.compile(regex);

      return pattern.matcher(phonenumber).matches();
   }
}
