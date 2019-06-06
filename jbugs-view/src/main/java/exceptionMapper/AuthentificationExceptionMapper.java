package exceptionMapper;

import exeptions.AuthentificationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */

@Provider
public class AuthentificationExceptionMapper implements ExceptionMapper<AuthentificationException> {
    @Override
    public Response toResponse(AuthentificationException e) {
        return Response.status(Response.Status.FORBIDDEN).build();
    }


}
