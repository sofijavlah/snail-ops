package com.bepos.resource;

import com.bepos.model.MarineOfficer;
import com.bepos.service.MarineOfficerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/marine-officers")
public class MarineOfficerResource {

    @Inject
    private MarineOfficerService marineOfficerService;

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
////    @Path("/getAllMarines")
//    public Response getAll() {
//        List<MarineOfficer> marines = marineOfficerService.getAll();
//        return Response.ok().entity(marines).build();
//    }

    @GET
    public List<MarineOfficer> getAll() {
        return marineOfficerService.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(Long id) {
        MarineOfficer marine = marineOfficerService.get(id);
        return Response.ok().entity(marine).build();
    }

//    @GET
//    @Path("/{id}")
//    public MarineOfficer getById(@PathParam("id") Long id) {
//        return marineOfficerService.getById(id);
//    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public String add(MarineOfficer marine) {
        marineOfficerService.create(marine);
        return "Added marine";
    }

//    @POST
////    @Path("/{name}")
//    public void create(MarineOfficer marineOfficer) {
//        marineOfficerService.create(marineOfficer);
//    }
}
