package com.udacity.pricing;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.udacity.pricing.domain.price.Price;
import com.udacity.pricing.repository.PriceRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PricingServiceApplicationTests {
	
	@Autowired
	private PriceRepository priceRepository;

	@Test
	public void contextLoads() {
	}
	
	@Test
	// Tests that a Price can be saved in the repository successfully
	public void savePrice() {
		Price testPrice = new Price("USD", new BigDecimal(10555.55), 1L);
		Price savedPrice = priceRepository.save(testPrice);
		
		assert(savedPrice.getCurrency()).equals(testPrice.getCurrency());
		assert(savedPrice.getPrice()).equals(testPrice.getPrice());
		assert(savedPrice.getId()).equals(testPrice.getId());
	}
}
