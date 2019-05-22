package user.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MobileNumberValidator.class)
public @interface MobileNumber {
    String message() default ValidationMessageCatalog.INVALID_MOBILE_NUMBER;

    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
