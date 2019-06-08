package user.control.logoutUserService;

import jsonfactory.JsonFactory;
import user.authentication.TokenDao;
import user.dao.UserDao;
import user.dto.UserLogoutDto;
import user.entity.UserEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.JsonObject;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
@Stateless
public class UserLogoutService {
    @EJB
    UserDao userDao;

    @EJB
    TokenDao tokenDao;

    @EJB
    JsonFactory jsonFactory;

    public UserLogoutService() {
    }

    public JsonObject logout(UserLogoutDto userLogoutDto) {
        String token = userLogoutDto.getToken();
        UserEntity userEntity = userDao.getUserByUsername(userLogoutDto.getUsername());

        this.tokenDao.deleteTokenAtLogout(token, userEntity);

        return this.jsonFactory.getLogoutUserJSON();
    }
}
