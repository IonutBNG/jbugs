package user.resource;

import user.dto.EditUserDto;
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
        return Response
                .status(200)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(userFacade.authenticateUser(userLoginDto))
                .build();
    }

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers(){
        return Response.ok(userFacade.getAllUsers()).build();
    }

    @POST
    @Path("/add-new-user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewUser(NewUserDto newUserDto) {
        return Response.ok(this.userFacade.addNewUser(newUserDto)).build();
    }

    @POST
    @Path("/edit-user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editUser(EditUserDto editUserDto) {
        return Response.ok(this.userFacade.editUser(editUserDto)).build();
    }

}
