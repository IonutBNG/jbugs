package bug.resource;

import bug.dto.NewBugDto;
import bug.facade.BugFacade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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



}