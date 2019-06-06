package user.facade;

import user.control.authenticationUserService.UserAuthenticationService;
import user.control.editUserService.EditUserService;
import user.control.newUserService.NewUserService;
import user.dto.EditUserDto;
import user.dto.NewUserDto;
import user.dto.UserLoginDto;
import user.dto.ViewUserDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.JsonObject;
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
    private user.control.userViewService.UserViewService userViewService;

    @EJB
    private NewUserService newUserService;

    @EJB
    private EditUserService editUserService;

    /**
     * Calls authenticateUser method from UserAuthenticationService
     * @param userLoginDto sent further to UserAuthenticationService
     * @return binary value
     */
    public JsonObject authenticateUser(UserLoginDto userLoginDto){
        return this.userAuthenticationService.authenticateUser(userLoginDto);
    }


    public List<ViewUserDto> getAllUsers() {
        return userViewService.gettAllUsers();
    }

    public JsonObject addNewUser(NewUserDto newUserDto) {
        return this.newUserService.addNewUser(newUserDto);
    }

    public JsonObject editUser(EditUserDto editUserDto) {
        return  this.editUserService.editUser(editUserDto);
    }

    public JsonObject activateUser(EditUserDto editUserDto){
        return this.editUserService.activateUser(editUserDto);
    }

    public JsonObject deactivateUser(EditUserDto editUserDto) { return this.editUserService.deactivateUser(editUserDto);}
}
