package role.resource;

import role.converter.dto.RoleDto;
import role.facade.RoleFacade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    @Path("/add-permissions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRolesForPermissionsAdd(){
        return Response.ok().entity(this.roleFacade.getAllRolesForPermissionsAdd()).build();
    }


    @GET
    @Path("/delete-permissions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRolesForPermissionDelete(){
        return Response.ok().entity(this.roleFacade.getAllRolesForPermissionsDelete()).build();
    }

    @PUT
    @Path("/add-permissions")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPermissions(List<RoleDto> roleDtoList){
        this.roleFacade.addPermissions(roleDtoList);
        return Response.ok().build();
    }

    @PUT
    @Path("/delete-permissions")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePermissions(List<RoleDto> roleDtoList){
        this.roleFacade.deletePermissions(roleDtoList);
        return Response.ok().build();
    }


}
