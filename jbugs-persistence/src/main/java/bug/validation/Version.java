package bug.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VersionValidator.class)
public @interface Version {

    String message() default ValidationMessageCatalog.INVALID_VERSION;

    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };


}
