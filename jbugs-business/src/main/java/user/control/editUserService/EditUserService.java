package user.control.editUserService;

import exeptions.BusinessException;
import exeptions.ExceptionMessageCatalog;
import jsonfactory.JsonFactory;
import user.converter.UserConverter;
import user.dao.UserDao;
import user.dto.EditUserDto;
import user.entity.UserEntity;
import user.validator.UserValidator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */

@Stateless
public class EditUserService {

    public static final Integer COUNTER_INIT = 5;
    public static final Integer COUNTER_DEACTIVATE = 0;

    @EJB
    UserDao userDao;

    @EJB
    UserValidator userValidator;

    @EJB
    private UserConverter userConverter;

    @EJB
    JsonFactory jsonFactory;


    public JsonObject editUser(EditUserDto editUserDto) throws BusinessException {

        //exception will be thrown here
        userValidator.validateBean(editUserDto);
        validateIfEmailExists(editUserDto);

        //handle the activate user
        if (editUserDto.isActivate()) {
            editUserDto.setCounter(COUNTER_INIT);
        }

        //update the user
        userDao.editUser(this.userConverter.convertEditDtoToEntity(editUserDto));

        return jsonFactory.getEditUserJSON();
    }

    private void validateIfEmailExists (EditUserDto editUserDto) {
        if (userDao.checkIfEmailIsUsed(editUserDto.getEmail())) {
            if (!this.userDao.getUsernameByEmail(editUserDto.getEmail()).equals(editUserDto.getUsername())){
                throw new BusinessException(ExceptionMessageCatalog.USER_EMAIL_ALREADY_EXISTS);
            }
        }
    }


    public JsonObject activateUser(EditUserDto editUserDto){
        UserEntity userEntity = userDao.getUserByUsername(editUserDto.getUsername());

        userEntity.setCounter(COUNTER_INIT);

        return jsonFactory.getEditUserJSON();
    }

    public JsonObject deactivateUser(EditUserDto editUserDto) {
        UserEntity userEntity = userDao.getUserByUsername(editUserDto.getUsername());

        userEntity.setCounter(COUNTER_DEACTIVATE);

        return jsonFactory.getEditUserJSON();


    }

}
