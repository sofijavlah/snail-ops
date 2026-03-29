package com.bepos.resource;

import com.bepos.model.Crew;
import com.bepos.model.WantedPirate;
import com.bepos.service.CrewService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/crews")
public class CrewResource {

    @Inject
    private CrewService crewService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Crew> crews = crewService.getAll();
        return Response.ok().entity(crews).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        Crew crew = crewService.getById(id);
        return Response.ok().entity(crew).build();
    }

    @GET
    @Path("/{id}/pirates")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPiratesByCrewId(@PathParam("id") Long id) {
        List<WantedPirate> wantedPirates = crewService.getPiratesByCrewId(id);
        return Response.ok().entity(wantedPirates).build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public String add(Crew crew) {
        crewService.create(crew);
        return "Added crew";
    }

    @PUT
    @Path("/edit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(Crew crew) {
        Crew editedCrew = crewService.edit(crew);
        return Response.ok().entity(editedCrew).build();
    }
}
