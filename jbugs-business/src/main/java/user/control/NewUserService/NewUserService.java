package user.control.newUserService;

import exeptions.BusinessException;
import exeptions.ExceptionMessageCatalog;
import jsonfactory.JsonFactory;
import user.converter.UserConverter;
import user.dao.UserDao;
import user.dto.NewUserDto;
import user.entity.UserEntity;
import user.validator.UserValidator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;


/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */

@Stateless
public class NewUserService {

    @EJB
    private UserDao userDao;

    @EJB
    private UserConverter userConverter;

    @EJB
    private UserValidator userValidator;

    @EJB
    private JsonFactory jsonFactory;


    public JsonObject addNewUser(NewUserDto newUserDto) {
        userValidator.validateBean(newUserDto);
        this.validateEmail(newUserDto);

        UserEntity userEntity;
        userEntity = userConverter.getEntityFromNewUserDto(newUserDto);
        userEntity.setUsername(this.generateUsername(newUserDto.getFirstName(), newUserDto.getLastName()));
        userEntity.setCounter(5);

        userDao.createUser(userEntity);

        return jsonFactory.getNewUserJSON();
    }




    private void validateEmail(NewUserDto newUserDto) {
        if(userDao.checkIfEmailIsUsed(newUserDto.getEmail())) {
            throw new BusinessException(ExceptionMessageCatalog.USER_EMAIL_ALREADY_EXISTS);
        }
    }

    private String generateUsername(String firstName, String lastName) {
        Integer lastDigitPart1 = -1;
        if (lastName.length() < 5) {
            lastDigitPart1 = lastName.length();
        }
        else {
            lastDigitPart1 = 5;
        }

        Integer lastDigitPart2 = 1;

        String Part1 = lastName.substring(0, lastDigitPart1);

        String Part2 = firstName.substring(0, lastDigitPart2);

        String tempUsername = Part1+Part2;

        try {

            while (lastDigitPart1 >= 1 && lastDigitPart2 <= firstName.length() && userDao.checkIfUsernameExists(tempUsername)) {
                lastDigitPart1--;
                lastDigitPart2++;

                Part1 = lastName.substring(0, lastDigitPart1);

                Part2 = firstName.substring(0, lastDigitPart2);

                tempUsername = Part1 + Part2;

            }
            if (userDao.checkIfUsernameExists(tempUsername)) {
                throw new StringIndexOutOfBoundsException();
            }

            return tempUsername;
        }
        catch (StringIndexOutOfBoundsException e) {
            int counter = 1;
            tempUsername += counter;

            while (userDao.checkIfUsernameExists(tempUsername)) {
                tempUsername = tempUsername.substring(0, tempUsername.length()-1);
                counter ++;
                tempUsername += counter;
                System.out.println(tempUsername);
            }
            return tempUsername;
        }
    }


}
