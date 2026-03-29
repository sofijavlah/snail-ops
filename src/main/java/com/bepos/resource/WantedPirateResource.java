package com.bepos.resource;

import com.bepos.model.WantedPirate;
import com.bepos.service.WantedPirateService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/wanted-pirates")
public class WantedPirateResource {

    @Inject
    private WantedPirateService wantedPirateService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<WantedPirate> wantedPirates = null;
        try {
            wantedPirates = wantedPirateService.getAll();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
        return Response.ok().entity(wantedPirates).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        WantedPirate wantedPirate = null;
        try {
            wantedPirate = wantedPirateService.get(id);
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
        return Response.ok().entity(wantedPirate).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/by-full-name")
    public Response getByFullName(@QueryParam("fullName") String fullName) {
        List<WantedPirate> wantedPirates = null;
        try {
            wantedPirates = wantedPirateService.getByFullName(fullName);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().entity(wantedPirates).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/by-crew-name")
    public Response getByCrewName(@QueryParam("crewName") String crewName) {
        List<WantedPirate> wantedPirates = null;
        try {
            wantedPirates = wantedPirateService.getByCrewName(crewName);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().entity(wantedPirates).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{pirateId}/assign-crew/{crewId}")
    public Response assignCrew(@PathParam("pirateId") Long pirateId, @PathParam("crewId") Long crewId) {
        try {
            WantedPirate wantedPirate = wantedPirateService.assignCrew(pirateId, crewId);
            return Response.ok().entity(wantedPirate).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            wantedPirateService.delete(id);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().build();
    }
}
