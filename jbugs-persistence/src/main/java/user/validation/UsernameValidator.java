package user.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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

      //validates if a username contains only letters
      char[] chars = username.toCharArray();

      for (char c : chars) {
         if(!Character.isLetter(c)) {
            return false;
         }
      }

      return true;
   }
}
