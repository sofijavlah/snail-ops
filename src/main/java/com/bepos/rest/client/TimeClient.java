package com.bepos.rest.client;

import com.bepos.model.TimeResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/time")
@RegisterRestClient(configKey="time-api")
public interface TimeClient {

//    @GET
//    @Path("/time/current/ip")
//    String getTime(@QueryParam("ipAddress") String ipAddress);

    @GET
    @Path("/current/ip")
    @Produces(MediaType.APPLICATION_JSON)
    TimeResponse getTime(@QueryParam("ipAddress") String ipAddress);
}
