package com.bepos.resource;

import com.bepos.model.TimeResponse;
import com.bepos.service.LocationService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
public class LocationResource {

    @Inject
    LocationService locationService;

    @GET
    @Path("/getTimezoneByIP")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTimezoneFromIp(@QueryParam("userId") Long userId) {
        try {
            TimeResponse timeResponse = locationService.getTimezoneByUserId(userId);
            return Response.ok().entity(timeResponse).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }
}
