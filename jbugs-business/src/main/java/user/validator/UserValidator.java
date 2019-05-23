package user.validator;

import exeptions.BusinessException;
import exeptions.ExceptionMessageCatalog;
import user.validation.ValidationMessageCatalog;
import utils.BaseDto;

import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */
@Stateless
public class UserValidator {

    //for validation
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();

    public void validateBean(BaseDto baseDto) throws BusinessException {
        Set<ConstraintViolation<BaseDto>> violations = validator.validate(baseDto);
        for (ConstraintViolation<BaseDto> v : violations) {
            String constraintErrorMessage = v.getMessage();
            switch (constraintErrorMessage) {

                case(ValidationMessageCatalog.INVALID_NAME):
                    throw new BusinessException(ExceptionMessageCatalog.USER_INVALID_NAME);
                case(ValidationMessageCatalog.INVALID_EMAIL):
                    throw new BusinessException(ExceptionMessageCatalog.USER_INVALID_EMAIL);
                case(ValidationMessageCatalog.INVALID_MOBILE_NUMBER):
                    throw new BusinessException(ExceptionMessageCatalog.USER_INVALID_PHONENUMBER);
                case(ValidationMessageCatalog.INVALID_USERNAME):
                    throw new BusinessException(ExceptionMessageCatalog.USER_INVALID_USERNAME);
                case(ValidationMessageCatalog.INVALID_PASSWORD):
                    throw  new BusinessException(ExceptionMessageCatalog.USER_INVALID_PASSWORD);

                    //TODO refactor cascading constraints to be contained in custom constraints

            }
        }

    }
}
