package exeptions;

import javax.ejb.ApplicationException;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
@ApplicationException(rollback =true)
public class AuthentificationException extends RuntimeException{

    private ExceptionMessage exceptionMessage;

    public AuthentificationException(final ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public ExceptionMessage getExceptionMessage() {
        return this.exceptionMessage;
    }
}
