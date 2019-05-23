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
   private static final Integer NAME_MAX_SIZE = 60;
   private static final Integer NAME_MIN_SIZE = 2;


   public void initialize(Name constraint) {
   }

   public boolean isValid(String name, ConstraintValidatorContext context) {
      //validate that the name is not empty
      if(name.length() > NAME_MAX_SIZE || name.length() < NAME_MIN_SIZE) {
         return false;
      }

      //validate that the first letter is uppercase;
      if(Character.isLowerCase(name.charAt(0))) {
         return false;
      }

      //validate that the characters are all letters, space, ' or -
      return name.matches( "[a-zA-z]+([ '-.][a-zA-Z]+)*" );
   }
}
