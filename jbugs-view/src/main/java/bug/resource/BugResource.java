package bug.resource;

import bug.dto.BugSublistSetterDto;
import bug.dto.EditBugDto;
import bug.dto.NewBugDto;
import bug.dto.ViewBugDto;
import bug.facade.BugFacade;
import security.Secured;
import security.SecurityPermission;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Stateless
@Path("/bug")
public class BugResource {

    @EJB
    BugFacade bugFacade;

    @POST
    @Path("/add-new-bug")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured(permissionsAllowed = SecurityPermission.BUG_MANAGEMENT)
    public Response addNewBug(NewBugDto newBugDto,@Context SecurityContext securityContext){
        return Response.ok(bugFacade.addNewBug(newBugDto)).build();
    }

    @GET
    @Path("/bugs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured(permissionsAllowed = SecurityPermission.BUG_MANAGEMENT)
    public Response getAllBugs(@Context SecurityContext securityContext) {
        return Response.ok(bugFacade.getAllBugs()).build();
    }

    @GET
    @Path("/status-transition/{status}")
    @Produces(MediaType.APPLICATION_JSON)
    @Secured(permissionsAllowed = SecurityPermission.BUG_MANAGEMENT)
    public Response getPossibleTransitions(@PathParam("status") String status,@Context SecurityContext securityContext){
        return Response.ok(this.bugFacade.getPossibleTransitions(status)).build();
    }

    @PUT
    @Path("/set-status")
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured(permissionsAllowed = SecurityPermission.BUG_MANAGEMENT)
    public Response setStatus(ViewBugDto viewBugDto,@Context SecurityContext securityContext){
        this.bugFacade.setStatus(viewBugDto);
        return Response.ok().build();
    }

    @GET
    @Path("/get-severity")
    @Produces(MediaType.APPLICATION_JSON)
    @Secured(permissionsAllowed = SecurityPermission.BUG_MANAGEMENT)
    public Response getSeverityValues(@Context SecurityContext securityContext){
        return Response.ok(this.bugFacade.getSeverityValues()).build();
    }

    @PUT
    @Path("/filtered")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Secured(permissionsAllowed = SecurityPermission.BUG_MANAGEMENT)
    public Response getSublist(BugSublistSetterDto bugSublistSetterDto){
        return Response.ok().entity(this.bugFacade.getSublist(bugSublistSetterDto)).build();
    }

    @PUT
    @Path("/edit-bug")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Secured(permissionsAllowed = SecurityPermission.BUG_MANAGEMENT)
    public Response editBug(EditBugDto editBugDto){
        return Response.ok().entity(this.bugFacade.editBug(editBugDto)).build();
    }

    @PUT
    @Path("/close-bug/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Secured(permissionsAllowed = SecurityPermission.BUG_CLOSE)
    public Response closeBug(@PathParam("id")Long id){
        return Response.ok().entity(this.bugFacade.closeBug(id)).build();
    }

    @GET
    @Path("/get-statistics")
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    public Response getStatistics() {
        return Response.ok().entity(this.bugFacade.getStatistics()).build();
    }

}
