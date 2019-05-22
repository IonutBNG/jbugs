package user.facade;

import user.control.authenticationUserService.UserAuthenticationService;
import user.dto.UserLoginDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */

@Stateless
public class UserFacade {

    @EJB
    private UserAuthenticationService userAuthenticationService;

    /**
     * Calls authenticateUser method from UserAuthenticationService
     * @param userLoginDto sent further to UserAuthenticationService
     * @return binary value
     */
    public boolean authenticateUser(UserLoginDto userLoginDto){
        return this.userAuthenticationService.authenticateUser(userLoginDto);
    }
}
