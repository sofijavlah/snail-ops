package com.bepos.resource;

import com.bepos.model.CurrencyResponse;
import com.bepos.service.TestService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/test")
public class TestResource {

    @Inject
    TestService testService;

    @GET
    @Path("/currencyConversion")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response convertCurrency(
            @QueryParam("from") String from,
            @QueryParam("to") String to,
            @QueryParam("double") double value,
            @QueryParam("userId") Long userId
    ) {
        try {
            CurrencyResponse currencyResponse = testService.getConvertedCurrency(from, to, value, userId);
            return Response.ok().entity(currencyResponse).build();
        } catch(WebApplicationException e) {
            return Response.ok().status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }
}
