package com.bepos.resource;

import com.bepos.model.Bounty;
import com.bepos.service.BountyService;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/bounties")
public class BountyResource {

    @Inject
    private BountyService bountyService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Bounty> bounties = null;
        try {
            bounties = bountyService.getAll();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
        return Response.ok().entity(bounties).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Bounty bounty = null;
        try {
            bounty = bountyService.get(id);
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
        return Response.ok().entity(bounty).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/by-pirate-name")
    public Response getByPirateName(@QueryParam("fullName") String fullName) {
        List<Bounty> bounties = null;
        try {
            bounties = bountyService.getByPirateName(fullName);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().entity(bounties).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            bountyService.delete(id);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().build();
    }
}
