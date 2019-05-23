package user.converter;

import user.dto.NewUserDto;
import user.dto.ViewUserDto;
import user.entity.UserEntity;

import javax.ejb.Stateless;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */

@Stateless
public class UserConverter {

    public UserEntity getEntityFromNewUserDto(NewUserDto newUserDto) {
        UserEntity userEntity = new UserEntity();

        userEntity.setFirstName(newUserDto.getFirstName());
        userEntity.setLastName(newUserDto.getLastName());
        userEntity.setPassword(newUserDto.getPassword());
        userEntity.setEmail(newUserDto.getEmail());
        userEntity.setMobileNumber(newUserDto.getMobileNumber());

        //the username will be set in control

        return userEntity;
    }

    public ViewUserDto convertEntityToViewUserDto(UserEntity userEntity){
        ViewUserDto viewUserDto = new ViewUserDto();

        viewUserDto.setFirstName(userEntity.getFirstName());
        viewUserDto.setLastName(userEntity.getLastName());
        viewUserDto.setEmail(userEntity.getEmail());
        viewUserDto.setMobileNumber(userEntity.getMobileNumber());
        viewUserDto.setUsername(userEntity.getUsername());

        return viewUserDto;
    }

}
