package user.converter;

import user.dto.EditUserDto;
import user.dto.NewUserDto;
import user.dto.ViewUserDto;
import user.entity.UserEntity;

import javax.ejb.Stateless;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static com.google.common.hash.Hashing.sha256;

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

    public EditUserDto getDtoFromEntity(){
       EditUserDto editUserDto = new EditUserDto();

        return editUserDto;
    }



    public ViewUserDto convertEntityToViewUserDto(UserEntity userEntity){
        ViewUserDto viewUserDto = new ViewUserDto();

        viewUserDto.setFirstName(userEntity.getFirstName());
        viewUserDto.setLastName(userEntity.getLastName());
        viewUserDto.setEmail(userEntity.getEmail());
        viewUserDto.setMobileNumber(userEntity.getMobileNumber());
        viewUserDto.setUsername(userEntity.getUsername());
        viewUserDto.setCounter(userEntity.getCounter());

        return viewUserDto;
    }

    public UserEntity convertEditDtoToEntity(EditUserDto editUserDto){
        return new UserEntity(
                editUserDto.getFirstName(),
                editUserDto.getLastName(),
                editUserDto.getMobileNumber(),
                editUserDto.getEmail(),
                editUserDto.getUsername(),
                sha256()
                    .hashString(editUserDto.getPassword(), StandardCharsets.UTF_8)
                    .toString(),
                editUserDto.getCounter(),
                Arrays.asList());
    }

}
