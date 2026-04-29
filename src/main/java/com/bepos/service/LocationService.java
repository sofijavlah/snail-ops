package com.bepos.service;

import com.bepos.model.WantedPirate;
import com.bepos.model.TimeResponse;
import com.bepos.rest.client.TimeClient;
import com.bepos.rest.client.IpClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.hibernate.annotations.NotFound;

@ApplicationScoped
public class LocationService {

    @Inject
    @RestClient
    IpClient ipClient;

    @Inject
    @RestClient
    TimeClient timeClient;

    @Inject
    WantedPirateService wantedPirateService;

    @Transactional
    public TimeResponse getTimezoneByUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("userId is required.");
        }

        WantedPirate pirate = wantedPirateService.get(userId);
        if (pirate == null) {
            throw new NotFoundException("Wanted pirate not found.");
        }

        String ip = ipClient.getPublicIp().trim();
        TimeResponse timeResponse = timeClient.getTime(ip);
        timeResponse.setIpAddress(ip);
        pirate.addTimeResponse(timeResponse);
//        wantedPirateService.edit(pirate);

        return timeResponse;
    }
}
