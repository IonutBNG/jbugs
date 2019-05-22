package user.control.NewUserService;

import exeptions.BusinessException;
import exeptions.ExceptionMessageCatalog;
import user.dto.NewUserDto;
import user.validation.ValidationMessageCatalog;

import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */

@Stateless
public class NewUserService {
    //for validation
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();

    public void addNewUser(NewUserDto newUserDto) throws BusinessException {

    }

    private void validateBean(NewUserDto newUserDto) throws BusinessException {
        Set<ConstraintViolation<NewUserDto>> violations = validator.validate(newUserDto);
        for (ConstraintViolation<NewUserDto> v : violations) {
            String constraintErrorMessage = v.getMessage();
            switch (constraintErrorMessage) {

                case(ValidationMessageCatalog.INVALID_NAME):
                    throw new BusinessException(ExceptionMessageCatalog.USER_INVALID_NAME);
                case(ValidationMessageCatalog.INVALID_EMAIL):
                    throw new BusinessException(ExceptionMessageCatalog.USER_INVALID_EMAIL);
                case(ValidationMessageCatalog.INVALID_MOBILE_NUMBER):
                    throw new BusinessException(ExceptionMessageCatalog.USER_INVALID_PHONENUMBER);

            }
        }

    }

    private void validateEmail(NewUserDto newUserDto) throws BusinessException {
        //TODO create a named query in user to check if the email is not already in use
    }

    private void validateUserTriesCounter(NewUserDto newUserDto) throws BusinessException {
        //TODO create a named query in user to check if the counter is not 0
    }
}
