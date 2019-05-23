package user.resource;

import user.dto.NewUserDto;
import user.dto.UserLoginDto;
import user.facade.UserFacade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(UserLoginDto userLoginDto){
        return Response.ok(this.userFacade.authenticateUser(userLoginDto)).build();
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getData(){
        UserLoginDto userLoginDto = new UserLoginDto("aeiou", "pass");
        return Response.ok(userLoginDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers(){
        return Response.ok(userFacade.getAllUsers()).build();
    }

    @POST
    @Path("/add-new-user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewUser(NewUserDto newUserDto) {
        return Response.ok(this.userFacade.addNewUser(newUserDto)).build();
    }

}
