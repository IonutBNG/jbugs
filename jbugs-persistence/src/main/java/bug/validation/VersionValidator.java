package bug.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class VersionValidator implements ConstraintValidator<Version, String> {

    String regex = "(?!\\.)(\\d+(\\.\\d+)+)(?:[.][a-zA-Z]+)?(?![\\d.])$";

    @Override
    public void initialize(Version version) {
    }

    @Override
    public boolean isValid(String version, ConstraintValidatorContext constraintValidatorContext) {

      if(version == null || version.equals("")){
          return false;
      }

        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(version).matches();
    }
}
