package user.facade;

import user.control.UserViewService.UserViewService;
import user.control.authenticationUserService.UserAuthenticationService;
import user.dto.UserLoginDto;
import user.dto.ViewUserDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */

@Stateless
public class UserFacade {

    @EJB
    private UserAuthenticationService userAuthenticationService;

    @EJB
    private UserViewService userViewService;

    /**
     * Calls authenticateUser method from UserAuthenticationService
     * @param userLoginDto sent further to UserAuthenticationService
     * @return binary value
     */
    public boolean authenticateUser(UserLoginDto userLoginDto){
        return this.userAuthenticationService.authenticateUser(userLoginDto);
    }


    public List<ViewUserDto> getAllUsers() {
        return userViewService.gettAllUsers();
    }
}
