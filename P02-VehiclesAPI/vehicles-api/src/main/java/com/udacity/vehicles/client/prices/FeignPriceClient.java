package com.udacity.vehicles.client.prices;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * Implements a class to interface with the Pricing Client for price data.
 */
@FeignClient(name="pricing-service")
public interface FeignPriceClient {
    
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
    @GetMapping("/prices/{id}")
    Price getPriceById(@PathVariable Long id);
    
    // add more endpoints here as necessary!
}
