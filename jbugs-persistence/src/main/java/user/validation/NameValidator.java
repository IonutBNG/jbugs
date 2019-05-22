package user.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Custom Validator linked with the Name adnotaiton
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public class NameValidator implements ConstraintValidator<Name, String> {
   public void initialize(Name constraint) {
   }

   public boolean isValid(String name, ConstraintValidatorContext context) {
      //validate that the name is not empty
      if(name.equals(null) || name.equals("")) {
         return false;
      }

      //validate that the first letter is uppercase;
      if(Character.isUpperCase(name.charAt(0))) {
         return false;
      }
      char[] chars = name.toCharArray();

      //validate that the characters are all letters
      for (char c : chars) {
         if(!Character.isLetter(c)) {
            return false;
         }
      }
      return true;
   }
}
