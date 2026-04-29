package com.bepos.rest.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey="ip-api")
public interface IpClient {

//    @GET
//    @Path("/")
//    @Produces(MediaType.APPLICATION_JSON)
//    public IpResponse getIp();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    String getPublicIp();
}
