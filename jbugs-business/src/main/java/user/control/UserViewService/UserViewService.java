package user.control.UserViewService;

import user.converter.UserConverter;
import user.dao.UserDao;
import user.dto.ViewUserDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class UserViewService {

    @EJB
    UserDao userDao;

    @EJB
    UserConverter userConverter;

    public List<ViewUserDto> gettAllUsers() {
        return userDao.getAllUsers()
                .stream()
                .map(userConverter::convertEntityToViewUserDto)
                .collect(Collectors.toList());
    }


}