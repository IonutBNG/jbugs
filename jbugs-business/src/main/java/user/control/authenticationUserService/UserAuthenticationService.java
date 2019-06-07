package user.control.authenticationUserService;

import com.google.common.hash.Hashing;
import exeptions.BusinessException;
import exeptions.ExceptionMessageCatalog;
import io.jsonwebtoken.Jwts;
import security.KeyGenerator;
import user.authentication.TokenDao;
import user.authentication.TokenDto;
import user.dao.UserDao;
import user.dto.UserLoginDto;
import user.entity.UserEntity;
import user.validator.UserValidator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Date;
import java.util.Calendar;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */

@Stateless
public class UserAuthenticationService {
    private static final int DAYS_UNTIL_TOKEN_INVALIDATION = 1;

    @EJB
    private UserDao userDao;

    @EJB
    private UserValidator userValidator;

    @EJB
    private TokenDao tokenDao;

    @EJB
    private KeyGenerator keyGenerator;


    /**
     * Validates UserLoginDto
     * @param userLoginDto -> validated & checked
     * @return Json object containing the token if the credentials were valid
     */
    public JsonObject authenticateUser(UserLoginDto userLoginDto){

        this.userValidator.validateBean(userLoginDto);

        if (validCredentials(userLoginDto)){
            String jwt = generateJwtToken(userLoginDto.getUsername());
            persistJwtToken(jwt, userLoginDto.getUsername());
            return generateJson(jwt);
        }
        return generateJson("");
    }

    /**
     * Checks the credentials or throws an appropriate exception
     * @param userLoginDto -> checked
     * @return binary value
     */
    private boolean validCredentials(UserLoginDto userLoginDto){

        UserEntity userEntity = this.userDao.getUserByUsername(userLoginDto.getUsername());

        if (userEntity == null){
            throw new BusinessException(ExceptionMessageCatalog.USER_INVALID_LOGIN_CREDENTIALS);
        }

        if (userEntity.getCounter() == 0){
            throw new BusinessException(ExceptionMessageCatalog.USER_LOGIN_TRIES_EXCEEDED);
        }

        String encryptedPass = Hashing.sha256()
                .hashString(userLoginDto.getPassword(), StandardCharsets.UTF_8)
                .toString();


        if (!userEntity.getPassword().equals(encryptedPass)){
            userEntity.setCounter(userEntity.getCounter()-1);
            this.userDao.setCounter(userEntity);
            return false;
//            throw new BusinessException(ExceptionMessageCatalog.USER_INVALID_LOGIN_CREDENTIALS);
        }

        return true;
    }


    /**
     * Generates token based on the username
     * @param username used for the token generation
     * @return token as String
     */
    private String generateJwtToken(String username) {
        Key key = keyGenerator.getKey();
        String jwt = Jwts.builder()
                .setIssuer(username)
                .signWith(key)
                .compact();
        return jwt;
    }

    /**
     * Generates Json
     * @param jwt used in the Json
     * @return JsonObject
     */
    private JsonObject generateJson(String jwt){
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("token", jwt)
                .build();
        return jsonObject;
    }

    private void persistJwtToken(String jwtToken, String username) {
        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken(jwtToken);
        tokenDto.setUsername(username);

        //setting expiration date
        Calendar c = Calendar.getInstance();
        Date date=new Date(c.getTimeInMillis());
        date = addDays(date, DAYS_UNTIL_TOKEN_INVALIDATION);
        tokenDto.setExpiryDate(date);

        tokenDao.addToken(tokenDto);

    }

    private Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
    }

}
