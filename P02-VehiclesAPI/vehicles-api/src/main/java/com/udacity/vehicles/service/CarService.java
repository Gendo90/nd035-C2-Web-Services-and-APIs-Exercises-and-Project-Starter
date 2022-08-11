package com.udacity.vehicles.service;

import com.udacity.vehicles.domain.Location;
import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.domain.car.CarRepository;
import com.udacity.vehicles.client.maps.MapsClient;
import com.udacity.vehicles.client.prices.Price;
import com.udacity.vehicles.client.prices.PriceClient;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

/**
 * Implements the car service create, read, update or delete
 * information about vehicles, as well as gather related
 * location and price data when desired.
 */
@Service
public class CarService {
	
	private final MapsClient mapClient;
	private final PriceClient priceClient;
    private final CarRepository repository;

    public CarService(MapsClient mapClient, PriceClient priceClient, CarRepository repository) {
    	this.mapClient = mapClient;
    	this.priceClient = priceClient;
        this.repository = repository;
    }

    /**
     * Gathers a list of all vehicles
     * @return a list of all vehicles in the CarRepository
     */
    public List<Car> list() {
        return repository.findAll();
    }

    /**
     * Gets car information by ID (or throws exception if non-existent)
     * @param id the ID number of the car to gather information on
     * @return the requested car's information, including location and price
     */
    public Car findById(Long id) {
        Car car = getCarById(id);

        /**
         * Get car price string from pricing service via pricing client
         * includes currency and price as a string
         * Note: The car class file uses @transient, meaning you will need to call
         *   the pricing service each time to get the price.
         */
    	String carPriceString = priceClient.getPrice(id);
        car.setPrice(carPriceString);
        

        /**
         * Note: The Location class file also uses @transient for the address,
         * meaning the Maps service needs to be called each time for the address.
         */
        Location carAddress = mapClient.getAddress(car.getLocation());
        car.setLocation(carAddress);


        return car;
    }

    /**
     * Either creates or updates a vehicle, based on prior existence of car
     * @param car A car object, which can be either new or existing
     * @return the new/updated car is stored in the repository
     */
    public Car save(Car car) {
        if (car.getId() != null) {
            return repository.findById(car.getId())
                    .map(carToBeUpdated -> {
                        carToBeUpdated.setDetails(car.getDetails());
                        carToBeUpdated.setLocation(car.getLocation());
                        return repository.save(carToBeUpdated);
                    }).orElseThrow(CarNotFoundException::new);
        }

        return repository.save(car);
    }

    /**
     * Deletes a given car by ID
     * @param id the ID number of the car to delete
     */
    public void delete(Long id) {
    	// otherwise get the car object to be deleted
    	Car car = getCarById(id);
    	
    	// delete the found car entity
    	repository.delete(car);
    }
    
    /**
     * Retrieves a car by ID or throws an exception if not found
     * serves as a helper function
     * @param id the ID number of the car to find
     */
    private Car getCarById(Long id) {
    	// find the existing car to delete using its id
    	Optional<Car> foundCar = repository.findById(id);
    	
    	// thrown an exception if the car is not present in the DB
    	if(!foundCar.isPresent()) {
    		throw new CarNotFoundException();
    	}
    	
    	// otherwise get the car object to be deleted
    	Car car = foundCar.get();
    	
    	return car;
    }
}
