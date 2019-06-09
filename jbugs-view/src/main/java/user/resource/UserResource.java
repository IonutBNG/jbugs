package user.resource;

import security.Secured;
import security.SecurityPermission;
import user.dto.EditUserDto;
import user.dto.NewUserDto;
import user.dto.UserLoginDto;
import user.dto.UserLogoutDto;
import user.facade.UserFacade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

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
//    @Secured(permissionsAllowed = SecurityPermission.USER_MANAGEMENT)
    public Response getAllUsers(@Context SecurityContext securityContext){
        return Response.ok(userFacade.getAllUsers()).build();
    }

    @GET
    @Path("/users-with-bug-management")
    @Produces(MediaType.APPLICATION_JSON)
    @Secured(permissionsAllowed = SecurityPermission.USER_MANAGEMENT)
    public Response getUsersWithBugManagement(@Context SecurityContext securityContext){
        return Response.ok(userFacade.getUsersWithBugManagement()).build();
    }


    @POST
    @Path("/add-new-user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
//    @Secured(permissionsAllowed = SecurityPermission.USER_MANAGEMENT)
    public Response addNewUser(NewUserDto newUserDto,@Context SecurityContext securityContext) {
        return Response.ok(this.userFacade.addNewUser(newUserDto)).build();
    }


    @POST
    @Path("/edit-user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
//    @Secured(permissionsAllowed = SecurityPermission.USER_MANAGEMENT)
    public Response editUser(EditUserDto editUserDto,@Context SecurityContext securityContext) {
        return Response.ok(this.userFacade.editUser(editUserDto)).build();
    }


    @POST
    @Path("/activate-user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
//    @Secured(permissionsAllowed = SecurityPermission.USER_MANAGEMENT)
    public Response activateUser(EditUserDto editUserDto,@Context SecurityContext securityContext){
        return Response.ok(this.userFacade.activateUser(editUserDto)).build();
    }

    @POST
    @Path("/deactivate-user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
//    @Secured(permissionsAllowed = SecurityPermission.USER_MANAGEMENT)
    public Response deactivateUser(EditUserDto editUserDto,@Context SecurityContext securityContext){
        return Response.ok(this.userFacade.deactivateUser(editUserDto)).build();
    }

    @POST
    @Path("/logout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    public Response logoutUser(UserLogoutDto userLogoutDto) {
        return Response.ok(this.userFacade.logoutUser(userLogoutDto)).build();
    }


}
