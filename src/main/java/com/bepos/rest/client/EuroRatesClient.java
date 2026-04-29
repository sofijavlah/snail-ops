package com.bepos.rest.client;


import com.bepos.model.CurrencyResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey="euro-rates-api")
public interface EuroRatesClient {

    @GET
    @Path("/rates")
    @Produces(MediaType.APPLICATION_JSON)
    CurrencyResponse getRates(@QueryParam("from") String from, @QueryParam("to") String to);
}
