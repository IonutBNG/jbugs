package bug.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class VersionValidator implements ConstraintValidator<Version, String> {

    String regex0 = "^(\\d+\\.)?(\\d+\\.)?(\\*|\\d+)$";

    //alphanumeric charachters
    String regex = "^([a-zA-Z0-9]+\\.)?([a-zA-Z0-9]+\\.)?(\\*|[a-zA-Z0-9]+)$";

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
