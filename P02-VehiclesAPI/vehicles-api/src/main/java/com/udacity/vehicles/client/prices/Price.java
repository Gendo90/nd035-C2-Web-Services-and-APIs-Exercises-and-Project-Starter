package com.udacity.vehicles.client.prices;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the price of a given vehicle, including currency.
 */
public class Price {

	@JsonProperty("currency")
    private String currency;
	@JsonProperty("price")
    private BigDecimal price;
    private Long vehicleId;
    
    public Price() {
    	super();
    }

    public Price(String currency, BigDecimal price) {
    	this.currency = currency;
    	this.price = price;
    }
    
    public Price(String currency, BigDecimal price, Long vehicleId) {
    	this.currency = currency;
    	this.price = price;
    	this.vehicleId = vehicleId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
