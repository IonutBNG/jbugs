package user.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public class UsernameValidator implements ConstraintValidator<Username, String> {
   public void initialize(Username constraint) {
   }

   public boolean isValid(String username, ConstraintValidatorContext context) {

      Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
      Matcher m = p.matcher(username);
      boolean check = m.find();

      return !check;
   }
}
