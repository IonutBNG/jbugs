package user.control.authenticationUserService;

import com.google.common.hash.Hashing;
import exeptions.BusinessException;
import exeptions.ExceptionMessage;
import exeptions.ExceptionMessageCatalog;
import user.dao.UserDao;
import user.dto.UserLoginDto;
import user.entity.UserEntity;
import user.validator.UserValidator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.nio.charset.StandardCharsets;

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


    /**
     * Validates UserLoginDto
     * @param userLoginDto -> validated & checked
     * @return binary response
     */
    public boolean authenticateUser(UserLoginDto userLoginDto){

        this.userValidator.validateBean(userLoginDto);

        return checkCredentials(userLoginDto);

    }

    /**
     * Checks the credentials or throws an appropriate exception
     * @param userLoginDto -> checked
     * @return binary value
     */
    private boolean checkCredentials(UserLoginDto userLoginDto){

        UserEntity userEntity = this.userDao.getUserByUsername(userLoginDto.getUsername());

        if (userEntity == null){
            throw new BusinessException(ExceptionMessageCatalog.USER_INVALID_LOGIN_CREDENTIALS);
        }

        if (userEntity.getCounter() == 0){
            throw new BusinessException(ExceptionMessageCatalog.USER_LOGIN_TRIES_EXCEEDED);
        }

        String encryptedPass = Hashing.sha256()
                .hashString(userLoginDto.getPassword(), StandardCharsets.UTF_8)
                .toString();

        if (!userEntity.getPassword().equals(encryptedPass)){
            userEntity.setCounter(userEntity.getCounter()-1);
            this.userDao.setCounter(userEntity);
            return false;
//            throw new BusinessException(ExceptionMessageCatalog.USER_INVALID_LOGIN_CREDENTIALS);
        }

        return true;
    }
}
