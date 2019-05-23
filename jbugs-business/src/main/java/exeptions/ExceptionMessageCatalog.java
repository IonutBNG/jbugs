package exeptions;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public class ExceptionMessageCatalog {

    public static ExceptionMessage USER_INVALID_LOGIN_CREDENTIALS = new ExceptionMessage("USER-001"
            ,"Invalid username or password");

    public static ExceptionMessage USER_EMAIL_ALREADY_EXISTS = new ExceptionMessage("USER-002"
            ,"The email is already used by another user");

    public static ExceptionMessage USER_LOGIN_TRIES_EXCEEDED = new ExceptionMessage("USER-003"
            ,"The user failed to introduce the correct login credentials 5 times in a row");

    public static ExceptionMessage USER_INVALID_EMAIL = new ExceptionMessage("USER-004"
            ,"The email is in an invalid format");

    public static ExceptionMessage USER_INVALID_PHONENUMBER = new ExceptionMessage("USER-005"
            ,"The phonenumber is in an invalid format");

    public static ExceptionMessage USER_INVALID_NAME = new ExceptionMessage("USER-006"
            ,"The name contains an invalid character");




}
