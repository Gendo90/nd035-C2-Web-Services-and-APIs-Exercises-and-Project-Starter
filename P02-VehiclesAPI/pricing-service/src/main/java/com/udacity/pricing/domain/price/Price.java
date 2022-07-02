package com.udacity.pricing.domain.price;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents the price of a given vehicle, including currency.
 */
@Entity
@Table(name="price")
public class Price {
	
	@Id
    private Long id;

	@Column(name = "currency")
    private String currency;
    
    @Column(name = "price")
    private BigDecimal price;
    

    public Price() {
    }

    public Price(String currency, BigDecimal price, Long id) {
        this.currency = currency;
        this.price = price;
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
