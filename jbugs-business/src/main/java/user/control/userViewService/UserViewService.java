package user.control.userViewService;

import permission.entity.PermissionEntity;
import role.entity.RoleEntity;
import user.converter.UserConverter;
import user.dao.UserDao;
import user.dto.ViewUserDto;
import user.entity.UserEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class UserViewService {

    @EJB
    private UserDao userDao;

    @EJB
    private UserConverter userConverter;

    public UserViewService() {
    }

    public List<ViewUserDto> gettAllUsers() {
        return userDao.getAllUsers()
                .stream()
                .map(userConverter::convertEntityToViewUserDto)
                .collect(Collectors.toList());
    }

    public List<ViewUserDto> getUsersWithBugManagement() {
        List<UserEntity> allUsers = userDao.getAllUsers();
        List<UserEntity> usersWithBugManagement = new ArrayList<>();
        for (UserEntity u : allUsers) {
            for (RoleEntity r : u.getRoleEntityList()) {
                for (PermissionEntity p : r.getPermissionEntityList()) {
                    if (p.getType().equals("BUG_MANAGEMENT")) {
                        usersWithBugManagement.add(u);
                        break;
                    }
                }
            }
        }

        return usersWithBugManagement
                .stream()
                .map(userConverter::convertEntityToViewUserDto)
                .collect(Collectors.toList());
    }


    /**
     * Calls the getUserByUsername method from dao
     * Returns the found UserEntity
     * @param username used for the method call
     * @return the found UserEntity
     */
    public UserEntity getUserByUsername(String username){
        return this.userDao.getUserByUsername(username);
    }


}
