package services;

import models.Car;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    private String filePath;

    /**
     * Create a file service for a specific CSV file
     */
    public FileService(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Read all cars from the CSV file
     */
    public List<Car> readCars() {
        List<Car> cars = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); // skip header
            String line;
            while ((line = reader.readLine()) != null) {
                cars.add(Car.fromCSV(line));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return cars;
    }

    /**
     * Write all cars to the CSV file
     */
    public void writeCars(List<Car> cars) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Id,Make,Model,Year,Type,Status,CurrentRenter,RentalStartDate,EstimatedReturnDate\n");
            for (Car car : cars) {
                writer.write(car.toCSV() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}