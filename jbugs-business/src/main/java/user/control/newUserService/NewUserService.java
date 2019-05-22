package user.control.newUserService;

import exeptions.BusinessException;
import exeptions.ExceptionMessageCatalog;
import user.dto.NewUserDto;
import user.validation.ValidationMessageCatalog;
import utils.BaseDto;

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


    public void addNewUser(NewUserDto newUserDto) throws BusinessException {

    }



    private void validateEmail(NewUserDto newUserDto) throws BusinessException {
        //TODO create a named query in user to check if the email is not already in use
    }

    private void validateUserTriesCounter(NewUserDto newUserDto) throws BusinessException {
        //TODO create a named query in user to check if the counter is not 0
    }
}
