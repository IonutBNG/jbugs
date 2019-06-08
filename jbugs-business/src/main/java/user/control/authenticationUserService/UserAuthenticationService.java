package user.control.authenticationUserService;

import com.google.common.hash.Hashing;
import exeptions.BusinessException;
import exeptions.ExceptionMessageCatalog;
import io.jsonwebtoken.Jwts;
import permission.entity.PermissionEntity;
import role.entity.RoleEntity;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */

@Stateless
public class UserAuthenticationService {
    private static final int COUNTER_RESET_TRIES = 5;
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
            resetUserCounter(userLoginDto.getUsername());
            String jwt = generateJwtToken(userLoginDto.getUsername());
            persistJwtToken(jwt, userLoginDto.getUsername());
            return generateJsonSuccesfulAuthentication(jwt, userLoginDto.getUsername());
        }
        return generateJsonFailedAuthentication();
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
    private JsonObject generateJsonSuccesfulAuthentication(String jwt, String username){
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("token", jwt)
                .add("permissions", getPermissionString(username))
                .build();
        return jsonObject;
    }

    private JsonObject generateJsonFailedAuthentication() {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("token", "")
                .add("permissions", "")
                .build();
        return jsonObject;
    }

    private void persistJwtToken(String jwtToken, String username) {
        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken(jwtToken);
        tokenDto.setUsername(username);

        //setting expiration date
        Date date = getCurrentDate();
        date = addDays(date, DAYS_UNTIL_TOKEN_INVALIDATION);
        tokenDto.setExpiryDate(date);

        UserEntity userEntity = userDao.getUserByUsername(username);
        tokenDao.addToken(tokenDto, userEntity);

    }

    private Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
    }

    private String getPermissionString(String username) {
        List<RoleEntity> userRoles = userDao.getUserByUsername(username).getRoleEntityList();
        List<PermissionEntity> permissionEntities = new ArrayList<>();
        List<String> permissionStrings = new ArrayList<>();

        for (RoleEntity r : userRoles) {
            for (PermissionEntity p : r.getPermissionEntityList()) {
                if (!permissionEntities.contains(p)) {
                    permissionEntities.add(p);
                }
            }
        }

        for (PermissionEntity p : permissionEntities) {
            permissionStrings.add(p.getType());
        }

        String res = "";

        for (String s : permissionStrings) {
            res += s + ",";
        }

        return  res;
    }

    private void resetUserCounter(String username) {
        userDao.getUserByUsername(username).setCounter(COUNTER_RESET_TRIES);
    }

    private Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        Date date=new Date(calendar.getTimeInMillis());
        return date;
    }

}
