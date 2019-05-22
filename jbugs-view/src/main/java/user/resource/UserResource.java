package user.resource;

import user.dto.UserLoginDto;
import user.facade.UserFacade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */

@Stateless
@Path("/user")
public class UserResource {

    @EJB
    private UserFacade userFacade;

    @POST
    @Path("/authenticate")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(UserLoginDto userLoginDto){
        return Response.ok(this.userFacade.authenticateUser(userLoginDto)).build();
    }
}
