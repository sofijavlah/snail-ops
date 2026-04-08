package com.bepos.resource;

import com.bepos.model.MarineBase;
import com.bepos.service.MarineBaseService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import com.bepos.model.MarineOfficer;

@Path("/marine-bases")
public class MarineBaseResource {

    @Inject
    private MarineBaseService marineBaseService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<MarineBase> marineBases = null;
        try {
            marineBases = marineBaseService.getAll();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
        return Response.ok().entity(marineBases).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        MarineBase marineBase = null;
        try {
            marineBase = marineBaseService.get(id);
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
        return Response.ok().entity(marineBase).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/officers")
    public Response getOfficersByMarineBaseId(@PathParam("id") Long id) {
        List<MarineOfficer> marineOfficers = null;
        try {
            marineOfficers = marineBaseService.getOfficersByMarineBaseId(id);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().entity(marineOfficers).build();
    }

    @RolesAllowed("admin")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public Response add(MarineBase marineBase) {
        try {
            marineBaseService.create(marineBase);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

        return Response.ok().build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            marineBaseService.delete(id);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

        return Response.ok().build();
    }
}
