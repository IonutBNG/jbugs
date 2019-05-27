package exeptions;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public class ExceptionMessageCatalog {

    public static final ExceptionMessage USER_INVALID_LOGIN_CREDENTIALS = new ExceptionMessage("USER-001"
            ,"Invalid username");

    public static final ExceptionMessage USER_EMAIL_ALREADY_EXISTS = new ExceptionMessage("USER-002"
            ,"The email is already used by another user");

    public static final ExceptionMessage USER_LOGIN_TRIES_EXCEEDED = new ExceptionMessage("USER-003"
            ,"The user failed to introduce the correct login credentials 5 times in a row");

    public static final ExceptionMessage USER_INVALID_EMAIL = new ExceptionMessage("USER-004"
            ,"The email is in an invalid format");

    public static final ExceptionMessage USER_INVALID_PHONENUMBER = new ExceptionMessage("USER-005"
            ,"The phonenumber is in an invalid format");

    public static final ExceptionMessage USER_INVALID_NAME = new ExceptionMessage("USER-006"
            ,"The name is in an invalid format");

    public static final ExceptionMessage USER_INVALID_USERNAME = new ExceptionMessage("USER-007"
            , "The username is in an invalid format");


    public static final ExceptionMessage USER_INVALID_PASSWORD = new ExceptionMessage("USER-008"
            ,"The password is in an invalid format");
}
