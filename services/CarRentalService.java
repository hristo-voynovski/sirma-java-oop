package services;

import interfaces.Rentable;
import interfaces.Searchable;
import models.Car;

import java.util.ArrayList;
import java.util.List;

public class CarRentalService implements Rentable, Searchable {
    private List<Car> cars;

    /**
     * Create a new car rental service with an initial list of cars
     */
    public CarRentalService(List<Car> initialCars) {
        this.cars = initialCars;
    }

    /**
     * Add new car to rental pool
     */
    public void addCar(Car car) {
        cars.add(car);
    }

    /**
     * Mark a car as removed so it can't be rented anymore
     */
    public void removeCar(int id) {
        Car car = searchById(id);
        if (car != null) car.setStatus("Removed");
    }

    /**
     * Update the details (id, make, model, year, type) of a car
     */
    public void editCar(int id, Car newData) {
        Car car = searchById(id);
        if (car != null) {
            car.setId(newData.getId());
            car.setMake(newData.getMake());
            car.setModel(newData.getModel());
            car.setYear(newData.getYear());
            car.setType(newData.getType());
        }
    }

    /**
     * Get a list of all cars that are currently available for rent
     */
    public List<Car> listAvailableCars() {
        List<Car> available = new ArrayList<>();
        for (Car car : cars) {
            if (car.getStatus().equalsIgnoreCase("Available")) available.add(car);
        }
        return available;
    }

    /**
     * Rent a car to a customer, setting the rental and return dates
     */
    @Override
    public void rent(int id, String renterName, String rentalStartDate, String estimatedReturnDate) {
        Car car = searchById(id);
        if (car != null && car.getStatus().equalsIgnoreCase("Available")) {
            car.setStatus("Rented");
            car.setCurrentRenter(renterName);
            car.setRentalStartDate(rentalStartDate);
            car.setEstimatedReturnDate(estimatedReturnDate);
        }
    }

    /**
     * (Deprecated) Rent a car to a customer without specifying dates
     */
    @Deprecated
    @Override
    public void rent(int id, String renterName) {
        rent(id, renterName, "", "");
    }

    /**
     * Return a rented car, making it available again
     */
    @Override
    public void returnCar(int id) {
        Car car = searchById(id);
        if (car != null && car.getStatus().equalsIgnoreCase("Rented")) {
            car.setStatus("Available");
            car.setCurrentRenter("");
            car.setRentalStartDate("");
            car.setEstimatedReturnDate("");
        }
    }

    /**
     * Search for cars by model name
     */
    @Override
    public List<Car> searchByModel(String model) {
        List<Car> results = new ArrayList<>();
        for (Car car : cars) {
            if (car.getModel().equalsIgnoreCase(model)) results.add(car);
        }
        return results;
    }

    /**
     * Find a car by its unique id
     */
    @Override
    public Car searchById(int id) {
        for (Car car : cars) {
            if (car.getId() == id) return car;
        }
        return null;
    }

    /**
     * Get a list of all cars in the system
     */
    public List<Car> getAllCars() {
        return cars;
    }
}