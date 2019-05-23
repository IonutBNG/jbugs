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
public class EmailValidator implements ConstraintValidator<Email, String> {

   private static final Integer EMAIL_MIN_SIZE = 3;
   private static final Integer EMAIL_MAX_SIZE = 320;

   public void initialize(Email constraint) {
   }

   public boolean isValid(String email, ConstraintValidatorContext context) {
      if (email == null) {
         return false;
      }

      if (email.length() > EMAIL_MAX_SIZE || email.length() < EMAIL_MIN_SIZE) {
         return false;
      }

      Pattern pattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@(?:(?:[a-zA-Z0-9-]+\\.)?[a-zA-Z]+\\.)?(msggroup)\\.com$");
      return pattern.matcher(email).matches();

   }
}
