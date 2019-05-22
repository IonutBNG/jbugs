package user.control.authenticationUserService;

import exeptions.BusinessException;
import exeptions.ExceptionMessage;
import exeptions.ExceptionMessageCatalog;
import user.dao.UserDao;
import user.dto.UserLoginDto;
import user.entity.UserEntity;
import user.validator.UserValidator;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */

@Stateless
public class UserAuthenticationService {

    @EJB
    private UserDao userDao;

    @EJB
    private UserValidator userValidator;

    public boolean authenticateUser(UserLoginDto userLoginDto){
        UserEntity userEntity = this.userDao.getUserByUsername(userLoginDto.getUsername());

        if (userEntity == null){
            throw new BusinessException(ExceptionMessageCatalog.USER_INVALID_LOGIN_CREDENTIALS);
        }

        return validateUser(userEntity, userLoginDto);



    }

    private boolean validateUser(UserEntity userEntity, UserLoginDto userLoginDto){
        String encryptedPass = userLoginDto.getPassword();
        if (userEntity.getPassword().equals(encryptedPass)){
            if (userEntity.getCounter()>0){
               return true;
            }

        }
        return false;
    }
}
