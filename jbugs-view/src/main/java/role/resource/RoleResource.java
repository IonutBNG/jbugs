package role.resource;

import role.converter.dto.RoleDto;
import role.facade.RoleFacade;
import security.Secured;
import security.SecurityPermission;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */

@Stateless
@Path("/role")
public class RoleResource {

    @EJB
    private RoleFacade roleFacade;

    @GET
    @Path("/add-permissionsAllowed")
    @Produces(MediaType.APPLICATION_JSON)
    @Secured(permissionsAllowed = SecurityPermission.PERMISSION_MANAGEMENT)
    public Response getAllRolesForPermissionsAdd(@Context SecurityContext securityContext){
        return Response.ok().entity(this.roleFacade.getAllRolesForPermissionsAdd()).build();
    }


    @GET
    @Path("/delete-permissionsAllowed")
    @Produces(MediaType.APPLICATION_JSON)
    @Secured(permissionsAllowed = SecurityPermission.PERMISSION_MANAGEMENT)
    public Response getAllRolesForPermissionDelete(@Context SecurityContext securityContext){
        return Response.ok().entity(this.roleFacade.getAllRolesForPermissionsDelete()).build();
    }

    @PUT
    @Path("/add-permissionsAllowed")
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured(permissionsAllowed = SecurityPermission.PERMISSION_MANAGEMENT)
    public Response addPermissions(List<RoleDto> roleDtoList,@Context SecurityContext securityContext){
        this.roleFacade.addPermissions(roleDtoList);
        return Response.ok().build();
    }

    @PUT
    @Path("/delete-permissionsAllowed")
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured(permissionsAllowed = SecurityPermission.PERMISSION_MANAGEMENT)
    public Response deletePermissions(List<RoleDto> roleDtoList,@Context SecurityContext securityContext){
        this.roleFacade.deletePermissions(roleDtoList);
        return Response.ok().build();
    }


}
