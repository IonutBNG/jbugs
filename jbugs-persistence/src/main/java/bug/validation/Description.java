package bug.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DescriptionValidator.class)
public @interface Description {

    String message() default ValidationMessageCatalog.INVALID_DESCRIPTION;

    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
