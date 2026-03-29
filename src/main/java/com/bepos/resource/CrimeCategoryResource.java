package com.bepos.resource;

import com.bepos.model.CrimeCategory;
import com.bepos.service.CrimeCategoryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/crime-categories")
public class CrimeCategoryResource {

    @Inject
    private CrimeCategoryService crimeCategoryService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<CrimeCategory> crimeCategories = null;
        try {
            crimeCategories = crimeCategoryService.getAll();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
        return Response.ok().entity(crimeCategories).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        CrimeCategory crimeCategory = null;
        try {
            crimeCategory = crimeCategoryService.get(id);
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
        return Response.ok().entity(crimeCategory).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public Response add(CrimeCategory crimeCategory) {
        try {
            crimeCategoryService.create(crimeCategory);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

        return Response.ok().build();
    }
}
