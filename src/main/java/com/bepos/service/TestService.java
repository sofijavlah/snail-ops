package com.bepos.service;

import com.bepos.model.CurrencyResponse;
import com.bepos.model.WantedPirate;
import com.bepos.rest.client.EuroRatesClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class TestService {

    @Inject
    EntityManager entityManager;

    @Inject
    @RestClient
    EuroRatesClient euroRatesClient;

    @Inject
    WantedPirateService wantedPirateService;

    @Transactional
    public CurrencyResponse getConvertedCurrency(String from, String to, double value, Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("userId is required.");
        }

        WantedPirate pirate = wantedPirateService.get(userId);
        if (pirate == null) {
            throw new NotFoundException("Wanted pirate not found.");
        }

        CurrencyResponse currencyResponse = euroRatesClient.getRates(from, to);
        double convertedValue = value * currencyResponse.getRate();
        currencyResponse.setValue(value);
        currencyResponse.setConvertedValue(convertedValue);
        pirate.addCurrencyResponse(currencyResponse);
        entityManager.persist(currencyResponse);
        return currencyResponse;
    }
}
