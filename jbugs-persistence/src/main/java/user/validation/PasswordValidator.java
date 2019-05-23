package user.validation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {
   private static final Integer PASSWORD_SIZE_MAX = 30;
   private static final Integer PASSWORD_SIZE_MIN = 5;

   public void initialize(Password constraint) {
   }

   public boolean isValid(String password, ConstraintValidatorContext context) {
      if(password.length() > PASSWORD_SIZE_MAX || password.length() < PASSWORD_SIZE_MIN) {
         return false;
      }

      if (password == null || password.equals("")) {
         return false;
      }
      return true;
   }
}
