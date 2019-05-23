package user.control.editUserService;

import exeptions.BusinessException;
import exeptions.ExceptionMessageCatalog;
import user.dao.UserDao;
import user.dto.EditUserDto;
import user.validator.UserValidator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */

@Stateless
public class EditUserService {

    public static final Integer COUNTER_INIT = 5;

    @EJB
    UserDao userDao;

    @EJB
    UserValidator userValidator;


    public void editUser(EditUserDto editUserDto) throws BusinessException {

        //exception will be thrown here
        userValidator.validateBean(editUserDto);
        validateIfEmailExists(editUserDto);

        //handle the activate user
        if (editUserDto.isActivate()) {
            editUserDto.setCounter(COUNTER_INIT);
        }

        //update the user
        userDao.editUser(editUserDto);
    }

    private void validateIfEmailExists (EditUserDto editUserDto)throws BusinessException {
        if (userDao.checkIfEmailExists(editUserDto.getEmail())) {
            throw new BusinessException(ExceptionMessageCatalog.USER_EMAIL_ALREADY_EXISTS);
        }
    }
}
