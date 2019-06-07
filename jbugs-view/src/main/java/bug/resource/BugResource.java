package bug.resource;

import bug.dto.BugSublistSetterDto;
import bug.dto.NewBugDto;
import bug.dto.ViewBugDto;
import bug.facade.BugFacade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    public Response addNewBug(NewBugDto newBugDto){
        return Response.ok(bugFacade.addNewBug(newBugDto)).build();
    }

    @GET
    @Path("/bugs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllBugs() {
        return Response.ok(bugFacade.getAllBugs()).build();
    }

    @GET
    @Path("/status-transition/{status}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPossibleTransitions(@PathParam("status") String status){
        return Response.ok(this.bugFacade.getPossibleTransitions(status)).build();
    }

    @PUT
    @Path("/set-status")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setStatus(ViewBugDto viewBugDto){
        this.bugFacade.setStatus(viewBugDto);
        return Response.ok().build();
    }

    @GET
    @Path("/get-severity")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSeverityValues(){
        return Response.ok(this.bugFacade.getSeverityValues()).build();
    }
    @PUT
    @Path("/filtered")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSublist(BugSublistSetterDto bugSublistSetterDto){
        return Response.ok().entity(this.bugFacade.getSublist(bugSublistSetterDto)).build();
    }



}
