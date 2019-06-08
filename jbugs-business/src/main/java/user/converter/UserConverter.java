package user.converter;

import role.dao.RoleDao;
import role.entity.RoleEntity;
import user.dto.EditUserDto;
import user.dto.NewUserDto;
import user.dto.ViewUserDto;
import user.entity.UserEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.hash.Hashing.sha256;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */

@Stateless
public class UserConverter {

    @EJB
    RoleDao roleDao;

    public UserEntity getEntityFromNewUserDto(NewUserDto newUserDto) {
        UserEntity userEntity = new UserEntity();

        userEntity.setFirstName(newUserDto.getFirstName());
        userEntity.setLastName(newUserDto.getLastName());
        userEntity.setPassword(sha256()
                .hashString(newUserDto.getPassword(), StandardCharsets.UTF_8)
                .toString());
        userEntity.setEmail(newUserDto.getEmail());
        userEntity.setMobileNumber(newUserDto.getMobileNumber());
        userEntity.setRoleEntityList(this.getRoleEntityListFromStringArray(newUserDto.getRoles()));

        return userEntity;
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
        UserEntity userEntity = new UserEntity();

        userEntity.setFirstName(editUserDto.getFirstName());
        userEntity.setLastName(editUserDto.getLastName());
        userEntity.setMobileNumber(editUserDto.getMobileNumber());
        userEntity.setEmail( editUserDto.getEmail());
        userEntity.setUsername(editUserDto.getUsername());
        if (editUserDto.getPassword() == "") {
            userEntity.setPassword("");
        }
        else {
            userEntity.setPassword(sha256()
                    .hashString(editUserDto.getPassword(), StandardCharsets.UTF_8)
                    .toString());
        }
        userEntity.setCounter(editUserDto.getCounter());
        userEntity.setRoleEntityList(getRoleEntityListFromStringArray(editUserDto.getRoles()));

        return userEntity;
    }


    private List<RoleEntity> getRoleEntityListFromStringArray(String[] roleStringArray) {
        List<RoleEntity> allRoleEntities = roleDao.getAllRoles();
        List<RoleEntity> res = new ArrayList<>();

        for (String role : roleStringArray) {
            for (RoleEntity roleEntity : allRoleEntities) {
                if (roleEntity.getType().equals(role)) {
                    res.add(roleEntity);
                }
            }
        }
        return res;
    }

}
