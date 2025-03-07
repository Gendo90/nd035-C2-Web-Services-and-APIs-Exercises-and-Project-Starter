package com.udacity.vehicles.client.prices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Implements a class to interface with the Pricing Client for price data.
 */
@Component
public class PriceClient {

    private static final Logger log = LoggerFactory.getLogger(PriceClient.class);

    private final WebClient client;

    public PriceClient(WebClient pricing) {
        this.client = pricing;
    }

    // In a real-world application we'll want to add some resilience
    // to this method with retries/CB/failover capabilities
    // We may also want to cache the results so we don't need to
    // do a request every time
    /**
     * Gets a vehicle price from the pricing client, given vehicle ID.
     * @param vehicleId ID number of the vehicle for which to get the price
     * @return Currency and price of the requested vehicle,
     *   error message that the vehicle ID is invalid, or note that the
     *   service is down.
     */
    // parameter was Long vehicleId before
    public String getPrice(Long id) {
        try {
            Price price = client
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/prices/" + Long.toString(id))
                            //.queryParam("id", id) // was vehicleId before - change back?
                            .build()
                    )
                    .retrieve().bodyToMono(Price.class).block();

            return price.getCurrency() + " " + price.getPrice().toString();

        } catch (Exception e) {
            log.error("Unexpected error retrieving price for vehicle {}", id, e); // was vehicleId
        }
        return "(consult price)";
    }
}
